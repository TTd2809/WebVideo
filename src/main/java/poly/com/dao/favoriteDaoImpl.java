package poly.com.dao;

import java.util.List;

import poly.com.entity.favorite;

public class favoriteDaoImpl extends AbstractDao<favorite> implements FavoriteDao{

	@Override
	public List<favorite> findByUser(String username) {
		// TODO Auto-generated method stub
		String sql ="SELECT o FROM favorite o WHERE o.user.username =?0 AND o.video.isActive =1 ORDER BY o.videoDate DESC";
		return super.findMany(favorite.class, sql, username);
	}

	@Override
	public List<favorite> findByUserAndIsLiked(String username) {
		// TODO Auto-generated method stub
		String sql ="SELECT o FROM favorite o WHERE o.user.username =?0 AND o.isLiked =1 "
				+ "AND o.video.isActive =1 ORDER BY o.videoDate DESC";
		return super.findMany(favorite.class, sql, username);
	}

	@Override
	public favorite findByUserandIsvideoId(Integer userId, Integer videoId) {
		// TODO Auto-generated method stub
		String sql ="SELECT o FROM favorite o WHERE o.user.id =?0 AND o.video.id =?1 AND o.video.isActive=1 ";
		return super.findOne(favorite.class, sql, userId,videoId	);
	}

	@Override
	public favorite create(favorite entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public favorite update(favorite entity) {
		// TODO Auto-generated method stub
		return super.upadate(entity);
	}
	
}
