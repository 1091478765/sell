package com.liulu.sell.service;

import com.liulu.sell.dataobject.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    ProductCategory findOne(Integer id);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategotyTypeIn(List<Integer> list);

    ProductCategory save(ProductCategory productCategory );


}
