package com.liulu.sell.repository;

import com.liulu.sell.dataobject.ProductCategory;
import com.liulu.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void save(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(UUID.randomUUID().toString().substring(16,32));
        productInfo.setCategoryType(1);
        productInfo.setProductDescription("这是一个皮蛋粥");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal(1));
        productInfo.setProductStatus(1);
        productInfo.setProductStock(100);
        productInfo.setProductIcon("123");
        productInfoRepository.save(productInfo);

    }

    @Test
    public void findByProductStatus() throws Exception {
       List<ProductInfo> list =  productInfoRepository.findByProductStatus(1);
        Assert.assertNotEquals(0,list.size());
    }

}