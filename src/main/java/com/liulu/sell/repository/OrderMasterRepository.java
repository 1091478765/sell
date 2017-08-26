package com.liulu.sell.repository;

import com.liulu.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 刘璐
 * 2017/8/25 21:57
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

    Page<OrderMaster> findByBuyerOpenId(String buyerOpenid, Pageable pageable);
}
