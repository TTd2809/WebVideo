package poly.com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import poly.com.dao.UserDao;
import poly.com.dao.UserDaoImpl;
import poly.com.entity.User;

public class UserServiceImpl implements UserService {

	private UserDao dao;
	
	public UserServiceImpl() {
		dao = new UserDaoImpl();
	}
	@Override
	public User findByID(Integer id) {
		// TODO Auto-generated method stub
		return dao.findByID(id);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return dao.findByEmail(email);
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return dao.findByUsername(username);
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return dao.findByUsernameandPassword(username, password);
	}

	@Override
	public User resetPassword(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findALl() {
		// TODO Auto-generated method stub
		return dao.findALl();
	}

	@Override
	public List<User> findAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return dao.findAll(pageNumber, pageSize);
	}

	@Override
	public User register(String username, String password, String email) {
		// TODO Auto-generated method stub
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setEmail(email);
		newUser.setAdmin(Boolean.FALSE);
		newUser.setActive(Boolean.TRUE);
		return dao.create(newUser);
		
	}

	@Override
	public User update(User entity) {
		// TODO Auto-generated method stub
		return dao.update(entity);
	}

	@Override
	public User delete(String username) {
		// TODO Auto-generated method stub
		User user = dao.findByUsername(username);
		user.setActive(Boolean.FALSE);
		return dao.delete(user);
	}
	@Override
	public List<User> findUserLikeByVideoHref(String href) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<>();
		params.put("videoHref", href);
		return dao.findUserLikeByVideoHref(params);
	}
	
}
