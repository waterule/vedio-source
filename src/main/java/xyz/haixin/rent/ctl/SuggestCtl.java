package xyz.haixin.rent.ctl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.haixin.rent.entity.Suggestion;
import xyz.haixin.rent.mapper.SuggestionMapper;
import xyz.haixin.rent.vo.SuggestVo;

@RestController
@RequestMapping("/suggest")
public class SuggestCtl {
    @Autowired
    SuggestionMapper mapper;
    @PostMapping("/submit")
    public String submitSuggest(@RequestBody SuggestVo req){
        Suggestion suggestion = new Suggestion();
        suggestion.setContent(req.getContent());
        suggestion.setMail(req.getMail());
        mapper.insert(suggestion);
        return "ok";

    }
}
