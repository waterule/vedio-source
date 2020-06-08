package xyz.haixin.rent.ctl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.haixin.rent.entity.RentUser;
import xyz.haixin.rent.mapper.RentUserMapper;
import xyz.haixin.rent.vo.UserCheckVo;
import xyz.haixin.rent.vo.UserVo;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
public class RentUserCtl {
    @Autowired
    RentUserMapper rentUserMapper;
    @PostMapping("/saveUser")
    public String  saveUser(UserVo req){
        RentUser user = new RentUser();
        user.setLoginTime(LocalDateTime.now());
        user.setName(req.getName());
        user.setMail(req.getMail());
        user.setPassword(req.getPassword());
        try {
            rentUserMapper.insert(user);
        }catch (Exception e){
            return "fail";
        }
        return "ok";
    }
    @PostMapping("/checkUser")
    public String checkUser(UserCheckVo req){
        List<RentUser> rentUsers = rentUserMapper.selectList(new QueryWrapper<RentUser>()
                .eq("name", req.getName()).eq("password", req.getPassword()));
        if(rentUsers != null && rentUsers.size()>0){
            return "1";
        }
        return "0";
    }
}
