package com.smartstore.smartstorewebservice.microservices.tasks.validation;

import com.smartstore.smartstorewebservice.common.validation.IValidator;
import com.smartstore.smartstorewebservice.common.validation.validationRules.ExistUserValidationRule;
import com.smartstore.smartstorewebservice.common.validation.validationRules.MaxStringLengthValidationRule;
import com.smartstore.smartstorewebservice.dataAccess.entities.Task;
import com.smartstore.smartstorewebservice.dataAccess.repositories.UserInfoRepository;

import java.util.ArrayList;
import java.util.List;

public class TaskValidator implements IValidator {
    private final ArrayList<String> errors;
    private Task task;
    private UserInfoRepository userInfoRepository;

    public TaskValidator(Task task, UserInfoRepository userInfoRepository) {
        this.task = task;
        this.userInfoRepository = userInfoRepository;
        errors = new ArrayList<>();
    }

    @Override
    public List<String> validate() {
        validateUser();
        validateCategory();
        validateDescription();
        validatePriority();
        validateState();
        validateTitle();
        return this.errors;
    }

    private void validateUser() {
        if (task.getUser() != null) {
            String validUser = new ExistUserValidationRule(task.getUser(), userInfoRepository).validate();
            if(validUser.length() > 0) errors.add("Usuario: " + validUser);
        }
    }

    private void validateCategory() {
        String maxLength = new MaxStringLengthValidationRule(task.getCategory().length(),
                255).validate();

        if (maxLength.length() > 0) errors.add("Categoría: " + maxLength);
    }

    private void validateDescription() {
        String maxLength = new MaxStringLengthValidationRule(task.getDescription().length(),
                255).validate();

        if (maxLength.length() > 0) errors.add("Descripción: " + maxLength);
    }

    private void validatePriority() {
        String maxLength = new MaxStringLengthValidationRule(task.getPriority().length(),
                255).validate();

        if (maxLength.length() > 0) errors.add("Prioridad: " + maxLength);
    }

    private void validateState() {
        String maxLength = new MaxStringLengthValidationRule(task.getState().length(),
                255).validate();

        if (maxLength.length() > 0) errors.add("Estado: " + maxLength);
    }

    private void validateTitle() {
        String maxLength = new MaxStringLengthValidationRule(task.getTitle().length(),
                255).validate();

        if (maxLength.length() > 0) errors.add("Título: " + maxLength);
    }
}
