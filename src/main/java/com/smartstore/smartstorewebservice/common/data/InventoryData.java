package com.smartstore.smartstorewebservice.common.data;

import com.smartstore.smartstorewebservice.dataAccess.entities.Inventory;
import com.smartstore.smartstorewebservice.dataAccess.entities.InventoryDetail;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryData {
    private Inventory inventory;
    private List<InventoryDetail> details;
}
