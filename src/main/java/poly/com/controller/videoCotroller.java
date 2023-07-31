package poly.com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import poly.com.constant.SessionAttr;
import poly.com.entity.User;
import poly.com.entity.Video;
import poly.com.entity.favorite;
import poly.com.service.VideoService;
import poly.com.service.favoriteService;
import poly.com.service.favoriteServiceImpl;
import poly.com.service.videoServiceImpl;

/**
 * Servlet implementation class videoCotroller
 */
@WebServlet(urlPatterns = "/video")
public class videoCotroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private VideoService videoService = new videoServiceImpl();
    private favoriteService favoriteService =new favoriteServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public videoCotroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String actionParam =req.getParameter("action");
		String href = req.getParameter("id");
		HttpSession session =req.getSession();
		
		switch (actionParam) {
		case "watch": {
			doGetWatch(session,href,req,resp);
			break;
		}
		case "like": {
			doGetLike(session,href,req,resp);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + actionParam);
		}
		
	}
	private void doGetWatch(HttpSession session,String href,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Video video =videoService.findByHref(href);
		req.setAttribute("video", video);
		User currentUser =(User)session.getAttribute(SessionAttr.CURRENT_USER);
		if (currentUser!=null ) {
			favorite favorite =favoriteService.create(currentUser, video);
			req.setAttribute("flagLikedBtn", favorite.getIsLiked());
		}
		req.getRequestDispatcher("/views/user/video-details.jsp").forward(req, resp);
	}
	private void doGetLike(HttpSession session,String href,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		User currentUser =(User)session.getAttribute(SessionAttr.CURRENT_USER);
		boolean result = favoriteService.updateLikeOrUnlike(currentUser, href);
		if (result==true) {
			resp.setStatus(204);
		}else {
			resp.setStatus(400);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
