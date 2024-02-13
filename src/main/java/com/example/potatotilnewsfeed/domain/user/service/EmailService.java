package com.example.potatotilnewsfeed.domain.user.service;

import com.example.potatotilnewsfeed.domain.user.dto.CheckEmailRequestDto;
import com.example.potatotilnewsfeed.domain.user.dto.CheckEmailResponseDto;
import com.example.potatotilnewsfeed.domain.user.dto.EmailRequestDto;
import com.example.potatotilnewsfeed.domain.user.dto.EmailResponseDto;
import com.example.potatotilnewsfeed.domain.user.entity.Email;
import com.example.potatotilnewsfeed.domain.user.repository.EmailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.NoSuchElementException;
import java.util.Random;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmailService {

    public EmailService(JavaMailSender mailSender, EmailRepository emailRepository) {
        this.mailSender = mailSender;
        this.emailRepository = emailRepository;
    }

    private final JavaMailSender mailSender;
    private final EmailRepository emailRepository;

    @Value("${spring.mail.username}")
    private String username;


    public EmailResponseDto joinEmail(EmailRequestDto emailRequestDto) {
        int authNumber = makeRandomNumber();
        String setFrom = username;
        String toMail = emailRequestDto.getEmail();
        String title = "회원 가입 인증 이메일 입니다.";
        String content =
            "TIL APP을 방문해주셔서 감사합니다." +
                "<br><br>" +
                "인증 번호는 " + authNumber + "입니다." +
                "<br>" +
                "인증번호를 입력해주세요";
        mailSend(setFrom, toMail, title, content);

        Email email = new Email(authNumber);

        return new EmailResponseDto(emailRepository.save(email));
    }

    //이메일을 전송합니다.
    public void mailSend(String setFrom, String toMail, String title, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content,true); // 이메일의 내용 설정 두 번째 매개 변수에 true를 설정하여 html 설정으로한다.
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private int makeRandomNumber() {
        Random r = new Random();
        String randomNumber = "";
        for(int i = 0; i < 6; i++) {
            randomNumber += Integer.toString(r.nextInt(10));
        }
        return Integer.parseInt(randomNumber);
    }


    public CheckEmailResponseDto checkEmail(CheckEmailRequestDto checkEmailDto, Long id) {
        Email email = emailRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 id의 인증번호 정보가 없습니다."));
        if(email.getNumber() == Integer.parseInt(checkEmailDto.getNumber())){
            return new CheckEmailResponseDto(true);
        }
        return new CheckEmailResponseDto(false);
    }
}
