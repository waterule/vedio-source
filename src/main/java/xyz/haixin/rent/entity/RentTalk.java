package xyz.haixin.rent.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("rent_talk")
public class RentTalk {
    private int id;
    private int userId;
    private int goodId;
    private String content;
    private String contact;
    private int isUse;
}
