package db;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//마이바티스접속을 위한 유틸 경로 설정
public class MybatisUtil {

	// 로딩시 스태틱 으로 설정.
	static SqlSessionFactory sqlSessionFactory;

	// class 로딩시 실행되는 블럭
	static {
		try {
			String resource = "sql/config/mybatis/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
}
