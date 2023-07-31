package poly.com.service;

import java.sql.Timestamp;
import java.util.List;

import poly.com.dao.FavoriteDao;
import poly.com.dao.favoriteDaoImpl;
import poly.com.entity.User;
import poly.com.entity.Video;
import poly.com.entity.favorite;

public class favoriteServiceImpl implements favoriteService{
	private FavoriteDao dao;
	private VideoService videoService =new videoServiceImpl();
	
	public favoriteServiceImpl() {
		dao =new favoriteDaoImpl();
	}
	@Override
	public List<favorite> findByUser(String username) {
		// TODO Auto-generated method stub
		return dao.findByUser(username);
	}

	@Override
	public List<favorite> findByUserAndIsLiked(String username) {
		// TODO Auto-generated method stub
		return dao.findByUserAndIsLiked(username);
	}

	@Override
	public favorite findByUserandIsvideoId(Integer userId, Integer videoId) {
		// TODO Auto-generated method stub
		return dao.findByUserandIsvideoId(userId, videoId);
	}

	@Override
	public favorite create(User user, Video video) {
		// TODO Auto-generated method stu
		favorite existFavorite =findByUserandIsvideoId(user.getId(), video.getId());
		if (existFavorite ==null) {
			existFavorite =new favorite();
			existFavorite = new favorite();
			existFavorite.setUser(user);
			existFavorite.setVideo(video);
			existFavorite.setVideoDate(new Timestamp(System.currentTimeMillis()));
			existFavorite.setIsLiked(Boolean.FALSE);
			return dao.create(existFavorite);
		}
		
		
		return existFavorite;
	}

	@Override
	public boolean updateLikeOrUnlike(User user, String videoHref) {
		// TODO Auto-generated method stub
		Video video = videoService.findByHref(videoHref);
		favorite exisFavorite =findByUserandIsvideoId(user.getId(), video.getId());
		if (exisFavorite.getIsLiked()==Boolean.FALSE ) {
			exisFavorite.setIsLiked(Boolean.TRUE);
			exisFavorite.setLikedDate(new Timestamp(System.currentTimeMillis()));
		}else {
			exisFavorite.setIsLiked(Boolean.FALSE);
			exisFavorite.setLikedDate(null);
		}
		favorite updateFavorite =dao.update(exisFavorite);
		return updateFavorite!=null?true:false;
		
	}

}
