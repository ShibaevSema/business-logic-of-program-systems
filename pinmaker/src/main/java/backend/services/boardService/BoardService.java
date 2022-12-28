package backend.services.boardService;

import backend.dto.requests.BoardRequest;

import java.util.List;

public interface BoardService {
    public Long createBoard(BoardRequest boardRequest);

    public boolean findBoard(String name, Long userId);

    public boolean findBoardById(Long id, Long userId);

    public boolean findBoardById(Long id);

    public List<String> findAllUserBoards(Long userId);
}
