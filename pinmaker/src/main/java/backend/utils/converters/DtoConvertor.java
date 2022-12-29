package backend.utils.converters;

import backend.dto.requests.PinRequest;
import backend.dto.requests.UserDto;
import backend.entities.Pin;
import backend.entities.User;
import backend.models.Role;
import org.springframework.stereotype.Component;

@Component
public class DtoConvertor {

    public User convertUserDtoToEntity(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAge(userDto.getAge());
        user.setRole(Role.USER);
        user.set_blocked(false);
        return user;
    }

    public Pin convertPinDtoToEntity(PinRequest pinRequest) {
        Pin pin = new Pin();
        pin.setName(pinRequest.getName());
        pin.setDescription(pinRequest.getDescription());
        pin.setAltText(pinRequest.getAlt_text());
        pin.setLink(pinRequest.getLink());
        pin.set_blocked(false);
        return pin;
    }

}
