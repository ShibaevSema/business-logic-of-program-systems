package backend.controllers;

import backend.dto.requests.BoardRequest;
import backend.repositories.BoardRepository;
import backend.services.boardService.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/pin-builder")
public class BoardController {

    private final BoardService boardService;

    /**
     * BOARD - pins are stored on boards
     */

    /**
     * finding all boards
     */

    @GetMapping(value = "/users/{id}/boards")
    public List<String> getAllUserBoards(@PathVariable Long id) {
        return boardService.findAllUserBoards(id);
    }

    /**
     * creating boards for pin
     */

    @PostMapping(value = "/boards")
    public ResponseEntity<String> makeBoard(@Valid @RequestBody BoardRequest board) throws Exception{
        board.validate();
        Long res = boardService.createBoard(board);
        return new ResponseEntity<>(res.toString(), HttpStatus.CREATED);
    }
}
