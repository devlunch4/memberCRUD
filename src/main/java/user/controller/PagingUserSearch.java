package user.controller;

import java.io.IOException;
import java.util.HashMap;
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
import common.model.PageVoSearch;
import user.model.UserVo;
import user.service.UserService;
import user.service.UserServiceI;

//검색시에 따른 페이지 출력 
//기존것과 통합을 해도되나 연습삼아 새로 생성
@WebServlet("/pagingUserSearch")
public class PagingUserSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PagingUserSearch.class);
	private UserServiceI userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// frm2 그리고 해당 검색 버튼을 클릭후 객체 설정 및 확인
		logger.debug("pagingUserSearch doGet() 진입완료");
		String pageParam = req.getParameter("page");
		String pageSizeParam = req.getParameter("pagesize");
		String keyword = req.getParameter("keyword");
		String searchType = req.getParameter("searchType");
		logger.debug("초기 입력pageParam:{}, pageSizeParam:{}, keyword:{}, searchType:{}, page:{}", pageParam, pageSizeParam,
				keyword, searchType,pageParam);

		// 페이지 로딩시 초기 기본값 1페이지 및 1페이지당의 5개씩 출력
		int page = pageParam == null ? 1 : Integer.parseInt(pageParam);
		int pagesize = pageSizeParam == null ? 5 : Integer.parseInt(pageSizeParam);
		logger.debug("null 처리 입력page:{}, pagesize:{}", page, pagesize);
		// 기존 pageVo 객체 선언
		PageVo pagevo = new PageVo(page, pagesize);
		// 검색시 사용될 pageVoSearch 선언
		PageVoSearch pageVoSearch = new PageVoSearch(page, pagesize, keyword);
		// 기존 사용한 map 선언
		// Map<String, Object> map = userService.selectPagingUser(pagevo);

		// 검색시을 사용될 값 설정을 위한 map 설정
		Map<String, Object> map = new HashMap<String, Object>();
		// searchType 값에 따른 검색 메소드 실행 설정
		if (searchType == null || searchType.equals("")) {
			// 아무 값이 없는경우
			map = userService.selectPagingUser(pagevo);
		} else if (searchType.equals("i")) {
			//아이디로 검색
			pageVoSearch.setSerachvalue("%" + keyword + "%");
			map = userService.idFindUser(pageVoSearch);
		} else if (searchType.equals("n")) {
			//이름으로 검색
			pageVoSearch.setSerachvalue("%" + keyword + "%");
			map = userService.nameFindUser(pageVoSearch);
		} else if (searchType.equals("a")) {
			//별명으로 검색
			pageVoSearch.setSerachvalue("%" + keyword + "%");
			map = userService.aliasFindUser(pageVoSearch);
		} else {
			map = userService.selectPagingUser(pagevo);
		}

		List<UserVo> userList = (List<UserVo>) map.get("userList");
		int userCnt = (int) map.get("userCnt");
		int pagination = (int) Math.ceil((double) userCnt / pagesize);
		logger.debug("userCnt 값 : {}, pagination 값 : {}", userCnt, pagination);
		req.setAttribute("userList", userList);
		req.setAttribute("keyword", keyword);
		req.setAttribute("searchType", searchType);
		req.setAttribute("pagination", pagination);
		req.setAttribute("pagevo", pagevo);
		logger.debug("In /pagingUserSearch doGet() >> Forward : memberpageSearch.jsp //");
		req.getRequestDispatcher("/memberpageSearch.jsp").forward(req, resp);

	}

}
