package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//사용자 정보 취소시 입력값 그대로 재출력
@MultipartConfig
@WebServlet("/userRegistC")
public class UserRegistCancel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(UserRegistCancel.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("userRegist doGet() 진입");
		req.getRequestDispatcher("/memberinsert.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("userRegist doPost() 진입");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		// 입력 값 가져오기
		// 취소 처리

		// 예외발생시 수행
		logger.debug("userRegist doPost() insertUserx()값:0 예외발생 >> userRegist doPost() 재실행");
		req.setAttribute("userVo", null);
		doGet(req, resp);
	}
}
