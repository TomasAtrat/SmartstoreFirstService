package com.smartstore.smartstorewebservice.common.wrappers;

import com.smartstore.smartstorewebservice.dataAccess.entities.Preparation;
import com.smartstore.smartstorewebservice.dataAccess.entities.PreparationDetail;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PreparationWrapper {
    private Preparation preparation;
    private List<PreparationDetail> details;
}
