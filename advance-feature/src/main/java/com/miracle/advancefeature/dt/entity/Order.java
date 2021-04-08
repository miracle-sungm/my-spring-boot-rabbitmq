package com.miracle.advancefeature.dt.entity;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author : sungm
 * @date : 2021-03-31 16:30
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    private Long orderId;
    private String orderContent;
    private LocalDateTime createdTime;
    private String status;

}
