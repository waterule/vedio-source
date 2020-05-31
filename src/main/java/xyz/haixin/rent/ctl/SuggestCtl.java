package xyz.haixin.rent.ctl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.haixin.rent.entity.Suggestion;
import xyz.haixin.rent.entity.Visitor;
import xyz.haixin.rent.mapper.SuggestionMapper;
import xyz.haixin.rent.mapper.VisitorMapper;
import xyz.haixin.rent.vo.SuggestVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/suggest")
public class SuggestCtl {
    @Autowired
    SuggestionMapper mapper;

    @Autowired
    VisitorMapper visitorMapper;
    @PostMapping("/submit")
    public String submitSuggest(@RequestBody SuggestVo req){
        Suggestion suggestion = new Suggestion();
        suggestion.setContent(req.getContent());
        suggestion.setMail(req.getMail());
        mapper.insert(suggestion);
        return "ok";

    }

    @GetMapping("/visitor")
    public String count(HttpServletRequest request){

        String ip = request.getHeader("x-forwarded-for");
        Visitor visitor  = new Visitor();
        visitor.setIp(ip);
        List<Visitor> ip1 = visitorMapper.selectList(new QueryWrapper<Visitor>().eq("ip", ip));
        if(ip1.size()>0){
            visitor.setCounts(ip1.get(0).getCounts()+1);
            visitorMapper.update(visitor,new QueryWrapper<Visitor>().eq("ip",ip));
            return "ok";
        }
        visitorMapper.insert(visitor);
        return "ok";
    }


}
