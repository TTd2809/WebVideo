package poly.com.dao;

import java.util.List;
import java.util.Map;

import poly.com.entity.User;

public interface UserDao {
	User findByID(Integer id);
	User findByEmail(String email);
	User findByUsername(String username);
	User findByUsernameandPassword(String username, String password);
	List<User> findALl();
	List <User> findAll(int pageNumber, int pageSize);
	User create(User entity);
	User update(User entity);
	User delete(User entity);
	List<User> findUserLikeByVideoHref(Map<String, Object> params);
}
