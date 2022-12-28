package backend.delegates.mainAdminProcess;

import backend.dto.requests.LoginRequest;
import backend.dto.responses.LoginResponse;
import backend.models.Role;
import backend.services.userService.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginAdmin implements JavaDelegate {

    private final UserServiceImpl userService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String email = (String) delegateExecution.getVariable("email");
        String password = (String) delegateExecution.getVariable("password");
        long adminId = -1;

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        LoginResponse loginResponse = userService.login(loginRequest);
        adminId = loginResponse.getUser().getId();


        if (userService.getUserRole(adminId) == Role.USER) {
            delegateExecution.setVariable("authError", "User cannot start this process");
            throw new BpmnError("authorizationRoleFailed");
        }

        delegateExecution.setVariable("adminId", adminId);
    }
}
