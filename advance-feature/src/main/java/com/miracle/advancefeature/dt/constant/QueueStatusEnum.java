package com.miracle.advancefeature.dt.constant;

/**
 * @author : sungm
 * @date : 2021-04-01 11:58
 */
public enum QueueStatusEnum {


    /**
     * 状态
     */
    已发送("0"), 消费成功("1"), 消费失败("2"), 发送失败("3");

    private final String value;

    QueueStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
