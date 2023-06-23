package com.example.app.mapper;

import com.example.app.domain.dto.PostDTO;
import com.example.app.domain.vo.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class PostMapperTests {
    @Autowired
    private PostMapper postMapper;

    @Test
    public void insertTest(){
        PostVO postVO = new PostVO();
        postVO.setMemberId(21L);
        postVO.setPostTitle("테스트 제목2");
        postVO.setPostContent("테스트 내용2");
        postMapper.insert(postVO);
    }

    @Test
    public void selectAllTest(){
        assertThat(postMapper.selectAll()).hasSize(2);
    }

    @Test
    public void selectTest(){
        postMapper.select(2L).map(PostDTO::getMemberName).ifPresent(log::info);
    }

    @Test
    public void updateTest(){
        postMapper.select(2L).ifPresent(postDTO -> {
            postDTO.setPostContent("수정된 내용");
            postMapper.update(postDTO);
        });
    }

    @Test
    public void deleteTest(){
        postMapper.delete(2L);
        assertThat(postMapper.selectAll()).hasSize(1);
    }

}















