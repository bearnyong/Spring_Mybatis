package com.greedy.section01.connection.javaconfig;

import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public interface MenuDAO {
    public List<MenuDTO> selectMenuList(SqlSessionTemplate sqlSession);

}
