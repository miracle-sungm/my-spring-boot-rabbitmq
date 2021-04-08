package com.miracle.common.api.constant;

/**
 * @author : sungm
 * @date : 2021-03-30 12:07
 */
public enum QueueEnum {

    /**
     * 交换机枚举
     */
    SMS(QueueNameConstant.SMS, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE),
    EMAIL(QueueNameConstant.EMAIL, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE),
    TTL(QueueNameConstant.TTL, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE),
    DEAD(QueueNameConstant.DEAD, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE),
    DT(QueueNameConstant.DT, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE);

    private final String name;
    private final Boolean durable;
    private final Boolean exclusive;
    private final Boolean autoDelete;

    QueueEnum(String name, Boolean durable, Boolean exclusive, Boolean autoDelete) {
        this.name = name;
        this.durable = durable;
        this.exclusive = exclusive;
        this.autoDelete = autoDelete;
    }

    public String getName() {
        return name;
    }

    public Boolean getDurable() {
        return durable;
    }

    public Boolean getExclusive() {
        return exclusive;
    }

    public Boolean getAutoDelete() {
        return autoDelete;
    }

}
