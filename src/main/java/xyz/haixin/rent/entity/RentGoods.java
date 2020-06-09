package xyz.haixin.rent.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("rent_goods")
public class RentGoods {
    private int id;
    private int price;
    private String detail;
    private String img;
    private int userId;
}
