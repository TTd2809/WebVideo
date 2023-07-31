package poly.com.dao;

import java.util.List;

import poly.com.dto.videoLikeInfo;
import poly.com.entity.User;

public interface StatDao {
	List<videoLikeInfo> findVideoLikeInfos();
	
}
