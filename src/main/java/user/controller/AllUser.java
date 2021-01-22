package user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import user.model.UserVo;
import user.service.UserService;
import user.service.UserServiceI;

//전체 회원 출력을 위한 서블릿
@WebServlet("/allUser")
public class AllUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(AllUser.class);
	private UserServiceI userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<UserVo> userList = userService.selectAllUser();
		req.setAttribute("userList", userList);
		req.getRequestDispatcher("/memberlist.jsp").forward(req, resp);
		logger.debug("In /allUser doGet() >> Forward : memberlist.jsp");
	}
}
