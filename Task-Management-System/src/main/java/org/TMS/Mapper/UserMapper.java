package org.TMS.Mapper;

import org.TMS.Dto.Req.UserRequestDto;
import org.TMS.Dto.Res.UserResponseDto;
import org.TMS.Entity.User;

public class UserMapper {

    public static User toEntity(UserRequestDto dto) {
        User user = new User();

        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhone());

        return user;
    }

    public static UserResponseDto toResponse(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getEmail(),
                user.getPhone()
        );
    }
}