package com.greedy.section02.aop.javaconfig.run;

import com.greedy.section02.aop.javaconfig.model.dto.OrderDTO;
import com.greedy.section02.aop.javaconfig.model.dto.OrderMenuDTO;
import com.greedy.section02.aop.javaconfig.model.sevice.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] agrs){

        /* 프로그래밍 레벨에서 트랜잭션 처리하는 방법을 알아본다.
         * 기존에 마이바티스를 이요할 때는 sqlSession과 Commit(), Rollback() 메소드를 이용하였다.
         * 하지만 스프링과 연동한 환경에서 이 메소드를 사용하려면 unsupportedOperationException 예외가 발생한다.
         * 스프링과 연동한 환경에서 수동으로 트랜잭션을 제어하고자 한다면 platformTransactionManager를 사용해야 한다.*/

        // xml 설정을 불러온다.
        ApplicationContext context =
                new GenericXmlApplicationContext("com/greedy/section02/aop/javaconfig/config/spring-context.xml");

        // bean 등록 확인하기
        String[] bean = context.getBeanDefinitionNames();
        for (String name : bean) {
            System.out.println("context : " + name);
        }

        OrderService orderService = context.getBean("orderService", OrderService.class);

        Scanner sc = new Scanner(System.in);

        List<OrderMenuDTO> ordermenulist = new ArrayList<>();

        orderMenu:
        do {
            System.out.println("=========== 트랜잭션 레스토랑 음식 주문 서비스 ===========");
            System.out.print("어떤 메뉴를 주문하시겠습니까? ( 메뉴코드입력 ) : ");
            int menuCode = sc.nextInt();
            System.out.print("주문 수량을 입력해 주세요 : ");
            int amount = sc.nextInt();
            System.out.print("다른 메뉴를 추가로 주문하시겠습니까? (Y/N) : ");
            sc.nextLine();
            char contineYN = sc.nextLine().toUpperCase().charAt(0); //toUpperCase : 대문자로 변경, charAt : 몇 번째 문자를?

            OrderMenuDTO orderMenu = new OrderMenuDTO();
            orderMenu.setMenuCode(menuCode);
            orderMenu.setAmount(amount);
            ordermenulist.add(orderMenu);

            switch (contineYN) {
                case 'Y' : break;
                default : break orderMenu;
            }
        } while(true);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        OrderDTO order = new OrderDTO();

        order.setDate(dateFormat.format(new java.util.Date(System.currentTimeMillis())));
        order.setTime(timeFormat.format(new java.util.Date(System.currentTimeMillis())));
        order.setMenuList(ordermenulist);

        System.out.println(order);

        int result = orderService.registOrder(order);
        if(result > 0) {
            System.out.println("메뉴 주문에 성공하였습니다.");
        } else {
            System.out.println("메뉴 주문에 실패하였습니다.");
        }

    }

}
