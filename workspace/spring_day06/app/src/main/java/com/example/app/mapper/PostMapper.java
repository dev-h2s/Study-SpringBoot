package com.example.app.mapper;

import com.example.app.domain.dto.PostDTO;
import com.example.app.domain.vo.MemberVO;
import com.example.app.domain.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {
    //    게시글 목록
    public List<PostDTO> selectAll();

    //    게시글 추가
    public void insert(PostVO postVO);

    //    게시글 조회
    public Optional<PostDTO> select(Long id);

    //    게시글 수정
    public void update(PostDTO postDTO);

    //    게시글 삭제
    public void delete(Long id);
}
