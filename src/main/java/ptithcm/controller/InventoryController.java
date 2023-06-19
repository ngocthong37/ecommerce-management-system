package ptithcm.controller;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.model.agency.Agency;
import ptithcm.model.inventory.InventoryReceiving;
import ptithcm.model.inventory.InventoryReceivingDetails;
import ptithcm.model.inventory.StatusReceiving;
import ptithcm.model.user.User;
import ptithcm.service.AgencyService;
import ptithcm.service.InventoryService;
import ptithcm.service.ProductService;

@Transactional
@Controller
@RequestMapping("/admin/")
public class InventoryController {

	@Autowired
	AgencyService agencyService;
	@Autowired
	InventoryService inventoryService;
	@Autowired
	ProductService productService;
	@Autowired
	SessionFactory sessionFactory;

	@RequestMapping(value = "inventory")
	public String showInventoryReceive(ModelMap modelMap) {
		List<Agency> allAgencies = agencyService.getListAgencies();
		modelMap.addAttribute("listAgency", allAgencies);
		List<StatusReceiving> allStatus = inventoryService.getAllStatusReceivings();
		modelMap.addAttribute("listStatus", allStatus);
		return "admin/inventory";
	}

	@RequestMapping(value = "inventory/receive", method = RequestMethod.POST)
	public String newInventoryReceive(ModelMap modelMap, @RequestParam("agency") int agencyId,
			@RequestParam("status") int statusId,
			@RequestParam(value = "productItem", required = false) List<Integer> productItems,
			@RequestParam(value = "qty", required = false) List<Integer> qtys,
			@RequestParam(value = "price", required = false) List<Integer> prices) {
		InventoryReceiving newInventoryReceiving = new InventoryReceiving();
		Date sqlDate = new Date(System.currentTimeMillis());
		newInventoryReceiving.setDate(sqlDate);
		newInventoryReceiving.setUser(inventoryService.getUserById(9));
		newInventoryReceiving.setAgency(agencyService.getAgencyById(agencyId));
		newInventoryReceiving.setStatusReceiving(agencyService.getStatusReceivingById(statusId));
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(newInventoryReceiving);
			t.commit();

			System.out.println("done");
		} catch (Exception e) {
			t.rollback();

			System.out.println(e);
		} finally {
			session.close();
		}
		
		for (int i = 0; i < productItems.size(); i++) {
			InventoryReceivingDetails newIRDetails = new InventoryReceivingDetails();
			newIRDetails.setInventoryReceiving(newInventoryReceiving);
			newIRDetails.setProductItem(productService.getProductItemById(productItems.get(i)));
			newIRDetails.setPrice(prices.get(i));
			newIRDetails.setQty(qtys.get(i));
			Session session1 = sessionFactory.openSession();
			Transaction t1 = session1.beginTransaction();
			try {
				session1.save(newIRDetails);
				t1.commit();
				System.out.println("done");
			} catch (Exception e) {
				t1.rollback();
				System.out.println(e);
			} finally {
				session1.close();
			}
			if(!agencyService.getStatusReceivingById(statusId).getStatus().equals("DRAFT")) {
			productService.updateQty(productItems.get(i), qtys.get(i));
			}
		}
		return "redirect:/admin/inventory/list.htm";
	}
	
	@RequestMapping(value = "inventory/list",method = RequestMethod.GET)
	public String showListInventory(ModelMap model) {
		List<InventoryReceiving> list = inventoryService.getAllInventoryReceivings();
		model.addAttribute("listIR",list);
		return "admin/inventory/list";
	}
	
	@RequestMapping(value ="inventory/details/{id}",method =  RequestMethod.GET)
	public String showInventoryDetails(@PathVariable int id, ModelMap model) {
		List<InventoryReceivingDetails> list = inventoryService.listInventoryReceivingDetails(id);
		long sum =0;
		InventoryReceiving inventoryReceiving = list.get(0).getInventoryReceiving();
		for (InventoryReceivingDetails inventoryReceivingDetails : list) {
			sum += inventoryReceivingDetails.getPrice()* inventoryReceivingDetails.getQty();		}
		model.addAttribute("list",list);
		model.addAttribute("sum",sum);
		model.addAttribute("iR",inventoryReceiving);
		List<StatusReceiving> allStatus = inventoryService.getAllStatusReceivings();
		model.addAttribute("listStatus", allStatus);
		int selectedStatus = list.get(0).getInventoryReceiving().getStatusReceiving().getId();
		model.addAttribute("selectedStatus",selectedStatus);
		if(list.get(0).getInventoryReceiving().getStatusReceiving().getStatus().equals("DRAFT")) {
			model.addAttribute("test",true);
		}
		return "admin/inventory/details";
	}
	
	@RequestMapping(value ="inventory/details/done",method =  RequestMethod.POST)
	public String changeStatus(@RequestParam("id") int id, ModelMap model, @RequestParam("status") int statusId) {
		InventoryReceiving inventoryReceiving = inventoryService.getInventoryReceivingById(id);
		inventoryReceiving.setStatusReceiving(agencyService.getStatusReceivingById(statusId));
		List<InventoryReceivingDetails> list = inventoryService.listInventoryReceivingDetails(id);
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.merge(inventoryReceiving);
			t.commit();
			System.out.println("done");
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		} finally {
			session.close();
		}
		if(!agencyService.getStatusReceivingById(statusId).getStatus().equals("DRAFT")) {
			for (InventoryReceivingDetails inventoryReceivingDetails : list) {
				productService.updateQty(inventoryReceivingDetails.getProductItem().getId(),inventoryReceivingDetails.getQty());
			}
		}
		return "redirect:/admin/inventory/list.htm";
	}
	
}
