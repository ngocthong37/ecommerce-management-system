package ptithcm.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.constant.SystemConstant;
import ptithcm.model.address.District;
import ptithcm.model.address.Province;
import ptithcm.model.address.Ward;
import ptithcm.model.user.User;
import ptithcm.model.user.UserPermission;
import ptithcm.model.user.UserProfile;
import ptithcm.service.AddressService;
import ptithcm.service.UserService;
import ptithcm.util.SessionUtil;

@Controller
@RequestMapping(value = "/user/")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	AddressService addressService;

	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		List<UserPermission> list = userService.getListUserPermissions();
		model.addAttribute("listPermission", list);
		return "user/new";
	}

	@RequestMapping(value = "new", method = RequestMethod.POST)
	public String newUser(@RequestParam("username") String username, @RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("permission") Integer permision, ModelMap model) {
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setEmail(email);
		newUser.setUserPermission(userService.getPermissionById(permision));
		newUser.setStatus(true);
		System.out.println(newUser.getUsername());
		System.out.println(newUser.getPassword());
		System.out.println(newUser.getEmail());
		System.out.println(newUser.getUserPermission().getValue());
		Session session1 = sessionFactory.openSession();
		org.hibernate.Transaction t = session1.beginTransaction();
		try {
			session1.save(newUser);
			t.commit();
			model.addAttribute("message", "Thêm mới thành công! ");
			System.out.println("done");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Thêm mới thất bại! ");
			System.out.println(e);
		} finally {
			session1.close();
		}
		return "redirect:/user/list.htm";
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String listUser(ModelMap model) {
		List<User> list = userService.getAllUser();
		model.addAttribute("listUser", list);
		return "user/list";
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable int id) {
		User deleteUser = userService.getUserById(id);
		if(deleteUser.getStatus()) {
		deleteUser.setStatus(false);}
		else {
			deleteUser.setStatus(true);
		}
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction t = session.beginTransaction();
		try {
			session.merge(deleteUser);
			t.commit();
			System.out.println("done");
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		} finally {
			session.close();
		}
		return "redirect:/user/list.htm";
	}
	
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String editUser(@PathVariable int id,ModelMap model) {
		User user = userService.getUserById(id);
		if (user.getUserProfile() != null) {
			model.addAttribute("name", user.getUserProfile().getName());
			model.addAttribute("phone", user.getUserProfile().getPhoneNumber());
			model.addAttribute("email", user.getEmail());
			model.addAttribute("selectedWard", user.getUserProfile().getWard().getId());
			model.addAttribute("selectedDistrict", user.getUserProfile().getWard().getDistrict().getId());
			model.addAttribute("selectedProvince", user.getUserProfile().getWard().getProvince().getId());
			List<Province> listPros = addressService.listProvinces();
			model.addAttribute("listPros", listPros);
			List<District> listDicts = addressService
					.listDistricts(user.getUserProfile().getWard().getProvince().getId());
			model.addAttribute("listDicts", listDicts);
			List<Ward> listWards = addressService.listWards(user.getUserProfile().getWard().getDistrict().getId());
			model.addAttribute("listWards", listWards);
			model.addAttribute("userId",id);
			return "user/profile";
		} else {
			model.addAttribute("email", user.getEmail());
			List<Province> listPros = addressService.listProvinces();
			model.addAttribute("listPros", listPros);
			model.addAttribute("userId",id);
			return "user/profile";
		}
	}
	
	@RequestMapping(value = "edit/profile", method = RequestMethod.POST)
	public String editUser(ModelMap model, HttpServletRequest request, @RequestParam("province") int provinceId,
			@RequestParam("district") int districtId, @RequestParam("ward") int wardId,
			@RequestParam("name") String name, @RequestParam("phone") String phone,
			@RequestParam("email") String email,@RequestParam("userId") int id) {
		model.addAttribute("name", name);
		model.addAttribute("phone", phone);
		model.addAttribute("email", email);
		model.addAttribute("userId",id);
		if (provinceId != 0) {
			if (districtId != 0) {
				List<Province> listPros = addressService.listProvinces();
				model.addAttribute("listPros", listPros);
				List<District> listDicts = addressService.listDistricts(provinceId);
				model.addAttribute("listDicts", listDicts);
				List<Ward> listWards = addressService.listWards(districtId);
				model.addAttribute("listWards", listWards);
				model.addAttribute("selectedProvince", provinceId);
				model.addAttribute("selectedDistrict", districtId);
				return "user/profile";
			}
			List<Province> listPros = addressService.listProvinces();
			model.addAttribute("listPros", listPros);
			List<District> listDicts = addressService.listDistricts(provinceId);
			model.addAttribute("listDicts", listDicts);
			model.addAttribute("selectedProvince", provinceId);
			return "user/profile";
		}
		return "user/profile";
	}
	

	@RequestMapping(value = "edit/profile/done.htm", method = RequestMethod.POST)
	public String editProfile(ModelMap model, HttpServletRequest request, @RequestParam("ward") int wardId,
			@RequestParam("name") String name, @RequestParam("phone") String phone,
			@RequestParam("email") String email, @RequestParam("userId") int id) {
		Date sqlDate = new Date(System.currentTimeMillis());
		User user = userService.getUserById(id);
		if (user.getUserProfile() != null) {
			UserProfile editProfile = user.getUserProfile();
			editProfile.setName(name);
			editProfile.setPhoneNumber(phone);
			editProfile.setWard(addressService.getWard(wardId));
			editProfile.setModifiedAt(sqlDate);
			user.setEmail(email);
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction t = session.beginTransaction();
			try {
				session.merge(editProfile);
				session.merge(user);
				t.commit();
				model.addAttribute("message", "Thêm mới thành công! ");
				System.out.println("done");
			} catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "Thêm mới thất bại! ");
				System.out.println(e);
			} finally {
				session.close();
			}

		} else {
			UserProfile newUserProfile = new UserProfile();
			newUserProfile.setUser(user);
			newUserProfile.setName(name);
			newUserProfile.setPhoneNumber(phone);
			newUserProfile.setWard(addressService.getWard(wardId));
			newUserProfile.setCreateAt(sqlDate);
			user.setEmail(email);
			user.setUserProfile(newUserProfile);
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction t = session.beginTransaction();
			try {
				session.merge(newUserProfile);
				session.merge(user);
				t.commit();
				model.addAttribute("message", "Thêm mới thành công! ");
				System.out.println("done");
			} catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "Thêm mới thất bại! ");
				System.out.println(e);
			} finally {
				session.close();
			}
		}
		return "redirect:/user/list.htm";
	}
	
	@RequestMapping(value = "profile", method = RequestMethod.GET)
	public String userProfile(ModelMap model, HttpServletRequest request) {
		if (SessionUtil.getInstance().getValue(request, SystemConstant.Model.USER_MODEL) == null) {
			return "redirect:/admin/login.htm";
		}
		User user = ((User) SessionUtil.getInstance().getValue(request, SystemConstant.Model.USER_MODEL));
		if (user.getUserProfile() != null) {
			model.addAttribute("name", user.getUserProfile().getName());
			model.addAttribute("phone", user.getUserProfile().getPhoneNumber());
			model.addAttribute("email", user.getEmail());
			model.addAttribute("selectedWard", user.getUserProfile().getWard().getId());
			model.addAttribute("selectedDistrict", user.getUserProfile().getWard().getDistrict().getId());
			model.addAttribute("selectedProvince", user.getUserProfile().getWard().getProvince().getId());
			List<Province> listPros = addressService.listProvinces();
			model.addAttribute("listPros", listPros);
			List<District> listDicts = addressService
					.listDistricts(user.getUserProfile().getWard().getProvince().getId());
			model.addAttribute("listDicts", listDicts);
			List<Ward> listWards = addressService.listWards(user.getUserProfile().getWard().getDistrict().getId());
			model.addAttribute("listWards", listWards);
			return "user/profile";
		} else {
			model.addAttribute("email", user.getEmail());
			List<Province> listPros = addressService.listProvinces();
			model.addAttribute("listPros", listPros);
			return "user/profile";
		}
	}

	@RequestMapping(value = "profile", method = RequestMethod.POST)
	public String userProfile1(ModelMap model, HttpServletRequest request, @RequestParam("province") int provinceId,
			@RequestParam("district") int districtId, @RequestParam("ward") int wardId,
			@RequestParam("name") String name, @RequestParam("phone") String phone,
			@RequestParam("email") String email) {
		model.addAttribute("name", name);
		model.addAttribute("phone", phone);
		model.addAttribute("email", email);
		if (provinceId != 0) {
			if (districtId != 0) {
				List<Province> listPros = addressService.listProvinces();
				model.addAttribute("listPros", listPros);
				List<District> listDicts = addressService.listDistricts(provinceId);
				model.addAttribute("listDicts", listDicts);
				List<Ward> listWards = addressService.listWards(districtId);
				model.addAttribute("listWards", listWards);
				model.addAttribute("selectedProvince", provinceId);
				model.addAttribute("selectedDistrict", districtId);
				return "user/profile";
			}
			List<Province> listPros = addressService.listProvinces();
			model.addAttribute("listPros", listPros);
			List<District> listDicts = addressService.listDistricts(provinceId);
			model.addAttribute("listDicts", listDicts);
			model.addAttribute("selectedProvince", provinceId);
			return "user/profile";
		}
		return "user/profile";
	}

	@RequestMapping(value = "profile/done.htm", method = RequestMethod.POST)
	public String addProfile(ModelMap model, HttpServletRequest request, @RequestParam("ward") int wardId,
			@RequestParam("name") String name, @RequestParam("phone") String phone,
			@RequestParam("email") String email) {
		Date sqlDate = new Date(System.currentTimeMillis());
		if (SessionUtil.getInstance().getValue(request, SystemConstant.Model.USER_MODEL) == null) {
			return "redirect:/admin/login.htm";
		}
		User user = ((User) SessionUtil.getInstance().getValue(request, SystemConstant.Model.USER_MODEL));
		if (user.getUserProfile() != null) {
			UserProfile editProfile = user.getUserProfile();
			editProfile.setName(name);
			editProfile.setPhoneNumber(phone);
			editProfile.setWard(addressService.getWard(wardId));
			editProfile.setModifiedAt(sqlDate);
			user.setEmail(email);
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction t = session.beginTransaction();
			try {
				session.merge(editProfile);
				session.merge(user);
				t.commit();
				model.addAttribute("message", "Thêm mới thành công! ");
				System.out.println("done");
			} catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "Thêm mới thất bại! ");
				System.out.println(e);
			} finally {
				session.close();
			}

		} else {
			UserProfile newUserProfile = new UserProfile();
			newUserProfile.setUser(user);
			newUserProfile.setName(name);
			newUserProfile.setPhoneNumber(phone);
			newUserProfile.setWard(addressService.getWard(wardId));
			newUserProfile.setCreateAt(sqlDate);
			user.setEmail(email);
			user.setUserProfile(newUserProfile);
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction t = session.beginTransaction();
			try {
				session.merge(newUserProfile);
				session.merge(user);
				t.commit();
				model.addAttribute("message", "Thêm mới thành công! ");
				System.out.println("done");
			} catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "Thêm mới thất bại! ");
				System.out.println(e);
			} finally {
				session.close();
			}
		}
		return "redirect:/user/profile.htm";
	}

}