package com.rookie.bigdata.dao;

import com.rookie.bigdata.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author rookie
 * @version 1.0
 * @date 2020/3/31 7:38
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addUser(User user){
        int update = jdbcTemplate.update("insert into user(id,name,address)values (?,?,?)", user.getId(), user.getName(), user.getAddress());
        return update;
    }

    public int updateUser(User user){

        return 0;
    }


}
