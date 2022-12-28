package backend.services.userService;

import backend.dto.requests.LoginRequest;
import backend.dto.requests.UserDto;
import backend.dto.responses.BoardResponseDto;
import backend.dto.responses.LoginResponse;
import backend.dto.responses.PinResponseDto;
import backend.dto.responses.UserResponse;
import backend.models.Role;

import java.util.List;

public interface UserService {
    public boolean checkUser(String email);

    public boolean findUser(Long id);

    public boolean addUser(UserDto userDto);

    public LoginResponse login(LoginRequest loginRequest);

    public List<UserResponse> getAllUsers();

    public List<BoardResponseDto> getUserBoards(Long userId);

    public List<PinResponseDto> getBoardPins(Long boardId);

    public Role getUserRole(Long id);
}
