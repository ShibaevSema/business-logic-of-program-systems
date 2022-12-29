package backend.utils.converters;

import backend.dto.responses.BoardResponseDto;
import backend.dto.responses.PinResponseDto;
import backend.dto.responses.UserResponse;
import backend.entities.Board;
import backend.entities.Pin;
import backend.entities.User;
import backend.services.pinService.PinService;
import backend.services.userService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class EntityConvertor {


    public List<UserResponse> convertListUserToListDto(List<User> users) {
        return users.stream().map(this::convertUserToDto).collect(Collectors.toList());
    }

    public UserResponse convertUserToDto(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setAge(user.getAge());
        return userResponse;
    }

    public BoardResponseDto convertBoardToDto(Board board){
        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setId(board.getId());
        boardResponseDto.setName(board.getName());
        boardResponseDto.set_blocked(board.is_blocked());
        return boardResponseDto;
    }
    public List<BoardResponseDto> convertListBoardToListDto(List<Board> boards) {
        return boards.stream().map(this::convertBoardToDto).collect(Collectors.toList());
    }

    public PinResponseDto convertPinToDto(Pin pin) {
        PinResponseDto pinResponseDto = new PinResponseDto();
        pinResponseDto.setId(pin.getId());
        pinResponseDto.setName(pin.getName());
        pinResponseDto.setDescription(pin.getDescription());
        pinResponseDto.setAltText(pin.getAltText());
        pinResponseDto.setLink(pin.getLink());
        pinResponseDto.setOriginalFileName(pin.getOriginalFileName());
        pinResponseDto.set_blocked(pin.is_blocked());
        return pinResponseDto;
    }

    public List<PinResponseDto> convertListPinToListDto(List<Pin> pins) {
        return pins.stream().map(this::convertPinToDto).collect(Collectors.toList());
    }



}



