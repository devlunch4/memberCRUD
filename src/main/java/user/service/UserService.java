package user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.model.PageVo;
import common.model.PageVoSearch;
import user.model.UserVo;
import user.repository.UserDao;
import user.repository.UserDaoI;

public class UserService implements UserServiceI {
	private UserDaoI userDao = new UserDao();

	@Override
	public List<UserVo> selectAllUser() {
		return userDao.selectAllUser();
	}

	@Override
	public UserVo selectUser(String userid) {
		return userDao.selectUser(userid);
	}

	@Override
	public Map<String, Object> selectPagingUser(PageVo pageVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<UserVo> userList = userDao.selectPagingUser(pageVo);
		int userCnt = userDao.selectAllUserCnt();
		map.put("userList", userList);
		map.put("userCnt", userCnt);

		return map;
	}

	@Override
	public int modifyUser(UserVo userVo) {
		return userDao.modifyUser(userVo);
	}

	@Override
	public int insertUser(UserVo userVo) {
		return userDao.insertUser(userVo);
	}

	@Override
	public int insertUserx(UserVo userVo) {
		int insertUserres = 0;
		try {
			insertUserres = userDao.insertUser(userVo) == 1 ? 1 : 0;
			return insertUserres;
		} catch (Exception e) {
		}
		return insertUserres;
	}

	@Override
	public int deleteUser(String userid) {
		return userDao.deleteUser(userid);
	}

	// 아이디로 검색한 페이징
	@Override
	public Map<String, Object> idFindUser(PageVoSearch pageVoSearch) {
		Map<String, Object> map = new HashMap<>();

		List<UserVo> userList = userDao.idFineUserPaging(pageVoSearch);
		int userCnt = userDao.idFindUserCount(pageVoSearch.getSerachvalue());

		map.put("userList", userList);
		map.put("userCnt", userCnt);

		return map;
	}

	// 이름으로 검색한 페이징
	@Override
	public Map<String, Object> nameFindUser(PageVoSearch pageVoSearch) {
		Map<String, Object> map = new HashMap<>();

		List<UserVo> userList = userDao.nameFindUserPaging(pageVoSearch);
		int userCnt = userDao.nameFindUserCount(pageVoSearch.getSerachvalue());

		map.put("userList", userList);
		map.put("userCnt", userCnt);

		return map;
	}

	// 별명으로 검색한 페이징
	@Override
	public Map<String, Object> aliasFindUser(PageVoSearch pageVoSearch) {
		Map<String, Object> map = new HashMap<>();

		List<UserVo> userList = userDao.aliasFindUserPaging(pageVoSearch);
		int userCnt = userDao.aliasFindUserCount(pageVoSearch.getSerachvalue());

		map.put("userList", userList);
		map.put("userCnt", userCnt);

		return map;
	}

}
