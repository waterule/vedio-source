package xyz.haixin.rent.ctl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.haixin.rent.entity.Suggestion;
import xyz.haixin.rent.entity.Visitor;
import xyz.haixin.rent.mapper.SuggestionMapper;
import xyz.haixin.rent.mapper.VisitorMapper;
import xyz.haixin.rent.vo.SuggestVo;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
        suggestion.setGoodId(req.getGoodId());
        suggestion.setUserId(req.getUserId());
        mapper.insert(suggestion);
        return "ok";

    }

    @GetMapping("/visitor")
    public String count(HttpServletRequest request){

        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null ){
            ip = request.getHeader("X-Real-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if(ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")){
            //根据网卡取本机配置的IP
            InetAddress inet=null;
            try {
                inet = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            ip= inet.getHostAddress();
        }
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

    @GetMapping("/visitorList")
    public String getListVisit(){
        List<Visitor> visitors = visitorMapper.selectList(new QueryWrapper<Visitor>());
        List<Suggestion> suggestions = mapper.selectList(new QueryWrapper<Suggestion>());
        String visit = JSONArray.toJSONString(visitors);
        String suggest = JSONArray.toJSONString(suggestions);
        return visit +"-------" + suggest;
    }

    @GetMapping("/getMessage")
    public List<Suggestion> getSuggs(@RequestParam("userId") String userId){
        List<Suggestion> suggestions = mapper.
                selectList(new QueryWrapper<Suggestion>().eq("user_id",userId));
        return suggestions;
    }

}
