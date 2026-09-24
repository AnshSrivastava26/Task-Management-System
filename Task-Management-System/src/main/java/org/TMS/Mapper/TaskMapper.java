package org.TMS.Mapper;

import org.TMS.Dto.Req.TaskRequestDto;
import org.TMS.Dto.Res.TaskResponseDto;
import org.TMS.Entity.Task;


public class TaskMapper {

    public static Task toEntity(TaskRequestDto dto) {
        Task task = new Task();

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setPriority(dto.getPriority());
        task.setDueDate(dto.getDueDate());
        return task;
    }

    public static TaskResponseDto toResponse(Task task){
        return new TaskResponseDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate(),
                task.getUser().getId()
        );
    }

}
