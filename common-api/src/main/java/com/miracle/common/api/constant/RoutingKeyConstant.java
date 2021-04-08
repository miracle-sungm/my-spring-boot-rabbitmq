package com.miracle.common.api.constant;

/**
 * @author : sungm
 * @date : 2021-03-30 15:09
 */
public class RoutingKeyConstant {

    private RoutingKeyConstant() {}

    public static final String DIRECT_SMS = "direct.order.sms";
    public static final String DIRECT_EMAIL = "direct.order.email";

    public static final String TOPIC_SMS = "*.order.sms";
    public static final String TOPIC_EMAIL = "#.email";

    public static final String DIRECT_TTL = "ttl";
    public static final String DIRECT_DEAD = "dead";
    public static final String DIRECT_DT = "dt";

}
