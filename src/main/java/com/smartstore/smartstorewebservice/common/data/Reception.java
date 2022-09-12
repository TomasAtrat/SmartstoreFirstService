package com.smartstore.smartstorewebservice.common.data;

import com.smartstore.smartstorewebservice.dataAccess.entities.ReceptionDetail;
import com.smartstore.smartstorewebservice.dataAccess.entities.ReceptionList;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reception {
    private ReceptionList receptionList;
    private List<ReceptionDetail> details;
}
