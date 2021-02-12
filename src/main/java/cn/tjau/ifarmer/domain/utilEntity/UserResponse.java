package cn.tjau.ifarmer.domain.utilEntity;

import cn.tjau.ifarmer.domain.UserInfo;
import cn.tjau.ifarmer.domain.UserLogin;
import lombok.Data;

import java.util.Date;

@Data
public class UserResponse {
    private Integer uid;
    private String username;
    private String nickname;
    private String telephone;
    private String password;
    private String gender;
    private Date birthday;
    private String address;

    public UserResponse(UserInfo info, UserLogin login) {
        this.uid = info.getUid();
        this.username = info.getUsername();
        this.birthday = info.getBirthday();
        this.nickname = info.getNickname();
        this.telephone = info.getTelephone();
        this.address = info.getAddress();
        this.gender = info.getGender();
        this.password = login.getPassword();
    }

    public UserResponse() {}
}
