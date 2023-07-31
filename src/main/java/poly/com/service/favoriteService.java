package poly.com.service;

import java.util.List;

import poly.com.entity.User;
import poly.com.entity.Video;
import poly.com.entity.favorite;

public interface favoriteService {
	List<favorite> findByUser(String username);
	List<favorite> findByUserAndIsLiked(String username);
	favorite findByUserandIsvideoId(Integer userId, Integer videoId);
	favorite create(User user, Video video);
	boolean updateLikeOrUnlike(User user, String videoHref);
}
