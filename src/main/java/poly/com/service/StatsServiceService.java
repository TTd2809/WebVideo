package poly.com.service;

import java.util.List;

import poly.com.dao.StatDao;
import poly.com.dao.StatDaoimpl;
import poly.com.dto.videoLikeInfo;

public class StatsServiceService implements StatsService {
	private StatDao statDao;
	public StatsServiceService() {
		statDao = new StatDaoimpl();
	}
	@Override
	public List<videoLikeInfo> finVideoLikeInfos() {
		// TODO Auto-generated method stub
		return statDao.findVideoLikeInfos();
	}
	
}
