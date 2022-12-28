package backend.delegates.mainAdminProcess;

import backend.services.pinService.impl.PinServiceImpl;
import backend.services.adminService.impl.AdminControlServiceImpl;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import static backend.utils.TextUtil.concatenateStrings;

@Component
@RequiredArgsConstructor
public class BlockingPin implements JavaDelegate {
    private final PinServiceImpl pinService;
    private final AdminControlServiceImpl adminService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        int pinId = (int) delegateExecution.getVariable("pinId");

        if (pinService.findPin((long) pinId))
            adminService.blockUserPin((long) pinId);
        else
            throw new Exception(concatenateStrings("пина с pinId = ", String.valueOf(pinId)," не существует"));

    }
}
