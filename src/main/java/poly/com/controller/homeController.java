package poly.com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
 * Servlet implementation class homeController
 */
@WebServlet(urlPatterns ={"/index","/favorites","/history"})
public class homeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VideoService videoService =new videoServiceImpl();
    private favoriteService favoriteService =new favoriteServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public homeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session =req.getSession();
		String path = req.getServletPath();
		switch (path) {
		case "/index": {
			doGetIndex(req, resp);
			break;
		}
		case "/favorites": {
			doGetFavorites(session,req, resp);
			break;
		}
		case "/history": {
			doGetHistory(session,req, resp);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
		
		
		
		
	}
	private void doGetIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Video> videos =videoService.findAll();
		req.setAttribute("videos", videos);
		
		req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);
	}
	private void doGetHistory(HttpSession session,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user =(User)session.getAttribute(SessionAttr.CURRENT_USER);
		List<favorite> favorites =favoriteService.findByUser(user.getUsername());
		List<Video> videos =new ArrayList<>();
		favorites.forEach(item -> videos.add(item.getVideo()));
		req.setAttribute("videos", videos);
		
		req.getRequestDispatcher("/views/user/history.jsp").forward(req, resp);
	}
	private void doGetFavorites(HttpSession session,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user =(User)session.getAttribute(SessionAttr.CURRENT_USER);
		List<favorite> favorites =favoriteService.findByUserAndIsLiked(user.getUsername());
		List<Video> videos =new ArrayList<>();
		favorites.forEach(item -> videos.add(item.getVideo()));
		req.setAttribute("videos", videos);
		
		req.getRequestDispatcher("/views/user/favorites.jsp").forward(req, resp);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
