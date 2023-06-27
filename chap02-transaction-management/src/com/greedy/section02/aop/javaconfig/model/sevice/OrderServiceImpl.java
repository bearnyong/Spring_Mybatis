package com.greedy.section02.aop.javaconfig.model.sevice;

import com.greedy.section02.aop.javaconfig.model.dao.OrderMapper;
import com.greedy.section02.aop.javaconfig.model.dto.OrderDTO;
import com.greedy.section02.aop.javaconfig.model.dto.OrderMenuDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.SQLException;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private SqlSessionTemplate sqlSession;
    private DataSourceTransactionManager transactionManager;


    @Autowired
    public OrderServiceImpl(SqlSessionTemplate sqlSession, DataSourceTransactionManager transactionManager) {
        this.sqlSession = sqlSession;
        this.transactionManager = transactionManager;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = {Exception.class})
    public int registOrder(OrderDTO order) {
        /**/
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        int orderResult = orderMapper.insertOrder(order); /*성공*/
        List<OrderMenuDTO> orderMenuDTOList = order.getMenuList();

        int orderMenuResult = 0;
        for (OrderMenuDTO orderMenu : orderMenuDTOList) {
            orderMenuResult += orderMapper.insertOrderMenu(orderMenu);
        }

        int result = 0;
        if(orderResult>0 && orderMenuResult==orderMenuDTOList.size()) {
            result = 1;
        }
        return result;
    }

}
