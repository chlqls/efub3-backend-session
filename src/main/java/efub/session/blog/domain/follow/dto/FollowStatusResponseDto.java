package efub.session.blog.domain.follow.dto;

import efub.session.blog.domain.account.domain.Account;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 어노테이션 추가
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowStatusResponseDto {
    private Long accountId;
    private String nickname;
    private String email;
    private String status;

    @Builder
    public FollowStatusResponseDto(Account account, Boolean isFollow) {
        this.accountId = account.getAccountId();
        this.nickname = account.getNickname();
        this.email = account.getEmail();

        if(!isFollow){
            this.status = "UNFOLLOW";
        }else{
            this.status = "FOLLOW";
        }
    }
}