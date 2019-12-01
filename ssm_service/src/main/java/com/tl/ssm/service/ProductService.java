package com.tl.ssm.service;

import com.tl.ssm.domain.Product;

import java.util.List;


public interface ProductService {
    //查询所有的产品信息   分页查询
    public List<Product> findAll(int page,int pageSize) throws Exception;

    //查询所有
    public List<Product> findAll() throws Exception;

    //添加产品信息
    void save(Product product) throws Exception;

    /*//查询所有的产品信息   产品选择操作
    public List<Product> selectFindAll() throws Exception;*/

    //根据id查询产品信息
    Product findById(Integer id) throws Exception;
}
