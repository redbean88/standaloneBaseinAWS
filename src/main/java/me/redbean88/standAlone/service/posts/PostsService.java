package me.redbean88.standAlone.service.posts;

import lombok.RequiredArgsConstructor;
import me.redbean88.standAlone.domain.posts.Posts;
import me.redbean88.standAlone.domain.posts.PostsRepository;
import me.redbean88.standAlone.web.dto.PostsResponseDto;
import me.redbean88.standAlone.web.dto.PostsSaveRequestDto;
import me.redbean88.standAlone.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor // final 생성자
@Service
public class PostsService {

    private final PostsRepository postsRepository;


    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. Id="+id));
        posts.update(requestDto.getTitle(),requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. Id="+id));
        return new PostsResponseDto(entity);
    }
}
