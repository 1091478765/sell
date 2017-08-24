package com.liulu.sell.repository;

import com.liulu.sell.dataobject.ProductCategory;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(ProductCategoryRepositoryTest.class);

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findone(){
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        logger.info(productCategory.toString());
    }

    @Test
    public void save(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("电器");
        productCategory.setCategoryType(3);
        productCategoryRepository.save(productCategory);
    }

    @Test
    @Transactional
    public void update(){
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        productCategory.setCategoryName("器1");
        productCategory.setCategoryType(9);
        productCategoryRepository.save(productCategory);
    }

    @Test
    public void findByCategoryTypeIn(){
        List list = Arrays.asList(2,3,4,5,6,7,8,9);
        List<ProductCategory> list2 = productCategoryRepository.findByCategoryTypeIn(list);
    }

}