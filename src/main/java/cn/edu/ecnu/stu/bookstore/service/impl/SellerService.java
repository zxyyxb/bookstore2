package cn.edu.ecnu.stu.bookstore.service.impl;

import cn.edu.ecnu.stu.bookstore.exception.BookAlreadyExistsException;
import cn.edu.ecnu.stu.bookstore.exception.StoreAlreadyExistsException;
import cn.edu.ecnu.stu.bookstore.exception.StoreNotFoundException;
import cn.edu.ecnu.stu.bookstore.exception.UserNotFoundException;
import cn.edu.ecnu.stu.bookstore.mapper.SellerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class SellerService {

    @Autowired
    private SellerMapper sellermapper;

    @Transactional
    public void createStore(String userId, String storeId) {
        // 检查商铺是否已存在
        if (sellermapper.storeExists(storeId)) {
            throw new StoreAlreadyExistsException("商铺ID已存在");
        }

        // 创建商铺
        sellermapper.insertStore(userId, storeId);
    }

    @Transactional
    public void addBookWithStock(String userId, String storeId, Map<String, Object> bookInfo, Integer stockLevel) {
        // 检查卖家用户是否存在
        if (!sellermapper.userExists(userId)) {
            throw new UserNotFoundException("卖家用户ID不存在");
        }

        // 检查商铺是否存在
        if (!sellermapper.storeExists(storeId)) {
            throw new StoreNotFoundException("商铺ID不存在");
        }

        // 检查图书是否已经存在
        String bookId = (String) bookInfo.get("id");
        if (sellermapper.bookExists(bookId)) {
            throw new BookAlreadyExistsException("图书ID已存在");
        }

        // 插入图书信息
        sellermapper.insertBook(userId, storeId, bookInfo);

        // 插入库存信息
        sellermapper.insertStock(bookId, storeId, stockLevel != null ? stockLevel : 0);
    }


    @Transactional
    public void addStockLevel(String storeId,String bookId, Integer stockLevel) {

        if (!sellermapper.storeExists(storeId)) {
            throw new UserNotFoundException("商铺ID不存在");
        }

        if (!sellermapper.bookExists(storeId)) {
            throw new StoreNotFoundException("商铺ID不存在");
        }

        // 更新图书信息
        sellermapper.updateStockLevel(bookId, storeId, stockLevel);

    }
}