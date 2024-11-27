package com.example.bbs.infrastructure.datasource;

import com.example.bbs.application.dto.UserCommentReadDto;
import com.example.bbs.domain.model.UserComment;
import com.example.bbs.application.dto.UserCommentDto;
import com.example.bbs.domain.model.UserCommentRepository;
import com.example.bbs.domain.model.UserComments;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.example.bbs.domain.model.UserId;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class UserCommentDatasource implements UserCommentRepository {
    private final UserCommentMapper mapper;

    @Override
    public void save(UserComment userComment) {
        mapper.insert(UserCommentDto.from(userComment));
    }

    @Override
    public UserComments select() {
        List<UserCommentReadDto> dtos = mapper.select();
        return convert(dtos);
    }

    @Override
    public UserComments select(UserId userId) {
        List<UserCommentReadDto> dtos = mapper.selectById(userId.toString());
        return convert(dtos);
    }

    private UserComments convert(List<UserCommentReadDto> dtos) {
        return UserComments.from(
                dtos.stream().map( dto -> UserComments.UserComment.from(
                        dto.getId(),
                        dto.getUserId(),
                        dto.getName(),
                        dto.getMailAddress(),
                        dto.getComment(),
                        dto.getCreatedAt()
                )).collect(Collectors.toUnmodifiableList())
        );
    }
}