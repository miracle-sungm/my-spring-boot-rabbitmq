package com.miracle.advancefeature.dt.constant;

/**
 * @author : sungm
 * @date : 2021-03-31 17:49
 */
public enum OrderStatusEnum {

    /**
     * 状态
     */
    就绪("0"), 成功("1"), 失败("2");

    private final String value;

    OrderStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
