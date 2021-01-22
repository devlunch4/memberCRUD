package user.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import user.model.UserVo;
import user.service.UserService;
import user.service.UserServiceI;
import util.FileUtil;

//사용자 정보 등록
@MultipartConfig
@WebServlet("/userRegist")
public class UserRegist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(UserRegist.class);
	private UserServiceI userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("userRegist doGet() 진입");
		req.setAttribute("notice", "이미 등록된 아이디가 있습니다.");
		req.getRequestDispatcher("/memberinsert.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("userRegist doPost() 진입");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		// 입력 값 가져오기
		String userid = req.getParameter("userId");
		String userNm = req.getParameter("userNm");
		String pass = req.getParameter("pass");
		
		// 날짜 설정
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		String reg_dt = req.getParameter("reg_dt");

		// Date reg_dt_fm = new Date(); 오늘날짜 호출법
		Date reg_dt_fm = null;
		try {
			reg_dt_fm = dateFormat.parse(req.getParameter("reg_dt"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		reg_dt_fm = new Date();

		String userAlias = req.getParameter("userAlias");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		String zipcode = req.getParameter("zipcode");

		// 사용자가 profile 업로드한경우
		// 1전송한 파일이름(filename
		// 2파일 확장자
		// 3서버에 저장할 파일이름(realfilename
		// 4서버에 지정된 공간에 저장
		Part profile = req.getPart("profile");

		String filename = "";
		String realFileName = "";
		if (profile.getSize() > 0) {
			filename = FileUtil.getFileName(profile.getHeader("Content-Disposition"));
			String fileExtension = FileUtil.getFileExtension(filename);
			// brown / bronw.png ?? 확장자 뒤의 "." 처리를 FileUtil.getFileExtension return 값에서 처리함
			realFileName = UUID.randomUUID().toString() + fileExtension;
			// 저장위치 지정
			profile.write("d:\\upload\\" + realFileName);
		}

		logger.debug("입력값들 중 id 확인 realFileName : {}", realFileName);
		logger.debug("날짜입력값: reg_dt: {}, reg_dt_fm:{}", reg_dt, reg_dt_fm);
		UserVo userVo = new UserVo(userid, userNm, pass, reg_dt_fm, userAlias, addr1, addr2, zipcode, filename,
				realFileName);

		if (userid == "" || userNm == "" || pass == "") {
			req.setAttribute("userVo", userVo);
			doGet(req, resp);
		} else {

//		try {
//			int insertUser = userService.insertUser(userVo);
//			if (insertUser == 1) {
//				// 정상수행
//				logger.debug("userRegist doPost() try 정상수행 >> /user?userid={} 이동",userid);
//				resp.sendRedirect(req.getContextPath() + "/user?userid=" + userid);
//			}
//		} catch (Exception e) {
//			logger.debug("userRegist doPost() try 예외발생 >> userRegist doPost() 재실행");
//			req.setAttribute("userVo", userVo);
//			doGet(req, resp);
//		}

			// ### SERVICE 객체를 사용하여 예외 처리
			int insertUser = userService.insertUserx(userVo);
			logger.debug("insertUser : {}", insertUser);
			if (insertUser == 1) {
				// 정상수행
				// logger.debug("userRegist doPost() insertUserx()값:1 정상수행 >> /user?userid={}
				// 이동", userid);
				// resp.sendRedirect(req.getContextPath() + "/user?userid=" + userid);
				logger.debug("userRegist doPost() insertUserx()값:1 정상수행 >> /pagingUser 이동 생성id:{}", userid);
				resp.sendRedirect(req.getContextPath() + "/pagingUser");
			} else {
				// 예외발생시 수행
				logger.debug("userRegist doPost() insertUserx()값:0 예외발생 >> userRegist doPost() 재실행");
				req.setAttribute("userVo", userVo);
				doGet(req, resp);
			}
		}
	}

}
