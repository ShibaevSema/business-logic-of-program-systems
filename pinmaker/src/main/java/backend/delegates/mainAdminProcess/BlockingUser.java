package backend.delegates.mainAdminProcess;

import backend.services.userService.impl.UserServiceImpl;
import backend.services.adminService.impl.AdminControlServiceImpl;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import static backend.utils.TextUtil.concatenateStrings;

@Component
@RequiredArgsConstructor
public class BlockingUser implements JavaDelegate {

    private final UserServiceImpl userService;
    private final AdminControlServiceImpl adminService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        int userId = (int) delegateExecution.getVariable("userId");

        if (userService.findUser((long) userId))
            adminService.blockUser((long) userId);
        else
            throw new Exception(concatenateStrings("пользователя с userId = ", String.valueOf(userId), " не существует"));

    }
}
