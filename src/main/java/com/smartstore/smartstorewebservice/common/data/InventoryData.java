package com.smartstore.smartstorewebservice.common.data;

import com.smartstore.smartstorewebservice.dataAccess.entities.Inventory;
import com.smartstore.smartstorewebservice.dataAccess.entities.InventoryDetail;
import com.smartstore.smartstorewebservice.dataAccess.entities.InventoryProblem;
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
    private List<InventoryProblem> problems;
}
