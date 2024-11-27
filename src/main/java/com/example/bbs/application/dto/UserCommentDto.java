package com.example.bbs.application.dto;

import com.example.bbs.domain.model.UserComment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserCommentDto {
    private final String name;
    private final String userId;
    private final String mailAddress;
    private final String comment;

    public static UserCommentDto from(
            UserComment userComment) {
        return new UserCommentDto(
                userComment.getName().toString(),
                userComment.getUserId().toString(),
                userComment.getMailAddress().toString(),
                userComment.getComment().toString()
        );
    }
}
