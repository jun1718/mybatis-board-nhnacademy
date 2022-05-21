package com.nhnacademy.jdbc.board.web;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (Objects.isNull(session)) {
            return "redirect:/login";
        }

        session.invalidate();
        return "redirect:/login";
    }
}
