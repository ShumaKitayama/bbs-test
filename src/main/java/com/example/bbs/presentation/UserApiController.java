package com.example.bbs.presentation;

import com.example.bbs.application.dto.UserCommentReadDto;
import com.example.bbs.application.usecase.UserCommentUseCase;
import com.example.bbs.domain.model.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserApiController {
    private final UserCommentUseCase useCase;

    @GetMapping("/my")
    public List<UserCommentReadDto> myComments( @AuthenticationPrincipal User user) {
        return UserCommentReadDto.from(useCase.read(UserId.from(user.getUsername())));
    }
}