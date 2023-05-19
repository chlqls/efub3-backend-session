package efub.session.blog.domain.comment.domain;

import efub.session.blog.domain.account.domain.Account;
import efub.session.blog.domain.heart.domain.CommentHeart;
import efub.session.blog.domain.post.domain.Post;
import efub.session.blog.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false, length = 500)
    private String content;

    // 양방향 다대일(Comment – Post)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false, updatable = false)
    private Post post;

    // 단방향 다대일(Comment – Account)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false, updatable = false)
    private Account writer;

    // 양방향 일대다(Comment - Heart)
    // 2. comment domain에 엔티티 매핑
    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CommentHeart> commentHeartList = new ArrayList<>();

    @Builder
    public Comment(String content, Post post, Account writer) {
        this.content = content;
        this.post = post;
        this.writer = writer;
    }

    public void updateComment(String content) {
        this.content = content;
    }
}
