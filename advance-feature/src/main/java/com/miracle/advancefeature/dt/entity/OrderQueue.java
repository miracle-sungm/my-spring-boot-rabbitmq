package com.miracle.advancefeature.dt.entity;

import lombok.*;

/**
 * @author : sungm
 * @date : 2021-04-01 11:50
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor()
@EqualsAndHashCode(callSuper = true)
public class OrderQueue extends Order {

    private String queueStatus;

}
