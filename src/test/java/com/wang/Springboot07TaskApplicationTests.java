package com.wang;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot07TaskApplicationTests {

    //在SpringBoot中JavaMailSenderImpl封装了邮件发送的方法, 自动装配即可使用
    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    void contextLoads() {

        //一个简单的邮件 (只有文字)
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        //标题
        simpleMailMessage.setSubject("Hello My QQ Mail!");
        //正文
        simpleMailMessage.setText("这里是正文!");
        //设置收发地址
        simpleMailMessage.setTo("715180879@qq.com");
        simpleMailMessage.setFrom("715180879@qq.com");

        //发送邮件
        mailSender.send(simpleMailMessage);
    }

    @Test
    void contextLoads2() throws MessagingException {

        //一个复杂的邮件, 可以通过mailSender的createMimeMessage方法创建一个MimeMessage
        MimeMessage message = mailSender.createMimeMessage();

        //组装, 利用SpringBoot提供的 MimeMessageHelper, boolean可以设置是否支持多文件
        MimeMessageHelper helper = new MimeMessageHelper(message,true);

        helper.setSubject("Hello My QQ Mail! Again!");
        //设置True支持Html标签
        helper.setText("<p style='color:red'>这里是正文!</p>",true);

        //附件, 使用绝对路径
        helper.addAttachment("pic.jpg", new File("C:\\Users\\Wang\\Pictures\\Saved Pictures\\pic.jpg"));

        //设置收发地址
        helper.setTo("715180879@qq.com");
        helper.setFrom("715180879@qq.com");

        mailSender.send(message);
    }

    /**
     *
     * @param html : whether enable use html in text
     * @param multipart : whether enable send multiFiles
     * @param Subject : title of the mail
     * @param text : content of the mail
     * @param fileName : name for the attachment files
     * @param filePath : path for the attachment files, use abstract path
     * @param to : send the mail to someone
     * @param from : the mail from someone
     * @author Wang Sky
     */
    public void sendMail(Boolean html, Boolean multipart, String Subject, String text, String[] fileName,
                         String[] filePath, String to, String from) throws MessagingException {
        //一个复杂的邮件, 可以通过mailSender的createMimeMessage方法创建一个MimeMessage
        MimeMessage message = mailSender.createMimeMessage();

        //组装, 利用SpringBoot提供的 MimeMessageHelper, boolean可以设置是否支持多文件
        MimeMessageHelper helper = new MimeMessageHelper(message,multipart);

        helper.setSubject(Subject);
        //设置True支持Html标签
        helper.setText(text, html);

        //附件, 使用绝对路径
        for (int i = 0; i < fileName.length; i++) {
            helper.addAttachment(fileName[i], new File(filePath[i]));
        }

        //设置收发地址
        helper.setTo(to);
        helper.setFrom(from);

        mailSender.send(message);
    }

    @Test
    void contextLoads3() throws MessagingException {

        String subject = "Hello My QQ Mail! Again!";
        String text = "<p style='color:red'>这里是正文!</p>";
        String fileName1 = "pic.jpg";
        String filePath1 = "C:\\Users\\Wang\\Pictures\\Saved Pictures\\pic.jpg";
        String[] fileName = {fileName1};
        String[] filePath = {filePath1};
        String to = "715180879@qq.com";
        String from = "715180879@qq.com";

        sendMail(true, true, subject, text, fileName, filePath, to, from);
    }

}
