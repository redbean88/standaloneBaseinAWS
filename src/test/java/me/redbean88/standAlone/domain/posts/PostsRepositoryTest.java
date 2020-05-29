package me.redbean88.standAlone.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll(); //reset datas
    }

    @Test
    public void 게시글저장_불러오기_삭제() throws Exception{
        //given
        String title ="테스트 게시물";
        String contents = "테스트 게시물 내용";
        String author= "redbean88@gmail.com";

        //insert or update
        postsRepository.save(Posts.builder()
                .title(title)
                .content(contents)
                .author(author)
                .build());

        //when

        List<Posts> postsList = postsRepository.findAll(); //read All

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(contents);
        assertThat(posts.getAuthor()).isEqualTo(author);

    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        String title ="테스트 게시물";
        String contents = "테스트 게시물 내용";
        String author= "redbean88@gmail.com";

        LocalDateTime now = LocalDateTime.of(2020,5,29,0,0,0);
        postsRepository.save(Posts.builder()
                .title(title)
                .content(contents)
                .author(author)
                .build());
        //when
        List<Posts> all = postsRepository.findAll();

        //then
        Posts posts = all.get(0);

        System.out.println(">>>createDate"+posts.getCreatedDate()+
                            ">>>ModifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }

}