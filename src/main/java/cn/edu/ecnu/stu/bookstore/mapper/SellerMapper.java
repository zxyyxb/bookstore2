package cn.edu.ecnu.stu.bookstore.mapper;

import org.apache.ibatis.annotations.*;

import java.util.Map;

@Mapper
public interface SellerMapper {

    // 检查商铺是否已存在
    @Select("SELECT COUNT(*) FROM t_store WHERE store_id = #{storeId}")
    boolean storeExists(@Param("storeId") String storeId);

    // 插入新商铺
    @Insert("INSERT INTO t_store (user_id, store_id) VALUES (#{userId}, #{storeId})")
    void insertStore(@Param("userId") String userId, @Param("storeId") String storeId);

    // 检查用户是否存在
    @Select("SELECT COUNT(*) FROM t_user WHERE id = #{userId}")
    boolean userExists(@Param("userId") String userId);


    // 检查图书是否存在
    @Select("SELECT COUNT(*) FROM t_book WHERE id = #{bookId}")
    boolean bookExists(@Param("bookId") String bookId);

    // 插入图书信息
    @Insert("INSERT INTO t_book (id, store_id, user_id, title, author, publisher, original_title, translator, pub_year, pages, price, binding,  author_intro, book_intro, content) "
            + "VALUES (#{bookInfo.id}, #{storeId}, #{userId}, #{bookInfo.title}, #{bookInfo.author}, #{bookInfo.publisher}, #{bookInfo.original_title}, #{bookInfo.translator}, #{bookInfo.pub_year}, #{bookInfo.pages}, #{bookInfo.price}, #{bookInfo.binding}, #{bookInfo.author_intro}, #{bookInfo.book_intro}, #{bookInfo.content})")
    void insertBook(@Param("userId") String userId, @Param("storeId") String storeId, @Param("bookInfo") Map<String, Object> bookInfo);

    // 插入库存信息
    @Insert("INSERT INTO t_stock (book_id, store_id, stock_level) VALUES (#{bookId}, #{storeId}, #{stockLevel})")
    void insertStock(@Param("bookId") String bookId, @Param("storeId") String storeId, @Param("stockLevel") Integer stockLevel);

    @Update("UPDATE t_stock SET stock_level = stock_level + #{stockLevel} WHERE book_id = #{bookId} AND store_id = #{storeId}")
    void updateStockLevel(@Param("bookId") String bookId, @Param("storeId") String storeId, @Param("stockLevel") Integer stockLevel);

}