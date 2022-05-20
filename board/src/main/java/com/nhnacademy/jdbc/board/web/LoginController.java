package com.nhnacademy.jdbc.board.web;


import com.nhnacademy.jdbc.board.user.service.UserService;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final UserService userLoginService;

    public LoginController(UserService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @GetMapping(value = {"/", "/login"})
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if (Objects.isNull(session) || Objects.isNull(session.getAttribute("id"))) {
            return "loginForm";
        }

        model.addAttribute("id", session.getAttribute("id"));
        return "redirect:/showPosts";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("id") String id,
                          @RequestParam("pwd") String pwd,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          ModelMap modelMap) {

        if (userLoginService.checkUser(id, pwd).isEmpty()) {
            return "redirect:/login";
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("id", id);
        modelMap.put("id", id);

        return "redirect:/login";
    }
}
