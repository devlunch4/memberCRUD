package file.servlet;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import user.model.UserVo;
import user.service.UserService;
import user.service.UserServiceI;

//프로파일 불러오기를 위한 설정
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ProfileServlet.class);

	private UserServiceI userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// userid 파라미터를 이용하여
		// userService 객체를 통해 사용자의 사진 파일 이름을 획득
		// 파일 입출력을 통해 사진을 읽어들여 resp객체의 outputStream므으로 응답 생성
		String userid = req.getParameter("userid");

		UserVo userVo = userService.selectUser(userid);
		logger.debug("doGet진입 선택된 userVo값 :{}", userVo);
		String path = "";
		if (userVo.getRealfilename() == null) {
			path = getServletContext().getRealPath("/image/unknown.png");
		} else {
			path = "d:\\upload\\" + userVo.getRealfilename();
		}

		logger.debug("불러오는 path 값 : {}", path);

		FileInputStream fis = new FileInputStream(path);
		ServletOutputStream sos = resp.getOutputStream();
		byte[] buff = new byte[512];
		while (fis.read(buff) != -1) {
			sos.write(buff);
		}
		fis.close();
		sos.close();
	}
}
