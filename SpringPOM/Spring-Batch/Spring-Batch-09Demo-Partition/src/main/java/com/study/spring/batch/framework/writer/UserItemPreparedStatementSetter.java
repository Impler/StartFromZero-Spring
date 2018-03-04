package com.study.spring.batch.framework.writer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import com.study.spring.batch.domain.User;

public class UserItemPreparedStatementSetter implements ItemPreparedStatementSetter<User> {

    @Override
    public void setValues(User item, PreparedStatement ps) throws SQLException {
        ps.setString(1, item.getResultCode());
        ps.setString(2, item.getResultContent());
        ps.setInt(3, item.getId());
    }

}
