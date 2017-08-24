package com.liulu.sell.service.impl;

import com.liulu.sell.dataobject.ProductCategory;
import com.liulu.sell.service.ProductCategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(ProductCategoryServiceImplTest.class);

    @Autowired
    private ProductCategoryService productCategoryService;


    @Test
    public void findOne() {
        ProductCategory productCategory = productCategoryService.findOne(1);
        logger.info(productCategory.toString());
     //   Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }

    @Test
    public void findAll() {
        productCategoryService.findAll();
    }

    @Test
    public void findByCategotyTypeIn() {
    }

    @Test
    public void save() {
    }

}