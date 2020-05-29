package me.redbean88.standAlone.web;

import lombok.RequiredArgsConstructor;
import me.redbean88.standAlone.service.posts.PostsService;
import me.redbean88.standAlone.web.dto.PostsResponseDto;
import me.redbean88.standAlone.web.dto.PostsSaveRequestDto;
import me.redbean88.standAlone.web.dto.PostsUpdateRequestDto;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor // final 생성자
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto ){

        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findbyId (@PathVariable Long id){

        return  postsService.findById(id);
    }
}
