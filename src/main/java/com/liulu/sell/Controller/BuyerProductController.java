package com.liulu.sell.Controller;

import com.liulu.sell.Enums.ResultEnum;
import com.liulu.sell.ResultVO.ProductInfoVO;
import com.liulu.sell.ResultVO.ProductVO;
import com.liulu.sell.ResultVO.ResultBody;
import com.liulu.sell.dataobject.ProductCategory;
import com.liulu.sell.dataobject.ProductInfo;
import com.liulu.sell.service.ProductCategoryService;
import com.liulu.sell.service.ProductInfoService;
import com.liulu.sell.util.ResultUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 刘璐
 * 2017/8/24
 */

@RestController
@RequestMapping(value = "/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;



    @GetMapping("/list")
    public ResultBody list(){

        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        List<Integer> categotyTypeList = new ArrayList<Integer>();

        for (ProductInfo productInfo : productInfoList){
            categotyTypeList.add(productInfo.getCategoryType());
        }
        List<ProductCategory> productCategories = productCategoryService.findByCategotyTypeIn(categotyTypeList);
        ResultBody body = new ResultBody();
        List<ProductVO> productVOS = new ArrayList<>();
        for(ProductCategory category : productCategories){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(category.getCategoryType());
            productVO.setCategoryName(category.getCategoryName());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList){
                if (productInfo.getCategoryType().equals(productVO.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOS.add(productVO);
        }
        return ResultUtils.getSuccessResult(ResultEnum.SUCCESS,productVOS);
    }
}
