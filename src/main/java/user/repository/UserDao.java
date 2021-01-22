package user.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import common.model.PageVo;
import common.model.PageVoSearch;
import db.MybatisUtil;
import user.model.UserVo;

public class UserDao implements UserDaoI {

	//모든 사용자 리스트화
	@Override
	public List<UserVo> selectAllUser() {
		SqlSession sqlSession = MybatisUtil.getSqlSession();

		// >> users.selectAllUser >> 네임스페이스.아이디
		List<UserVo> userList = sqlSession.selectList("users.selectAllUser");

		// 자원 해제
		sqlSession.close();

		// dao >> service >> 화면
		return userList;
	}
	
	// 단일 사용자 조회
	@Override
	public UserVo selectUser(String userid) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		UserVo user = sqlSession.selectOne("users.selectUser", userid);
		sqlSession.close();

		return user;
	}

	//페이징 선택에 따른 사용자 조회
	@Override
	public List<UserVo> selectPagingUser(PageVo pagevo) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		List<UserVo> pageList = sqlSession.selectList("users.selectPagingUser", pagevo);
		sqlSession.close();

		return pageList;
	}

	// 모든 사용자 수 조회
	@Override
	public int selectAllUserCnt() {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int userCnt = sqlSession.selectOne("users.selectAllUserCnt");
		// SELECT COUNT(*) FROM users
		sqlSession.close();

		return userCnt;
	}

	//사용자 수정
	@Override
	public int modifyUser(UserVo userVo) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int updateCnt = sqlSession.update("users.modifyUser", userVo);
		if (updateCnt == 1) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return updateCnt;
	}
	
	//사용자 추가
	@Override
	public int insertUser(UserVo userVo) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int insertCnt = sqlSession.update("users.insertUser", userVo);
		if (insertCnt == 1) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return insertCnt;
	}

	//사용자 삭제
	@Override
	public int deleteUser(String userid) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int deleteCnt = sqlSession.delete("users.deleteUser", userid);
		if (deleteCnt == 1) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return deleteCnt;
	}

	// 아이디로 검색
	@Override
	public List<UserVo> idFindUser(String userid) {
		SqlSession session = MybatisUtil.getSqlSession();
		List<UserVo> list = session.selectList("users.idFindUser", userid);
		session.close();
		return list;
	}

	// 아이디로 검색 한 회원 수
	@Override
	public int idFindUserCount(String userid) {
		SqlSession session = MybatisUtil.getSqlSession();
		int cnt = session.selectOne("users.idFindUserCount", userid);
		session.close();
		return cnt;
	}

	// 아이디로 검색한 회원 페이징
	@Override
	public List<UserVo> idFineUserPaging(PageVoSearch pageVoSearch) {
		SqlSession session = MybatisUtil.getSqlSession();
		List<UserVo> userList = session.selectList("users.idFineUserPaging", pageVoSearch);
		session.close();
		return userList;
	}

	// 이름으로 검색
	@Override
	public List<UserVo> nameFindUser(String usernm) {
		SqlSession session = MybatisUtil.getSqlSession();
		List<UserVo> list = session.selectList("users.nameFindUser", usernm);
		session.close();
		return list;
	}

	// 이름으로 검색 한 회원 수
	@Override
	public int nameFindUserCount(String usernm) {
		SqlSession session = MybatisUtil.getSqlSession();
		int cnt = session.selectOne("users.nameFindUserCount", usernm);
		session.close();
		return cnt;
	}

	// 이름으로 검색한 회원 페이징
	@Override
	public List<UserVo> nameFindUserPaging(PageVoSearch pageVoSearch) {
		SqlSession session = MybatisUtil.getSqlSession();
		List<UserVo> userList = session.selectList("users.nameFindUserPaging", pageVoSearch);
		session.close();
		return userList;
	}

	// 별명으로 검색
	@Override
	public List<UserVo> aliasFindUser(String alias) {
		SqlSession session = MybatisUtil.getSqlSession();
		List<UserVo> list = session.selectList("users.aliasFindUser", alias);
		session.close();
		return list;
	}

	// 별명으로 검색한 회원 수
	@Override
	public int aliasFindUserCount(String alias) {
		SqlSession session = MybatisUtil.getSqlSession();
		int cnt = session.selectOne("users.aliasFindUserCount", alias);
		session.close();
		return cnt;
	}

	// 별명으로 검색한 회원 페이징
	@Override
	public List<UserVo> aliasFindUserPaging(PageVoSearch pageVoSearch) {
		SqlSession session = MybatisUtil.getSqlSession();
		List<UserVo> userList = session.selectList("users.aliasFindUserPaging", pageVoSearch);
		session.close();
		return userList;
	}

}
