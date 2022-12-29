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
    public ResponseEntity<String> makeBoard(@Valid @RequestBody BoardRequest board) throws Exception{
        board.validate();
        Long res = boardService.createBoard(board);
        return new ResponseEntity<>(res.toString(), HttpStatus.CREATED);
    }
}
