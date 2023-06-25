package com.greedy.section01.connection.javaconfig;

import com.greedy.section01.connection.javaconfig.config.ContextConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);
        System.out.println("전체 메뉴 조회하기");


        MenuService menuService = context.getBean("menuService", MenuService.class);

        List<MenuDTO> menuDTOList = menuService.selectMenuList();
        for (MenuDTO menuDTO : menuDTOList) {
            System.out.println(menuDTO);
        }

    }
}