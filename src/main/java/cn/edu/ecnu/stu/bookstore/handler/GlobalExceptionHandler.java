package cn.edu.ecnu.stu.bookstore.handler;

import cn.edu.ecnu.stu.bookstore.component.AppException;
import cn.edu.ecnu.stu.bookstore.component.Constants;
import cn.edu.ecnu.stu.bookstore.component.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public Result handleAppException(AppException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

//    @ExceptionHandler(Exception.class)
//    public Result handleException(Exception e) {
//        return Result.error(Constants.SYSTEM_ERROR, e.getMessage());
//    }

}
