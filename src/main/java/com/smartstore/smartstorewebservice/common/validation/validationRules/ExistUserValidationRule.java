package com.smartstore.smartstorewebservice.common.validation.validationRules;

import com.smartstore.smartstorewebservice.common.validation.IValidationRule;
import com.smartstore.smartstorewebservice.dataAccess.entities.UserInfo;
import com.smartstore.smartstorewebservice.dataAccess.repositories.UserInfoRepository;

public class ExistUserValidationRule implements IValidationRule {

    private UserInfo user;
    private UserInfoRepository userInfoRepository;

    public ExistUserValidationRule(UserInfo user, UserInfoRepository userInfoRepository){
        this.user = user;
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public String validate() {
        String error = "";
        if(!userInfoRepository.existsById(user.getId()))
            error = "El usuario ingresado no existe en el sistema";
        return error;
    }
}
