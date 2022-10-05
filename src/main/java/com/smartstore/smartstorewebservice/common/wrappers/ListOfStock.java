package com.smartstore.smartstorewebservice.common.wrappers;

import com.smartstore.smartstorewebservice.dataAccess.entities.Stock;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListOfStock {
    private List<Stock> stockList;
}
