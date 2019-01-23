package info.hongshu.quokka.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

import static java.util.Collections.emptyList;

@Getter
@Setter
public class Member extends User {

    private long id;

    public Member(String username, String password) {
        super(username, password, emptyList());
    }

}
