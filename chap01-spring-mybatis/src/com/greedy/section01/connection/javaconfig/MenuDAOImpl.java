package com.greedy.section01.connection.javaconfig;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("menuDAO")
public class MenuDAOImpl implements MenuDAO {

    /*마이바티스 라이브러리 추가해야함...?*/
    public List<MenuDTO> selectMenuList(SqlSessionTemplate sqlSession){
        return sqlSession.selectList("MenuMapper.selectMenuList");
    }


}
