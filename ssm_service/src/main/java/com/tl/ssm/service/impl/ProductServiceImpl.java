package com.tl.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.tl.ssm.dao.ProductDao;
import com.tl.ssm.domain.Product;
import com.tl.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
    服务层
 */
@Service
@Transactional //事务管理
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    //查询所有产品新  分页查询
    @Override
    public List<Product> findAll(int page,int pageSize) throws Exception {
        PageHelper.startPage(page,pageSize);
        List<Product> products = productDao.findAll();
        return products;
    }

    //查询所有
    @Override
    public List<Product> findAll() throws Exception {
        return productDao.findAll();
    }

    //添加
    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }

    //根据id查询产品信息
    @Override
    public Product findById(Integer id) throws Exception {
        return productDao.findById(id);
    }

   /* //查询所有产品新  产品选择操作
    @Override
    public List<Product> selectFindAll() throws Exception {
        return productDao.findAll();
    }*/
}
