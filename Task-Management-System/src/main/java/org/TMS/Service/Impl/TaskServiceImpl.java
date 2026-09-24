package org.TMS.Service.Impl;

import org.TMS.Dto.Req.TaskRequestDto;
import org.TMS.Dto.Res.TaskResponseDto;
import org.TMS.Entity.Task;
import org.TMS.Entity.User;
import org.TMS.Mapper.TaskMapper;
import org.TMS.Repository.TaskRepository;
import org.TMS.Repository.UserRepository;
import org.TMS.Service.TaskService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public TaskServiceImpl(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskResponseDto createTask(Long userId,TaskRequestDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->
                        new RuntimeException("User not found with user id -"+userId));

        Task newTask = TaskMapper.toEntity(dto);

        newTask.setUser(user);

        Task savedTask = taskRepository.save(newTask);

        return TaskMapper.toResponse(savedTask);
    }

    @Override
    public List<TaskResponseDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskResponseDto> responseList = new ArrayList<>();

        for(Task task : tasks){
            TaskResponseDto dto = TaskMapper.toResponse(task);
            responseList.add(dto);
        }

        return responseList;
    }

    @Override
    public TaskResponseDto getTaskById(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with the given id - "+id));



        return TaskMapper.toResponse(task);
    }

    @Override
    public TaskResponseDto getTaskByTitle(String title) {

        Task task = taskRepository.findByTitle(title).orElseThrow(() -> new RuntimeException("Task not found with the given title-"+title));

        return TaskMapper.toResponse(task);
    }

    @Override
    public List<TaskResponseDto> getAllTasksOfAUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with user id -"+userId));
        List<Task> tasks = taskRepository.findByUser(user);
        List<TaskResponseDto> responseList = new ArrayList<>();
        for (Task task : tasks){
            TaskResponseDto dto = TaskMapper.toResponse(task);
            responseList.add(dto);
        }
        return responseList;
    }

    @Override
    public TaskResponseDto updateTask(Long id, TaskRequestDto dto) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found with the given id - "+id));
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setPriority(dto.getPriority());
        task.setDueDate(dto.getDueDate());

        Task updatedTask = taskRepository.save(task);
        return TaskMapper.toResponse(updatedTask);
    }


    @Override
    public void deleteTask(Long id) {

        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found with the given id - "+id));

        taskRepository.delete(task);
    }
}
