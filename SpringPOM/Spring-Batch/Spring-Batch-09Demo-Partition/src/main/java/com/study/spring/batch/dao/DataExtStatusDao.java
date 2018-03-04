package com.study.spring.batch.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.study.spring.batch.domain.DataExtStatus;

@Repository
public class DataExtStatusDao {

    @Autowired
    private SqlSession sqlSession;
    
    @Transactional(isolation=Isolation.READ_COMMITTED)
    public DataExtStatus getTodayStatus() {
        return sqlSession.selectOne("dataExtStatusDao.getTodayStatus", new Object().hashCode());
    }
    @Transactional(isolation=Isolation.READ_COMMITTED)
    public boolean update(DataExtStatus dataStatus) {
        int result = sqlSession.update("dataExtStatusDao.update", dataStatus);
        return result == 1;
    }
}
