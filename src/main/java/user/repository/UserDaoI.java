package user.repository;

import java.util.List;

import common.model.PageVo;
import common.model.PageVoSearch;
import user.model.UserVo;

public interface UserDaoI {

	// 전체 사용자 정보 조회
	List<UserVo> selectAllUser();

	// userid에 해당하는 사용자 한명의 정보 조회
	UserVo selectUser(String userid);

	// 페이지 처리
	List<UserVo> selectPagingUser(PageVo pageVo);

	// 사용자 전체수 조회
	int selectAllUserCnt();

	// 사용자 정보 수정
	int modifyUser(UserVo userVo);

	// 사용자 정보 추가
	int insertUser(UserVo userVo);

	// 사용자 삭제
	int deleteUser(String userid);

	// 검색
	// 검색
	// 검색
	// 아이디로 검색
	List<UserVo> idFindUser(String userid);

	List<UserVo> idFineUserPaging(PageVoSearch pageVoSearch);

	int idFindUserCount(String userid);

	// 이름으로 검색
	List<UserVo> nameFindUser(String usernm);

	List<UserVo> nameFindUserPaging(PageVoSearch pageVoSearch);

	int nameFindUserCount(String usernm);

	// 별명으로 검색
	List<UserVo> aliasFindUser(String alias);

	List<UserVo> aliasFindUserPaging(PageVoSearch pageVoSearch);

	int aliasFindUserCount(String alias);
}
