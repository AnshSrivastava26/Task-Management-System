package org.TMS.Controller;

import jakarta.validation.Valid;
import org.TMS.Dto.Req.UserRequestDto;
import org.TMS.Dto.Res.UserResponseDto;
import org.TMS.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto dto){

        UserResponseDto response = userService.createUser(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id){

        UserResponseDto response = userService.getUserById(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){

        List<UserResponseDto> responseList = userService.getAllUsers();

        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id ,@Valid @RequestBody UserRequestDto dto){

        UserResponseDto response = userService.updateUser(id,dto);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){

        userService.deleteUser(id);

        return ResponseEntity.status(HttpStatus.OK).body("User Deleted Successfully");

    }
}
