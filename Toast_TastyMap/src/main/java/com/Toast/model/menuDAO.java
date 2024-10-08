package com.Toast.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.Toast.db.SqlSessionManager;

public class menuDAO {
	SqlSessionFactory sqlSessionFactory = SqlSessionManager.getFactory();
	
	// 식당 전체 메뉴 조회
	public List<menuDTO> getMenuByIdx(int place_idx) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<menuDTO> menuList = sqlSession.selectList("getMenuByIdx", place_idx);
		sqlSession.close();
		return menuList;
	}
	
}
