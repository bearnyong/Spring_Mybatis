package com.greedy.section02.aop.javaconfig.model.sevice;


import com.greedy.section02.aop.javaconfig.model.dto.OrderDTO;

public interface OrderService {

    /* 주문을 등록하는 용도의 서비스 메소드 */
    int registOrder(OrderDTO order);


}
