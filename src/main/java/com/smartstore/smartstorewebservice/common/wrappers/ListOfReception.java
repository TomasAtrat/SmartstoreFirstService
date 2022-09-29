package com.smartstore.smartstorewebservice.common.wrappers;

import com.smartstore.smartstorewebservice.dataAccess.entities.ReceptionList;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListOfReception {
    private List<ReceptionList> references;
}
