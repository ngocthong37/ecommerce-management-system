package ptithcm.service.admin;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
	public boolean validateUser(String user, String password) {
		return user.equalsIgnoreCase("tiennguyen") && password.equalsIgnoreCase("1234");
	}
}
