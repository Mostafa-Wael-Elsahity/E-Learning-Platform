package com.example.elearningplatform.resetpassword;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.elearningplatform.Response;
import com.example.elearningplatform.email.EmailService;
import com.example.elearningplatform.user.User;
import com.example.elearningplatform.user.UserRepository;
import com.example.elearningplatform.verficationtoken.VerficationTokenService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResetPasswordService {

    private final VerficationTokenService verficationTokenService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    /******************************************************************************************************************/

    public String resetPassword(String email) throws MessagingException, IOException, SQLException {

        User user = userRepository.findByEmail(email).orElse(null);
        String token = verficationTokenService.create(user);
        return token;
    }

    /********************************************************************************************************************/
    public Response savePassword(User user, String password)
            throws SQLException, IOException, MessagingException {
        try {
            user.setPassword(passwordEncoder.encode(password)); // encoded password);
            userRepository.save(user);
            return new Response(HttpStatus.OK, "Password Changed Successfully", null);
        } catch (Exception e) {
            return new Response(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }

    /********************************************************************************************************************/

    public Response sendResetpasswordEmail(User user, HttpServletRequest request, String verficationToken) {

        try {

            String url = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()
                    + "/check-token?token=" + verficationToken;

            System.out.println("url : " + url);

            String subject = "Reset Password Verification";

            String senderName = "User Registration Portal Service";

            String content = "<p> Hi, " + user.getFirstName() + ", </p>" +
                    "<p>Thank you for registering with us," + "" +
                    "Please, follow the link below to complete your registration.</p>" +
                    "<a href=\"" + url + "\">Reset password</a>" +
                    "<p> Thank you <br> Reset Password Portal Service";

            emailService.sendEmail(user, content, subject, senderName);

            return new Response(HttpStatus.OK, "  Please, check your email to reset your password", null);

        } catch (Exception e) {
            return new Response(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }
    /********************************************************************************************************************/

}