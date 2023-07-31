package poly.com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import poly.com.constant.SessionAttr;
import poly.com.entity.User;
import poly.com.service.UserService;
import poly.com.service.UserServiceImpl;


/**
 * Servlet implementation class userController
 */
@WebServlet(urlPatterns = {"/login","/logout","/register"})
public class userController extends HttpServlet {
	private static final long serialVersionUID = 2L;
	private UserService userService =new UserServiceImpl();
//	private emailService emailService= new emailServiecImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userController() {
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
			case "/login": {
				doGetLogin(req, resp);
				break;
			}
			case "/register": {
				doGetRegister(req, resp);
				break;
			}
			case "/logout": {
				doGetLogout(session,req, resp);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + path);
			}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session =req.getSession();
		String path = req.getServletPath();
		switch (path) {
		case "/login": {
			doPostLogin(session,req, resp);
			break;
		}
		case "/register": {
			doPostRegister(session,req, resp);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
	}
	
	private void doPostLogin(HttpSession session,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username =req.getParameter("username");
		String password =req.getParameter("password");
		
		User user =userService.login(username, password);
		if (user != null) {
			session.setAttribute(SessionAttr.CURRENT_USER, user);
			resp.sendRedirect("index");
		}else {
			resp.sendRedirect("login");
		}
	}
	
	private void doPostRegister(HttpSession session,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username =req.getParameter("username");
		String password =req.getParameter("password");
		String email =req.getParameter("email");
		User user =userService.register(username, password, email);
		if (user != null) {

			session.setAttribute(SessionAttr.CURRENT_USER, user);
			resp.sendRedirect("index");
		}else {
			resp.sendRedirect("register");
		}
	}
	
	private void doGetLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		req.getRequestDispatcher("/views/user/login.jsp").forward(req, resp);
	}
	private void doGetRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		req.getRequestDispatcher("/views/user/register.jsp").forward(req, resp);
	}
	private void doGetLogout(HttpSession session,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session.removeAttribute(SessionAttr.CURRENT_USER);
		resp.sendRedirect("index");
	}
	

}
