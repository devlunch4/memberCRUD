package user.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.model.PageVo;
import user.model.UserVo;
import user.service.UserService;
import user.service.UserServiceI;

//리스트 출력시 페이징 처리를 위한 서블릿
@WebServlet("/pagingUser")
public class PagingUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PagingUser.class);
	private UserServiceI userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// left.jsp /pagingUser?page=?=pageSize=?
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>pagingUser

		// doGet()메소드에서 page, pageSize 파라미터가 request 객체에 존재하지 않을때
		// page 1로, pageSize 5로 생각을 코드를 작성
		// 파라미터가 존재하면 해당 파라미터를 이용
		logger.debug("doGet() 진입완료");
		// 파라미터 설정
		String pageParam = req.getParameter("page");
		String pageSizeParam = req.getParameter("pagesize");
		String keyword = req.getParameter("keyword");
		String searchType = req.getParameter("searchType");
		logger.debug("초기 입력pageParam:{}, pageSizeParam:{}, keyword:{}, searchType:{}", pageParam, pageSizeParam,
				keyword, searchType);

		// 삼항연산자를 통한 null값 처리
		int page = pageParam == null ? 1 : Integer.parseInt(pageParam);
		int pagesize = pageSizeParam == null ? 5 : Integer.parseInt(pageSizeParam);
		
		// 페이징 객체 선언
		PageVo pagevo = new PageVo(page, pagesize);

		// 조회된 페이지 정보를 맵에 설정
		Map<String, Object> map = userService.selectPagingUser(pagevo);
		
		// 서비스에서 생성되어 현 클래스내 설정된 맵의 키값인 "userList" 값을 List화
		List<UserVo> userList = (List<UserVo>) map.get("userList");
		
		// 서비스에서 생성되어 현 클래스내 설정된 맵의 키값인 "userCnt"를 값을 int화
		int userCnt = (int) map.get("userCnt");
		
		//위에서 구한 값을 통한 연산 및 올림
		int pagination = (int) Math.ceil((double) userCnt / pagesize);
		
		logger.debug("userCnt 값 : {}, pagination 값 : {}", userCnt, pagination);
		
		//req 설정
		req.setAttribute("userList", userList);
		req.setAttribute("pagination", pagination);
		req.setAttribute("pagevo", pagevo);
		logger.debug("In /pagingUser doGet() >> Forward : pagingUser.jsp //");
		
		//송신
		req.getRequestDispatcher("/memberpage.jsp").forward(req, resp);
	}
}
