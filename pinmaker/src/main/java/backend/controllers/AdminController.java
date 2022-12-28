package backend.controllers;

import backend.dto.responses.UserResponse;
import backend.services.adminService.AdminControlService;
import backend.services.adminService.impl.AdminControlServiceImpl;
import backend.services.userService.UserService;
import backend.services.userService.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static backend.utils.TextUtil.concatenateStrings;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final AdminControlService adminService;

    /**-
     * get all users and their pins and boards
     */

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }


    /**
     * block user - it will block all user's pins and boards
     */

    @RequestMapping(value = "/blocking/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> blockUser(@Valid @PathVariable Long id) throws Exception {
        adminService.blockUser(id);
        return new ResponseEntity<>(concatenateStrings("User ", String.valueOf(id), " is blocked"), HttpStatus.OK);
    }

    /**
     * block user's pin
     */

    @RequestMapping(value = "/blocking/pin/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> blockUserPin(@Valid @PathVariable Long id) throws Exception {
        adminService.blockUserPin(id);
        return new ResponseEntity<>(concatenateStrings("Pin ", String.valueOf(id)," is blocked"), HttpStatus.OK);
    }

    /**
     * block user's board - it will block all user's pins on this board
     */

    @RequestMapping(value = "/blocking/board/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> blockUserBoard(@Valid @PathVariable Long id) throws Exception {
        adminService.blockUserBoard(id);
        return new ResponseEntity<>(concatenateStrings("Board ", String.valueOf(id), " is blocked"), HttpStatus.OK);
    }


}
