package poly.com.dao;

import java.util.List;
import java.util.Map;

import poly.com.entity.User;

public class UserDaoImpl extends AbstractDao<User> implements UserDao{

	@Override
	public User findByID(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(User.class, id);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		String sql ="SELECT o FROM User o WHERE o.email =?0";
		return super.findOne(User.class, sql, email);
	}

	@Override
	public User findByUsername(String username) {
		String sql ="SELECT o FROM User o WHERE o.username =?0";
		return super.findOne(User.class, sql, username);
	}

	@Override
	public User findByUsernameandPassword(String username, String password) {
		String sql ="SELECT o FROM User o WHERE o.username =?0 AND o.password =?1";
		return super.findOne(User.class, sql, username,password);
	}

	@Override
	public List<User> findALl() {
		// TODO Auto-generated method stub
		return super.findAll(User.class, true);
	}


	@Override
	public User create(User entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public User update(User entity) {
		// TODO Auto-generated method stub
		return super.upadate(entity);
	}

	@Override
	public User delete(User entity) {
		// TODO Auto-generated method stub
		return super.delete(entity);
	}

	@Override
	public List<User> findAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return super.findAll(User.class, true,pageNumber,pageSize);
	}

	@Override
	public List<User> findUserLikeByVideoHref(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return super.callStored("User.FindListLikedByVideoHref" , params);
	}

}
