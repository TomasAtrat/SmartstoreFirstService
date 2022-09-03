package com.smartstore.smartstorewebservice.common.wrappers;

import com.smartstore.smartstorewebservice.dataAccess.entities.OrderInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListOfOrderWrapper {
    List<OrderInfo> orders;
}
