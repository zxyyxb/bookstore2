package cn.edu.ecnu.stu.bookstore.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum OrderStatus{
    WAIT_PAYMENT(0),
    WAIT_SEND(1),
    WAIT_RECEIVE(2),
    COMPLETED(3),
    CANCEL(4);

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static OrderStatus getByValue(int val) {
        for(OrderStatus status : values()) {
            if(status.value == val)
                return status;
        }
        return null;
    }
}
