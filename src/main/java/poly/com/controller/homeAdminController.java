package poly.com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import poly.com.constant.SessionAttr;
import poly.com.dto.videoLikeInfo;
import poly.com.entity.User;
import poly.com.service.StatsService;
import poly.com.service.StatsServiceService;
import poly.com.service.UserService;
import poly.com.service.UserServiceImpl;

/**
 * Servlet implementation class homeAdminController
 */
@WebServlet(urlPatterns = { "/admin","/admin/favorites"},name="/homeAdminController")
public class homeAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private StatsService statsService = new StatsServiceService();
    private UserService userService = new UserServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public homeAdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session =req.getSession();
		User currentUser =(User) session.getAttribute(SessionAttr.CURRENT_USER);
		
		if (currentUser!= null && currentUser.isAdmin() == Boolean.TRUE) {
			String path = req.getServletPath();
			switch (path) {
			case "/admin": {
				doGetHome(req, resp);
				break;
			}
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + path);
			}
			
			
		}else {
			resp.sendRedirect("index");
		}
		
	}
	private void doGetHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<videoLikeInfo> videos = statsService.finVideoLikeInfos();
		req.setAttribute("videos", videos); 
		req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
