package authinterceptor;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public class AuthController {
	@Auth(role = Auth.Role.CUSTOMER)
    @RequestMapping(value = "/login/customer", method = RequestMethod.GET)
    public String customerLogin(HttpSession session) {
        return "e-commerce/login";
    }

    @Auth(role = Auth.Role.ADMIN)
    @RequestMapping(value = "/login/admin", method = RequestMethod.GET)
    public String adminLogin(HttpSession session) {
        return "admin/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String checkLogin(@RequestParam("user") String user,
                             @RequestParam("pass") String pass,
                             @RequestParam("role") String role,
                             HttpSession session) {
        if ("123456".equals(pass)) {
            if ("admin".equals(user)) {
                session.setAttribute("isLogin", true);
                session.setAttribute("user", user);
                session.setAttribute("role", Auth.Role.ADMIN);
                return "redirect:/admin";
            } else if ("customer".equals(user)) {
                session.setAttribute("isLogin", true);
                session.setAttribute("user", user);
                session.setAttribute("role", Auth.Role.CUSTOMER);
                return "redirect:/customer";
            }
        }

        return "login";
    }
}
