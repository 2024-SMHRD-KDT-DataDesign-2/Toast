package com.Toast.model;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.Toast.db.SqlSessionManager;

public class reviewDAO {
	SqlSessionFactory sqlSessionFactory = SqlSessionManager.getFactory();

    // 리뷰 추가
    public int insertReview(reviewDTO dto) {
        SqlSession session = sqlSessionFactory.openSession(true);
        int row = session.insert("insertReview", dto);
        session.close();
        return row;
    }
    
    // 리뷰 수정
    public int updateReview(reviewDTO dto) {
    	SqlSession session = sqlSessionFactory.openSession(true);
        int row = session.update("updateReview", dto);
        session.close();
        return row;
    }

    // 특정 회원의 리뷰 리스트 조회
    public List<reviewDTO> getReviewsByMember(String mem_id) {
        SqlSession session = sqlSessionFactory.openSession();
        List<reviewDTO> reviewList = session.selectList("getReviewsByMember", mem_id);
        session.close();
        return reviewList;
    }
    
    // 방문 식당: 식당별 회원의 방문 횟수
    public int getVisitedTimes(reviewDTO dto) {
    	SqlSession session = sqlSessionFactory.openSession();
        int result = session.selectOne("getVisitedTimes", dto);
        session.close();
        return result;
    }
    
    // 방문 식당: 식당별 회원의 평균 평점
    public double getVisitedRatings(reviewDTO dto) {
        SqlSession session = sqlSessionFactory.openSession();
        Number result = session.selectOne("getVisitedRatings", dto);
        session.close();
        
        if (result != null) {
            return result.doubleValue();
        } else {
            return 0.0;
        }
    }
    
    // 식당idx를 통해 식당정보 조회
    public List<String> getPlaceNameByIdx(String mem_id) {
    	SqlSession session = sqlSessionFactory.openSession();
    	List<String> placeName = session.selectList("getPlaceNameByIdx", mem_id);
    	return placeName;
    }
    
    // 식당의 리뷰 개수 조회
    public int countReviewByIdx(int place_idx) {
    	SqlSession session = sqlSessionFactory.openSession();
    	int countReview = session.selectOne("countReviewByIdx", place_idx);
    	return countReview;
    }
    
    // 내가 작성한 리뷰 개수 조회
    public int countReviewByMember(String mem_id) {
    	SqlSession session = sqlSessionFactory.openSession();
    	int result = session.selectOne("countReviewByMember", mem_id);
    	return result;
    }
    
    // 식당 전체 리뷰 조회
    public List<reviewDTO> getPlaceReviews(int place_idx) {
    	SqlSession session = sqlSessionFactory.openSession();
    	List<reviewDTO> result = session.selectList("getPlaceReviews", place_idx);
    	return result;
    }
    
    // 리뷰 삭제
    public int deleteReview(reviewDTO dto) {
        SqlSession session = sqlSessionFactory.openSession(true);
        int row = session.delete("deleteReview", dto);
        session.close();
        return row;
    }

    // 리뷰 상세 조회
    public reviewDTO getReviewById(int review_idx) {
        SqlSession session = sqlSessionFactory.openSession();
        reviewDTO review = session.selectOne("getReviewById", review_idx);
        session.close();
        return review;
    }
    
    // 리뷰 idx를 통한 식당명 조회
    public String getPlaceNameByReviewIdx(int review_idx) {
    	SqlSession session = sqlSessionFactory.openSession();
    	String result = session.selectOne("getPlaceNameByReviewIdx", review_idx);
    	session.close();
    	return result;
    }
    
    // 리뷰 idx를 통한 작성자 닉네임 조회
    public String getMemNickByReviewIdx(int review_idx) {
    	SqlSession session = sqlSessionFactory.openSession();
    	String result = session.selectOne("getMemNickByReviewIdx", review_idx);
    	session.close();
    	return result;
    }
    
    // 식당의 평균 평점
    public double getAvgRatings(int place_idx) {
    	SqlSession session = sqlSessionFactory.openSession();
    	Double result = session.selectOne("getAvgRatings", place_idx);
    	session.close();
    	return result != null ? result : 0.0;
    }
}
