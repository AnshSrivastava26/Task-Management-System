package org.TMS.Service;

import org.TMS.Dto.Req.UserRequestDto;
import org.TMS.Dto.Res.UserResponseDto;

import java.util.List;

public interface UserService {
//    Create User
//    Get all Users
//    Get User by ID
//    Update User
//    Delete User

    UserResponseDto createUser(UserRequestDto dto);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(Long id);
    UserResponseDto updateUser(Long id,UserRequestDto dto);
    void deleteUser(Long id);
}
