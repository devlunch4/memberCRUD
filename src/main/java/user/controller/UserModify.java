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

//사용자 정보 수정
@MultipartConfig
@WebServlet("/userModify")
public class UserModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(UserModify.class);
	private UserServiceI userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		1) userid parameter 확인
//		2) userService 객체를 이용하여 파라미터에 해당하는 사용자 정보 조회
//		3) request 객체에 2)번 조회된 값을 user란 속성으로 저장
//		4) webapp/user/user.jsp 화면 생성 위임
		logger.debug("ModifyUser doGet()진입완료");
		String userid = req.getParameter("userid");
		logger.debug("userid : {}", userid);
		UserVo user = userService.selectUser(userid);
		req.setAttribute("user", user);
		logger.debug("UserVo user 값 : {}, user.getAlias() : {}", user, user.getAlias());
		req.getRequestDispatcher("/memberupdate.jsp").forward(req, resp);
	}

	// 사용자 정보 수정
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("UserModify doPost 진입완료");

		// 파라미터를 읽기 전에 실행
		// servlet의 doPost메소드 마다 실행 필요 ==> Filter
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		// 값 가져오기
		String userid = req.getParameter("userid");
		String userNm = req.getParameter("userNm");
		String pass = req.getParameter("pass");

		String reg_dt = req.getParameter("reg_dt");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Date reg_dt_fm = null;
		try {
			reg_dt_fm = dateFormat.parse(reg_dt);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String userAlias = req.getParameter("userAlias");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		String zipcode = req.getParameter("zipcode");

		// 선언
		// String filename = "";
		// String realfilename = "";
		String filename = req.getParameter("filename");
		String realfilename = req.getParameter("realfilename");

//		사용자가 사진을 새롭게 전송을 했는지 여부 체크
//		1.사진을 안보낸 경우 >> 기존값 유지
//		2. 사진을 보낸경우 업로드시 생성된 filename,realfilename으로 변경
//		profile 가져오기
		Part profile = req.getPart("profile");
		
		// if문 시작
		if (profile.getSize() > 0) {
			filename = FileUtil.getFileName(profile.getHeader("Content-Disposition"));
			String fileExtension = FileUtil.getFileExtension(filename);
			realfilename = UUID.randomUUID().toString() + fileExtension;
			
			
			profile.write("d:\\upload\\" + realfilename);

			logger.debug("filename : {} fileExtension:{}", filename, fileExtension);
			logger.debug("사진 수정 저장 수행 완료");
		}

		UserVo userVo = new UserVo(userid, userNm, pass, reg_dt_fm, userAlias, addr1, addr2, zipcode, filename,
				realfilename);
		logger.debug("UserVo user 값 : {}, user.getAlias() : {}", userVo, userVo.getAlias());
		int updateCnt = userService.modifyUser(userVo);
		if (updateCnt == 1) {
			// 정상 수행시
			// 방법 2: resp.sendRedirect 사용
			logger.debug("사용자 {} 수정 완료", userid);
			resp.sendRedirect(req.getContextPath() + "/member?userid=" + userid);
		} else {
			// 비정상 수행시
			// 방법1 : doGet 으로 넘기기
			doGet(req, resp);
		}
	}
}