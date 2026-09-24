package org.TMS.Dto.Res;


import jakarta.validation.constraints.Email;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private Long id;

//    @Email
    private String name;

    private String email;

    private String phone;
}
