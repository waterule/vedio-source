package xyz.haixin.rent.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@TableName("rent_user")
public class RentUser {
    private int id;
    private String name;
    private String password;
    private LocalDateTime loginTime;
    private String mail;
}
