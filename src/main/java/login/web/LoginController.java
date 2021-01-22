package login.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import user.model.UserVo;
import user.service.UserService;
import user.service.UserServiceI;

// web.xml에 설정하는 servlet, servlet-mapping을 어노테이션을 통해 설정하는 방법
@WebServlet("/loginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private UserServiceI userService = new UserService();

	// 요청 메소드와 관련없이 서블릿이 동작하게 하려면
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("doPost()");

		// !! JS-COOKIE@ 사용
		// 클라이언트가 서버로 요청을 보낼시 브라우저에 의해 같이 전송된 쿠키 정보 확인
//				Cookie[] cookies = req.getCookies();
//				for (Cookie cookie : cookies) {
//					logger.debug("cookie.getName : {}, cookie.getValue() : {}", cookie.getName(), cookie.getValue());
//					if (cookie.getName().equals("userid")) {
//						Cookie newServrCookie = new Cookie("newServrCookie", "testVlaue");
//						resp.addCookie(newServrCookie);
//					}
//				}
		/*
		 * // 사용자가 userid, pass 파라미터를 전송 했다는 가정으로 개발 // 하나의 파라미터 확인
		 * logger.debug("하나의 파라미터 확인");
		 * logger.debug("req.getParameter(\"userid\") : {}",
		 * req.getParameter("userid")); logger.debug("req.getParameter(\"pass\") : {}",
		 * req.getParameter("pass"));
		 * 
		 * // 복수개의 값을 갖는 파라미터 확인 logger.debug("복수개의 값을 갖는 파라미터 확인");
		 * logger.debug("req.getParameterValues(\"userid\") : ");
		 * 
		 * // 향상된 for 문사용 루프 for (String userid : req.getParameterValues("userid")) {
		 * logger.debug(userid); } // 요청에 담기 파라미터 이름을 확인
		 * logger.debug("요청에 담기 파라미터 이름을 확인");
		 * logger.debug("req.getParameterNames() : "); Enumeration<String> enumeration =
		 * req.getParameterNames(); while (enumeration.hasMoreElements()) {
		 * logger.debug(enumeration.nextElement()); }
		 * 
		 * // 요청에 담긴 파라미터를 관리하는 맵객체 확인 logger.debug("요청에 담긴 파라미터를 관리하는 맵객체 확인");
		 * logger.debug("req.getParameterMap() : "); // 맵 객체 생성 Map<String, String[]>
		 * map = req.getParameterMap(); // 맵 객체에 키 설정 Set<String> keys = map.keySet();
		 * // 반복자 호출 동일한 방법을 호출 Iterator<String> it = keys.iterator(); // 반복적으로 확인 및 출력
		 * while (it.hasNext()) { logger.debug("{}", map.get(it.next())); }
		 */

		String userid = req.getParameter("userid");
		String pass = req.getParameter("pass");
		UserVo user = userService.selectUser(userid);

		// 로그인 성공 ==> service를 통해 데이터베이스에 저장된 값과 일치할떄
		// session에 데이터 베이스를 조회한 사용자 정보를 (userVo)를 저장
		if (user != null && pass.equals(user.getPass())) {
			logger.debug("로그인 성공 >> 메인페이지 진입");

			HttpSession session = req.getSession();
			session.setAttribute("S_USER", user);
//			req.setAttribute("userid", userid);
//			req.setAttribute("pass", pass);
//			req.getRequestDispatcher("/pagingUser").forward(req, resp);
			resp.sendRedirect(req.getContextPath() + "/pagingUser");
		}
		// 로그인 실패
		else {
			logger.debug("로그인 실패 >> 메인페이지 불가");
		    resp.setContentType("text/html; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>alert('아이디/비밀번호를 확인하세요'); location.href='"+"/login.jsp"+"';</script>");
			out.close();
			//resp.sendRedirect(req.getContextPath() + "/login.jsp");
		}
	}
}
