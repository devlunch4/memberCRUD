package user.controller;

import java.io.IOException;

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

// 사용자 정보 조회
@WebServlet("/member")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private UserServiceI userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		1) userid parameter 확인
//		2) userService 객체를 이용하여 파라미터에 해당하는 사용자 정보 조회
//		3) request 객체에 2)번 조회된 값을 user란 속성으로 저장
//		4) webapp/user/user.jsp 화면 생성 위임

		logger.debug("doGet() 진입완료");
		String userid = req.getParameter("userid");
		logger.debug("userid : {}", userid);
		UserVo user = userService.selectUser(userid);
		req.setAttribute("user", user);
		logger.debug("UserVo user 값 : {}, user.getAlias() : {}", user, user.getAlias());
		req.getRequestDispatcher("memberform.jsp").forward(req, resp);
	}
}
