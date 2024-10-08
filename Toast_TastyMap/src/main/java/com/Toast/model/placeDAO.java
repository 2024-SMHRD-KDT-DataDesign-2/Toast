package com.Toast.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.Toast.db.SqlSessionManager;

public class placeDAO {
    SqlSessionFactory sqlSessionFactory = SqlSessionManager.getFactory();

    // 전체 음식점 목록
    public List<placeDTO> getPlaces() {
    	SqlSession sqlSession = sqlSessionFactory.openSession();
        List<placeDTO> placeList = (List) sqlSession.selectList("getPlaces");
        sqlSession.close();
        return placeList;
    }
    
    // 음식점 목록 필터링
    public ArrayList<placeDTO> filtering(filteringDTO dto) {
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	ArrayList<placeDTO> placeList = (ArrayList) sqlSession.selectList("filtering", dto);
    	sqlSession.close();
    	return placeList;
    }
    
    // 인덱스 번호로 식당 찾기
    public placeDTO getPlaceByIdx(int idx) {
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	placeDTO dto = sqlSession.selectOne("getPlaceByIdx", idx);
    	sqlSession.close();
    	return dto;
    }
    
    // 영업 여부
    public String isOpen(int idx) {
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	String result = sqlSession.selectOne("isOpen", idx);
    	sqlSession.close();
    	return result;
    }
    
    // 종료 시간
    public String closeTime(int idx) {
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	String result = sqlSession.selectOne("closeTime", idx);
    	sqlSession.close();
    	return result;
    }
    
    // 식당 idx를 통해 방문 식당 조회
    public List<placeDTO> getVisitedPlaceByIdx(String id) {
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	List<placeDTO> dto = sqlSession.selectList("getVisitedPlaceByIdx",id);
    	return dto;
    }
}