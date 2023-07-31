package poly.com.dao;

import java.util.ArrayList;
import java.util.List;

import poly.com.dto.videoLikeInfo;

public class StatDaoimpl extends AbstractDao<Object[]> implements StatDao{

	@Override
	public List<videoLikeInfo> findVideoLikeInfos() {
		// TODO Auto-generated method stub
		String sql ="select v.id, v.title, v.href, SUM(cast(f.isLiked as int)) as totallike from"
				+ " video v left join favorite f on v.id =f.videoId"
				+ " where"
				+ "	v.isActive =1 "
				+ "group by "
				+ "	v.id, v.title, v.href "
				+ "order by "
				+ "SUM(cast(f.isLiked as int)) desc";
		List<Object[]> objects =super.findManyByNativeQuery( sql);
		List<videoLikeInfo> result = new ArrayList<>();
		objects.forEach(object -> {
			videoLikeInfo videoLikeInfo =setDataVideoLikeInfo(object);
			result.add(videoLikeInfo);
		});
		return result;
	}
	private videoLikeInfo setDataVideoLikeInfo(Object[] objects) {
		videoLikeInfo videoLikeInfo =new videoLikeInfo();
		videoLikeInfo.setVideoId((Integer)objects[0]);
		videoLikeInfo.setTitle((String)objects[1]);
		videoLikeInfo.setHref((String)objects[2]);
		videoLikeInfo.setTotalLike(objects[3] ==null?0:(Integer)objects[3]);
		return videoLikeInfo;
	}
	
}
