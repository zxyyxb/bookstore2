package cn.edu.ecnu.stu.bookstore.component;

import lombok.Data;

@Data
public class Result {

    private Object data;

    private String message;

    private String code;

    public Result(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success(Object data) {
        return new Result(Constants.SUCCESS, "ok", data);
    }

    public static Result error(String code, String message) {
        return new Result(code, message, null);
    }

    public static Result success() {
        return new Result(Constants.SUCCESS, Constants.SUCCESS_MESSAGE, null);
    }


}
