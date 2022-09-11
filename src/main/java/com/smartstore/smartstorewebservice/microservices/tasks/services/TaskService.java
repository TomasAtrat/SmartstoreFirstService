package com.smartstore.smartstorewebservice.microservices.tasks.services;

import com.smartstore.smartstorewebservice.common.wrappers.HTTPAnswer;
import com.smartstore.smartstorewebservice.dataAccess.entities.Task;
import com.smartstore.smartstorewebservice.dataAccess.entities.UserInfo;
import com.smartstore.smartstorewebservice.dataAccess.repositories.TaskRepository;
import com.smartstore.smartstorewebservice.dataAccess.repositories.UserInfoRepository;
import com.smartstore.smartstorewebservice.dataAccess.repositories.VTasksByUserRepository;
import com.smartstore.smartstorewebservice.microservices.tasks.validation.TaskValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private UserInfoRepository userInfoRepository;
    private VTasksByUserRepository vTasksByUserRepository;

    public TaskService(TaskRepository taskRepository,
                       UserInfoRepository userInfoRepository,
                       VTasksByUserRepository vTasksByUserRepository){
        this.taskRepository = taskRepository;
        this.userInfoRepository = userInfoRepository;
        this.vTasksByUserRepository = vTasksByUserRepository;
    }

    public HTTPAnswer add(Task task){
        List<String> errors = new TaskValidator(task, userInfoRepository).validate();
        if (errors.size() == 0) saveTask(task);
        return createHTTPAnswer(errors);
    }

    private void saveTask(Task task) {
        if(task.getUser() == null) assignUserToTask(task);
        taskRepository.save(task);
    }

    private void assignUserToTask(Task task) {
        var users = vTasksByUserRepository.findAll();
        Optional<UserInfo> user = userInfoRepository.findById(users.stream().findFirst().get().getId());
        task.setUser(user.get());
    }

    private HTTPAnswer createHTTPAnswer(List<String> errors) {
        if(errors.size() > 0)
            return new HTTPAnswer(400, errors);
        return new HTTPAnswer(200, errors);
    }

}
