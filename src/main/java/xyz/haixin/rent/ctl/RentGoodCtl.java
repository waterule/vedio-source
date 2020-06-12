package xyz.haixin.rent.ctl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.haixin.rent.entity.RentGoods;
import xyz.haixin.rent.mapper.RentGoodsMapper;
import xyz.haixin.rent.vo.GoodsVo;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class RentGoodCtl {
    @Autowired
    RentGoodsMapper mapper;
    @PostMapping("/saveGoods")
    public int saveGoods(@RequestBody GoodsVo req){
        RentGoods goods = new RentGoods();
        goods.setDetail(req.getDetail());
        goods.setImg(req.getImg());
        goods.setPrice(req.getPrice());
        goods.setUserId(req.getUserId());
        return mapper.insert(goods);
    }
    @GetMapping("/getGoods")
    public List<RentGoods> getGoods(){
        List<RentGoods> rentGoods = mapper.selectList(new QueryWrapper<RentGoods>()
                .eq("is_use",1));
        return rentGoods;
    }
    @GetMapping("/getGood")
    public RentGoods getGood(@RequestParam("id") int id){
        RentGoods goods = mapper.selectById(id);
        return goods;
    }
    @GetMapping("/getMyGoods")
    public List<RentGoods> getMyGoods(@RequestParam("userId") String userId){
        List<RentGoods> rentGoods = mapper
                .selectList(new QueryWrapper<RentGoods>().
                        eq("user_id",userId).eq("is_use",1));
        return rentGoods;
    }
    @GetMapping("/deleteGood")
    public int deleteGoods(@RequestParam("goodId") String goodId){
        RentGoods goods = mapper.selectById(goodId);
        goods.setIsUse(0);
        return mapper.update(goods,new QueryWrapper<RentGoods>());
    }


}
