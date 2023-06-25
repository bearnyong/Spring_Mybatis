package com.greedy.section01.connection.javaconfig;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    private final MenuDAO menuDAO;
    private final SqlSessionTemplate sqlSession;

    /*의존성 주입*/
    @Autowired
    public MenuServiceImpl(MenuDAO menuDAO, SqlSessionTemplate sqlSession) {
        this.menuDAO = menuDAO;
        this.sqlSession = sqlSession;
    }

    /*MenuMapper의 "selectMenuList"불러오기...?*/
    @Override
    public List<MenuDTO> selectMenuList() {
        return menuDAO.selectMenuList(sqlSession);
    }

}
