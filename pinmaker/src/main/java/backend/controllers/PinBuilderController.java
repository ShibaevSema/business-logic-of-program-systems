package backend.controllers;


import backend.dto.requests.PinRequest;
import backend.dto.responses.PinWithPhotoResponse;
import backend.services.boardService.BoardService;
import backend.services.boardService.impl.BoardServiceImpl;
import backend.services.pinService.PinService;
import backend.services.pinService.impl.PinServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/pin-builder")
public class PinBuilderController {

    private final PinService pinService;

    private final BoardService boardService;


    /**
     * PIN
     */

    /**
     * creating Pin
     */

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> makePin(@RequestBody PinRequest pin) throws Exception {
        pinService.createPin(pin);
        return new ResponseEntity<>("Pin was created", HttpStatus.CREATED);
    }


    /**
     * finding all user's pin
     */
    @RequestMapping(value = "user/{id}/pins", method = RequestMethod.GET)
    public List<PinWithPhotoResponse> getAllUserPins(@PathVariable Long id) {
        return pinService.findUserPins(id);
    }

    /**
     * get all pins of one board
     */
    @RequestMapping(value = "/board/{id}/pins", method = RequestMethod.GET)
    public List<PinWithPhotoResponse> getAllBoardPins(@PathVariable Long id) {
        return pinService.findBoardPins(id);
    }


}