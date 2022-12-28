package backend.dto.requests;

import lombok.*;

@Data
public class UserDto {
    private String email;
    private String password;
    private String age;

    public UserDto(){}
}
