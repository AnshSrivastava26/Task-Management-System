package org.TMS.Controller;

import jakarta.validation.Valid;
import org.TMS.Dto.Req.TaskRequestDto;
import org.TMS.Dto.Res.TaskResponseDto;
import org.TMS.Service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<TaskResponseDto> createTask(
            @PathVariable Long userId,
            @Valid @RequestBody TaskRequestDto dto) {

        TaskResponseDto response = taskService.createTask(userId,dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {

        List<TaskResponseDto> responseList = taskService.getAllTasks();

        return ResponseEntity.ok(responseList);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTaskById(
            @PathVariable Long id) {

        TaskResponseDto response = taskService.getTaskById(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<TaskResponseDto> getTaskByTitle(
            @PathVariable String title) {

        TaskResponseDto response = taskService.getTaskByTitle(title);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskResponseDto>> getAllTasksOfAUser(
            @PathVariable Long userId) {

        List<TaskResponseDto> responseList =
                taskService.getAllTasksOfAUser(userId);

        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequestDto dto) {

        TaskResponseDto response = taskService.updateTask(id, dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(
            @PathVariable Long id) {

        taskService.deleteTask(id);

        return ResponseEntity.ok("Task Deleted Successfully");
    }


}
