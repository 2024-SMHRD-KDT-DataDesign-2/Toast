package com.Toast.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.Toast.db.SqlSessionManager;

public class memberDAO {

	SqlSessionFactory sqlSessionFactory = SqlSessionManager.getFactory();

	// 회원가입
	public int join(memberDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		int cnt = sqlSession.insert("join", dto);
		sqlSession.close();
		return cnt;
	}

	// mem_id 중복 체크
	public boolean isIdDuplicated(String mem_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int count = sqlSession.selectOne("checkDuplicateId", mem_id);
		sqlSession.close();
		return count > 0;
	}

	// 로그인
	public memberDTO login(memberDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		dto = sqlSession.selectOne("login", dto);
		sqlSession.close();
		return dto;
	}

	// Pw 수정
	public int updatePw(memberDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		int cnt = sqlSession.update("updatePw", dto);
		sqlSession.close();
		return cnt;
	}

	// Img 수정
	public int updateImg(memberDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		int cnt = sqlSession.update("updateImg", dto);
		sqlSession.close();
		return cnt;
	}

	// Nick 수정
	public int updateNick(memberDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		int cnt = sqlSession.update("updateNick", dto);
		sqlSession.close();
		return cnt;
	}
	
	// grade 자동 업데이트
	public int updateGrage(memberDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		int cnt = sqlSession.update("updateGrade", dto);
		sqlSession.close();
		return cnt;
	}
	
	// 현재 grade
	public String memGrade(memberDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		String grade = sqlSession.selectOne("memGrade", dto);
		sqlSession.close();
		return grade;
	}

}
