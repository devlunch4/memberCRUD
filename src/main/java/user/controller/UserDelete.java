package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import user.service.UserService;
import user.service.UserServiceI;


//사용자 정보 삭제
@WebServlet("/userDelete")
public class UserDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(UserDelete.class);
	private UserServiceI userService = new UserService();

//DELETE users
//WHERE userid = "파라미터로 받은 사용자 아이디"
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("userDelete doPost() 진입");
		String userid = req.getParameter("userid");
		int deleteCnt = 0;
		try {
			deleteCnt = userService.deleteUser(userid);
		} catch (Exception e) {
			deleteCnt = -1;
		}

		if (deleteCnt == 1) {
			logger.debug("사용자 {} 삭제 완료", userid);
			resp.sendRedirect(req.getContextPath() + "/pagingUser");
		} else {
			resp.sendRedirect(req.getContextPath() + "/member?userid=" + userid);
		}

	}
}
