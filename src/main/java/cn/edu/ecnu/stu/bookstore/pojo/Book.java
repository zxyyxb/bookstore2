package cn.edu.ecnu.stu.bookstore.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class Book {

    private String bookId;

    private String storeId;

    private String title;

    private String author;

    private String publisher;

    private String originalTitle;

    private String translator;

    private String pubYear;

    private Integer pages;

    private BigDecimal price;

    private String binding;

    private String isbn;

    private Integer stockLevel;

    private List<String> tags;

    private List<String> pictures;

    private String authorIntro;

    private String bookIntro;

    private String content;
}
