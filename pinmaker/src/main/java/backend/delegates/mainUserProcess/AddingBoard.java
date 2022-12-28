package backend.delegates.mainUserProcess;

import backend.dto.requests.BoardRequest;
import backend.services.boardService.impl.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddingBoard implements JavaDelegate {

    private final BoardServiceImpl boardService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String name = (String) delegateExecution.getVariable("nameOfBoard");
        long userId = (long) delegateExecution.getVariable("userId");

        BoardRequest boardRequest = new BoardRequest();
        boardRequest.setName(name);
        boardRequest.setUserId(userId);

        Long res = boardService.createBoard(boardRequest);

        delegateExecution.setVariable("boardId", res);

    }
}
