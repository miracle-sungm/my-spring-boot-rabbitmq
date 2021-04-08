package com.miracle.common.api.constant;

/**
 * @author : sungm
 * @date : 2021-03-30 16:30
 */
public enum ExchangeEnum {

    /**
     * 交换机
     */
    FANOUT_MESSAGE("message_fanout_exchange", "fanout", Boolean.TRUE, Boolean.FALSE),
    DIRECT_MESSAGE("message_direct_exchange", "direct", Boolean.TRUE, Boolean.FALSE),
    TOPIC_MESSAGE("message_topic_exchange", "topic", Boolean.TRUE, Boolean.FALSE),

    TTL_DIRECT_MESSAGE("ttl_direct_exchange", "direct", Boolean.TRUE, Boolean.FALSE),
    DEAD_DIRECT_MESSAGE("dead_direct_exchange", "direct", Boolean.TRUE, Boolean.FALSE),
    DT_DIRECT_MESSAGE("dt_direct_exchange", "direct", Boolean.TRUE, Boolean.FALSE);

    private final String name;
    private final String type;
    private final Boolean durable;
    private final Boolean autoDelete;

    ExchangeEnum(String name, String type, Boolean durable, Boolean autoDelete) {
        this.name = name;
        this.type = type;
        this.durable = durable;
        this.autoDelete = autoDelete;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Boolean getDurable() {
        return durable;
    }

    public Boolean getAutoDelete() {
        return autoDelete;
    }
}
