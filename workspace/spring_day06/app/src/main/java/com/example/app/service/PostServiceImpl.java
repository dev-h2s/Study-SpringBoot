package com.example.app.service;

import com.example.app.dao.PostDAO;
import com.example.app.domain.dto.PostDTO;
import com.example.app.domain.vo.PostVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostDAO postDAO;

    @Override
    public List<PostDTO> getList() {
        return postDAO.findAll();
    }
//    public List<QuestionVO> findAll(){
//        return postMapper.selectAll();
//    }
    @Override
    public void write(PostVO postVO) {
        postDAO.save(postVO);
    }

    @Override
    public Optional<PostDTO> read(Long id) {
        return postDAO.findById(id);
    }

    @Override
    public void modify(PostVO postVO) {
        postDAO.setPostDTO(toDTO(postVO));
    }

    @Override
    public void remove(Long id) {
        postDAO.delete(id);
    }
}
