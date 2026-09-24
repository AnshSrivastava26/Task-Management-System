package org.TMS.Service.Impl;

import org.TMS.Dto.Req.UserRequestDto;
import org.TMS.Dto.Res.UserResponseDto;
import org.TMS.Entity.User;
import org.TMS.Mapper.UserMapper;
import org.TMS.Repository.UserRepository;
import org.TMS.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto dto) {
        User user = UserMapper.toEntity(dto);
        return UserMapper.toResponse(userRepository.save(user));
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> userList = userRepository.findAll();

        List<UserResponseDto> responseList = new ArrayList<>();

        for (User user : userList) {
            UserResponseDto dto = UserMapper.toResponse(user);
            responseList.add(dto);
        }

        return responseList;
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("User Not Found with User id -"+id));
        return UserMapper.toResponse(user);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User Not Found with User id -"+id));

        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPassword(dto.getPassword());
        userRepository.save(user);
        return UserMapper.toResponse(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User Not Found with User id -"+id));

        userRepository.delete(user);
    }
}
