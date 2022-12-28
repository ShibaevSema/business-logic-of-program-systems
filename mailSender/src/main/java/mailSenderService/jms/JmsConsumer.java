package mailSenderService.jms;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mailSenderService.dto.NewMailDto;
import mailSenderService.services.MailSenderService;
import mailSenderService.services.impl.MailSenderServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class JmsConsumer {
    private final MailSenderService mailSenderService;

    @Value("@{letter.blocking-user.theme}")
    private String blockUserLetterTheme;

    @Value("@{letter.blocking-user.body}")
    private String blockUserLetterBody;

    @Value("@{letter.blocking-board.theme}")
    private String blockBoardLetterTheme;

    @Value("@{letter.blocking-board.theme}")
    private String blockBoardLetterBody;

    @Value("@{letter.blocking-board.theme}")
    private String blockPinLetterTheme;

    @Value("@{letter.blocking-board.theme}")
    private String blockPinLetterBody;

    @JmsListener(destination = "blockUserMail")
    public void getMessageForBlockingUser(String message) throws Exception {
        NewMailDto newMailDto = new NewMailDto();
        newMailDto.setEmailTo(message);
        newMailDto.setSubject(blockUserLetterTheme);
        newMailDto.setMessageBody(blockUserLetterBody);
        log.info("Mail was sent to email " + message);
        mailSenderService.sendEmail(newMailDto);
    }

    @JmsListener(destination = "blockBoardMail")
    public void getMessageForBlockingBoard(String message) throws Exception {
        NewMailDto newMailDto = new NewMailDto();
        newMailDto.setEmailTo(message);
        newMailDto.setSubject(blockBoardLetterTheme);
        newMailDto.setMessageBody(blockBoardLetterBody);
        log.info("Mail was sent to email " + message);
        mailSenderService.sendEmail(newMailDto);
    }

    @JmsListener(destination = "blockPinMail")
    public void getMessageForBlockingPin(String message) throws Exception {
        NewMailDto newMailDto = new NewMailDto();
        newMailDto.setEmailTo(message);
        newMailDto.setSubject(blockPinLetterTheme);
        newMailDto.setMessageBody(blockPinLetterBody);
        log.info("Mail was sent to email " + message);
        mailSenderService.sendEmail(newMailDto);
    }
}
