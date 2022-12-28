package backend.delegates.mainAdminProcess;

import backend.services.boardService.impl.BoardServiceImpl;
import backend.services.adminService.impl.AdminControlServiceImpl;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import static backend.utils.TextUtil.concatenateStrings;


@Component
@RequiredArgsConstructor
public class BlockingBoard implements JavaDelegate {
    private final BoardServiceImpl boardService;
    private final AdminControlServiceImpl adminService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        int boardId = (int) delegateExecution.getVariable("boardId");

        if (boardService.findBoardById((long) boardId))
            adminService.blockUserBoard((long) boardId);
        else
            throw new Exception(concatenateStrings("доски с boardId = ", String.valueOf(boardId), " не существует"));

    }
}
