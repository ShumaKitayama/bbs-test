package com.example.bbs.application.usecase;

import com.example.bbs.application.form.CommentForm;
import com.example.bbs.domain.model.UserComment;
import com.example.bbs.domain.model.UserCommentRepository;
import com.example.bbs.domain.model.UserComments;
import com.example.bbs.domain.model.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCommentUseCase {
    private final UserCommentRepository repository;

    /**
     * ユーザの書き込みをDBに反映し、表示するデータをプレゼンテーション層に渡す
     *
     * @param commentForm ユーザの入力データ
     * @param user
     * @return 表示するデータ
     */
    public void write(CommentForm commentForm, User user) {
        // フォームオブジェクトからドメインオブジェクトへ変換
        UserComment userComment = UserComment.from(
                commentForm.getName(),
                user.getUsername(),
                commentForm.getMailAddress(),
                commentForm.getComment()
        );

        // 例えばここで、直近の投稿の一覧を取得し、今回と同じ内容の投稿がないかチェックする

        repository.save(userComment);
    }
    /**
     * 投稿の取得
     * @return 投稿のリスト
     */
    public UserComments read(UserId userId) {
        return repository.select(userId);
    }

    public UserComments read() {
        return repository.select();
    }
}