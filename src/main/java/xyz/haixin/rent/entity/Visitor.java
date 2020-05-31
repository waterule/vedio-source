package xyz.haixin.rent.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("rent_visitor")
public class Visitor {
    @TableId
    private int id;
    private String ip;
    private int counts;
}
