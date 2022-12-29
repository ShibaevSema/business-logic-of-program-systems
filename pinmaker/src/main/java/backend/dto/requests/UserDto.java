package backend.dto.requests;

import backend.exceptions.ApplicationException;
import backend.exceptions.ErrorEnum;
import lombok.*;

@Data
public class UserDto {
    private String email;
    private String password;
    private String age;

    public UserDto(){}

    public void validate(){
        if(this.email.length()<10)
            throw ErrorEnum.BAD_REQUEST_REGISTRATION.exception();
    }
}
