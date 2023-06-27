package com.greedy.section01.programmatic.model.dao;

import com.greedy.section01.programmatic.model.dto.OrderDTO;
import com.greedy.section01.programmatic.model.dto.OrderMenuDTO;

public interface OrderMapper {

    int insertOrder(OrderDTO order);
    int insertOrderMenu(OrderMenuDTO orderMenuDTO);

}
