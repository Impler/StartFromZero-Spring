package com.study.spring.batch.remote.partition.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.spring.batch.remote.partition.domain.dto.Pagination;

@Repository
public class UserDao {

    @Autowired
    private SqlSession sqlSession;
    
    public Pagination queryDataRange() {
        return sqlSession.selectOne("userDao.queryDataRange");
    }
    
    public Pagination queryBatchDataRange(Pagination page) {
        Pagination newPage = sqlSession.selectOne("userDao.queryBatchDataRange", page);
        newPage.setStartId(page.getStartId());
        return newPage;
    }

}
