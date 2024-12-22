package cn.edu.ecnu.stu.bookstore.component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AppException extends RuntimeException{

    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
