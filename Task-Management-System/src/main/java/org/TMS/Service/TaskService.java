package org.TMS.Service;


import org.TMS.Dto.Req.TaskRequestDto;
import org.TMS.Dto.Res.TaskResponseDto;

import java.util.List;

public interface TaskService {
//    Create Task
//    Get all Tasks
//    Get Task by ID
//    Get Task by title
//    Get all Tasks of a User
//    Update Task
//    Delete Task

    TaskResponseDto createTask(Long UserId,TaskRequestDto dto);
    List<TaskResponseDto> getAllTasks();
    TaskResponseDto getTaskById(Long id);
    TaskResponseDto getTaskByTitle(String title);
    List<TaskResponseDto> getAllTasksOfAUser(Long userId);
    TaskResponseDto updateTask(Long id, TaskRequestDto dto);
    void deleteTask(Long id);

}
