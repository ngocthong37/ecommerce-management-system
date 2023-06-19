package ptithcm.dao.user;

import java.util.List;

import ptithcm.model.user.User;
import ptithcm.model.user.UserPermission;

public interface UserDao {
	public User findByUserNameAndPasswordAndStatus(String userName, String password, boolean status);
	public User getUserById(int id);
	public List<UserPermission> getListPermissions();
	public UserPermission getPermissionbyId(int id);
	public List<User> getAllUsers();
	public List<User> getAllShipper();
	
}
