package poly.com.dao;

import java.util.List;

import poly.com.entity.favorite;

public interface FavoriteDao {
	List<favorite> findByUser(String username);
	List<favorite> findByUserAndIsLiked(String username);
	favorite findByUserandIsvideoId(Integer userId, Integer videoId);
	favorite create(favorite entity);
	favorite update(favorite entity);

}
