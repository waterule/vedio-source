package xyz.haixin.rent.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("rent_file")
public class FileRent {
    private int id;
    private String fileName;
    private int goodsId;
    private LocalDateTime createTime;
}
