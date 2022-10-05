package com.smartstore.smartstorewebservice.common.wrappers;

import com.smartstore.smartstorewebservice.dataAccess.entities.OrderDetail;
import com.smartstore.smartstorewebservice.dataAccess.entities.OrderInfo;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderWrapper {
    private OrderInfo orderInfo;
    private List<OrderDetail> orderDetailList;
}
