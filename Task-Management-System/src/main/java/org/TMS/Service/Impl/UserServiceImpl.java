package org.TMS.Service.Impl;

import org.TMS.Dto.Req.UserRequestDto;
import org.TMS.Dto.Res.UserResponseDto;
import org.TMS.Entity.User;
import org.TMS.Exception.DuplicateUserException;
import org.TMS.Exception.UserNotFoundException;
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
        boolean existsByEmail = userRepository.existsByEmail(dto.getEmail());
        boolean existsByPhone = userRepository.existsByPhone(dto.getPhone());
        if (existsByEmail) {
            throw new DuplicateUserException(
                    "User with email '" + dto.getEmail() + "' already exists"
            );
        }

        if (existsByPhone){
            throw new DuplicateUserException(
              "User with phone '"+dto.getPhone()+"' already exists"
            );
        }

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
                        new UserNotFoundException("User Not Found with User id -"+id));
        return UserMapper.toResponse(user);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException("User Not Found with User id -"+id));

        boolean existsByEmail =
                userRepository.existsByEmailAndIdNot(dto.getEmail(), id);

        boolean existsByPhone =
                userRepository.existsByPhoneAndIdNot(dto.getPhone(), id);
        if (existsByEmail) {
            throw new DuplicateUserException(
                    "User with email '" + dto.getEmail() + "' already exists"
            );
        }

        if (existsByPhone) {
            throw new DuplicateUserException(
                    "User with phone '" + dto.getPhone() + "' already exists"
            );
        }

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPassword(dto.getPassword());
        userRepository.save(user);
        return UserMapper.toResponse(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException("User Not Found with User id -"+id));

        userRepository.delete(user);
    }
}
