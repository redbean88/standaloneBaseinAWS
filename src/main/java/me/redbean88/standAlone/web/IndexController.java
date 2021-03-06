package me.redbean88.standAlone.web;

import lombok.RequiredArgsConstructor;
import me.redbean88.standAlone.config.auth.LoginUser;
import me.redbean88.standAlone.config.auth.dto.SessionUser;
import me.redbean88.standAlone.domain.posts.PostsRepository;
import me.redbean88.standAlone.service.posts.PostsService;
import me.redbean88.standAlone.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor //final 생성자
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts",postsService.findAllDesc());

        if(user != null) model.addAttribute("userName",user.getName());
        return "index";
    }


    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id , Model model){

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";

    }


}
