package com.example.bbs.presentation;

import ch.qos.logback.classic.Logger;
import com.example.bbs.application.form.UserForm;
import com.example.bbs.application.usecase.UserAuthUsecase;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;


@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserAuthUsecase userAuthUsecase;
    private final HttpServletRequest request; // DIでrequestを取得する
    private Logger log;

    @GetMapping
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("user/signup");
        modelAndView.addObject("userForm", new UserForm());
        return modelAndView;
    }

    @GetMapping("/signup")
    public ModelAndView signup(ModelAndView modelAndView) {
        modelAndView.setViewName("user/signup");
        modelAndView.addObject("userForm", new UserForm());
        return modelAndView;
    }

    @PostMapping("/signup")
    public ModelAndView register(
            @Validated @ModelAttribute UserForm userForm,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("user/signup");
            modelAndView.addObject("userForm", userForm);
            return modelAndView;
        }

        try {
            userAuthUsecase.userCreate(userForm, request);
        }catch (Exception e) {
            log.error("ユーザ作成 or ログイン失敗", e);
            return new ModelAndView("redirect:/user");
        }

        return new ModelAndView("redirect:/board");
    }
}
