package xyz.haixin.rent.vo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UserVo {
    private String name;
    private String password;
    private String mail;
    private LocalDateTime LoginTime;
}
