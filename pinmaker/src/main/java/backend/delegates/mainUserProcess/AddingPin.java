package backend.delegates.mainUserProcess;

import backend.dto.requests.PinRequest;
import backend.services.pinService.impl.PinServiceImpl;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddingPin implements JavaDelegate {

    private final PinServiceImpl pinService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String name = (String) delegateExecution.getVariable("nameOfPin");
        Long boardId = (long) delegateExecution.getVariable("boardId");
        String description = (String) delegateExecution.getVariable("description");
        String altText = (String) delegateExecution.getVariable("altText");
        String link = (String) delegateExecution.getVariable("link");
        Long userId = (long) delegateExecution.getVariable("userId");
        String fileName = (String) delegateExecution.getVariable("fileName");

        PinRequest pinRequest = new PinRequest();
        pinRequest.setName(name);
        pinRequest.setBoard_id(boardId);
        pinRequest.setDescription(description);
        pinRequest.setAlt_text(altText);
        pinRequest.setLink(link);
        pinRequest.setUserId(userId);
        pinRequest.setFileName(fileName);

        pinService.createPin(pinRequest);
    }

}

