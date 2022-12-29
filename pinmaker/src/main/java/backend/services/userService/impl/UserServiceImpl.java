package backend.services.userService.impl;

import backend.dto.mappers.UserMapper;
import backend.dto.requests.LoginRequest;
import backend.dto.requests.UserDto;
import backend.dto.responses.*;
import backend.entities.Board;
import backend.entities.Pin;
import backend.models.Role;
import backend.entities.User;
import backend.exceptions.ApplicationException;
import backend.exceptions.ErrorEnum;
import backend.repositories.BoardRepository;
import backend.repositories.PinRepository;
import backend.repositories.UserRepository;
import backend.security.JwtUtil;
import backend.services.userService.UserService;
import backend.utils.converters.DtoConvertor;
import backend.utils.converters.EntityConvertor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final PinRepository pinRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final DtoConvertor dtoConvertor;

    private final EntityConvertor entityConvertor;

    public boolean checkUser(String email) {
        return userRepository.findUserByEmail(email) != null;
    }

    public boolean findUser(Long id) {
        return userRepository.findUserById(id) != null;
    }

    public boolean addUser(UserDto userDto) {
        if (checkUser(userDto.getEmail())) {
            return false;
        }
        User user = dtoConvertor.convertUserDtoToEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Unexpected Error {}", e.getMessage());
            throw new ApplicationException(ErrorEnum.SERVICE_DATA_BASE_EXCEPTION.createApplicationError());
        }

        return true;
    }

    public LoginResponse login(LoginRequest loginRequest) throws ApplicationException {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        if (!authentication.isAuthenticated()) {
            throw ErrorEnum.UNAUTHORIZED_EXCEPTION.exception();
        }

        User user = userRepository.findUserByEmail(loginRequest.getEmail());
        ErrorEnum.AUTH_LOGIN_ERROR.throwIfFalse(!ObjectUtils.isEmpty(user));
        ErrorEnum.AUTH_PASSWORD_ERROR.throwIfFalse(passwordEncoder.matches(loginRequest.getPassword(),
                user.getPassword()));
        LoginDto loginDto = userMapper.convertMemberToDto(user);
        String token = jwtUtil.generateToken(loginRequest.getEmail());
        LoginResponse loginResponse = new LoginResponse(token, loginDto);
        return loginResponse;
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAllUsers();

        return entityConvertor.convertListUserToListDto(users);
    }

    public List<BoardResponseDto> getUserBoards(Long userId) {
        List<Board> boards = boardRepository.findAllByUser_Id(userId);
        return entityConvertor.convertListBoardToListDto(boards);
    }

    public List<PinResponseDto> getBoardPins(Long boardId) {
        List<Pin> pins = pinRepository.findAllByBoard_Id(boardId);
        return entityConvertor.convertListPinToListDto(pins);
    }

    public Role getUserRole(Long id) {
        User user = userRepository.findUserById(id);
        return user.getRole();
    }

}
