package xyz.haixin.rent.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("rent_suggestion")
public class Suggestion {
    @TableId
    private int id;
    private String content;
    private String mail;
}
