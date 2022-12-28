package backend.controllers;

import backend.dto.requests.BoardRequest;
import backend.repositories.BoardRepository;
import backend.services.boardService.BoardService;
import backend.services.boardService.impl.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/pin-builder")
public class BoardController {

    private final BoardRepository boardRepository;

    private final BoardService boardService;

    /**
     * BOARD - pins are stored on boards
     */

    /**
     * finding all boards
     */

    @RequestMapping(value = "/users/{id}/boards", method = RequestMethod.GET)
    public List<String> getAllUserBoards(@PathVariable Long id) {
        return boardService.findAllUserBoards(id);
    }

    /**
     * creating boards for pin
     */

    @RequestMapping(value = "/boards", method = {RequestMethod.OPTIONS, RequestMethod.POST})
    public ResponseEntity<String> makeBoard(@Valid @RequestBody BoardRequest board, BindingResult result) throws Exception{
        log.info("POST request to create new board {}", board);

        if (result.hasErrors()) {
            log.info("Validation Error");
            return new ResponseEntity<>("Board's name cannot be empty", HttpStatus.BAD_REQUEST);
        }

        if (!boardService.findBoard(board.getName(), board.getUserId())) {
            log.info("Board name is not unique");
            return new ResponseEntity<>("Board name must be unique", HttpStatus.BAD_REQUEST);
        }

        Long res = boardService.createBoard(board);

        log.info("Board {} was successfully created!", board);

        return new ResponseEntity<>(res.toString(), HttpStatus.CREATED);
    }
}
