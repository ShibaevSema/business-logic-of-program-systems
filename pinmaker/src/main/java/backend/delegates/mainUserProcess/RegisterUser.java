package backend.delegates.mainUserProcess;

import backend.dto.requests.UserDto;
import backend.services.userService.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterUser implements JavaDelegate {

    private final UserServiceImpl userService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String email = (String) delegateExecution.getVariable("email");
        String password = (String) delegateExecution.getVariable("password");
        String age = (String) delegateExecution.getVariable("age");

        UserDto newUser = new UserDto();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setAge(age);

        userService.addUser(newUser);
    }

}
