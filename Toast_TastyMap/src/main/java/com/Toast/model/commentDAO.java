package com.Toast.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;
import com.Toast.db.SqlSessionManager;

public class commentDAO {

    SqlSessionFactory sqlSessionFactory = SqlSessionManager.getFactory();

    // 댓글 작성
    public int insertComment(commentDTO comment) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        int result = sqlSession.insert("insertComment", comment);
        sqlSession.close();
        return result;
    }

    // 특정 리뷰의 댓글 목록 조회
    public List<commentDTO> getCommentsByReview(int review_idx) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<commentDTO> result = sqlSession.selectList("getCommentsByReview", review_idx);
        sqlSession.close();
        return result;
    }
    
    // 특정 리뷰의 댓글의 프로필 이미지 조회
    public List<String> getCommentsMemImg(int review_idx) {
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	List<String> result = sqlSession.selectList("getCommentsMemImg", review_idx);
    	sqlSession.close();
    	return result;
    }

    // 댓글 삭제
    public int deleteComment(int comment_id) {
    	SqlSession sqlSession = sqlSessionFactory.openSession(true);
    	int row = sqlSession.delete("deleteComment", comment_id);
    	sqlSession.close();
    	return row;
    }

}
