package com.iydsj.sw;

import ch.qos.logback.classic.db.SQLBuilder;
import ch.qos.logback.classic.db.names.TableName;
import com.iydsj.server.common.db.dbtools.ParamsMap;
import com.iydsj.server.common.db.dbtools.SqlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
@Repository
public class UserDao {

    private String TABLE = "user";

    @Autowired
    protected NamedParameterJdbcOperations namedParameterJdbcTemplate;

    public User findUser(int id){
        SqlBuilder sqlBuilder = SqlBuilder.select(TABLE,"id,name");
        ParamsMap paramsMap = ParamsMap.create("id",id);
        try{
           return namedParameterJdbcTemplate.queryForObject(sqlBuilder.toString(), paramsMap.getParams(), new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int i) throws SQLException {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    return user;
                }
            });
        }catch (EmptyResultDataAccessException ex){
            return null;
        }
    }
}
