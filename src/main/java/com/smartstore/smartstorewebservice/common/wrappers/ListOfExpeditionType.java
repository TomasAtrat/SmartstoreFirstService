package com.smartstore.smartstorewebservice.common.wrappers;

import com.smartstore.smartstorewebservice.dataAccess.entities.ExpeditionType;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListOfExpeditionType {
    private List<ExpeditionType> expeditionTypeList;
}
