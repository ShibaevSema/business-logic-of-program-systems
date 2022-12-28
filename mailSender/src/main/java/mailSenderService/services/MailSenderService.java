package mailSenderService.services;

import mailSenderService.dto.NewMailDto;

import javax.mail.MessagingException;

public interface MailSenderService {
    public void sendEmail(NewMailDto newMailDto) throws MessagingException;
}
