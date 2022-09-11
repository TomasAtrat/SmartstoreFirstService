package com.smartstore.smartstorewebservice.common.wrappers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class HTTPAnswer {
    public int status;
    public List<String> errors;
}
