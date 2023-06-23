package com.example.app.service;

import com.example.app.dao.FileDAO;
import com.example.app.dao.PostDAO;
import com.example.app.dao.ReplyDAO;
import com.example.app.domain.dto.Pagination;
import com.example.app.domain.dto.PostDTO;
import com.example.app.domain.dto.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostDAO postDAO;
    private final ReplyDAO replyDAO;
    private final FileDAO fileDAO;

    @Override
        @Transactional(rollbackFor = Exception.class)
    public List<PostDTO> getList(Pagination pagination, Search search) {
//        게시글 전체 목록
        final List<PostDTO> posts = postDAO.findAll(pagination, search);

//        게시글 하나씩 첨부파일 목록 담기
        posts.forEach(post -> post.setFiles(fileDAO.findAll(post.getId())));
        return posts;
    }









    @Override
    @Transactional(rollbackFor = Exception.class)
    public void write(PostDTO postDTO) {
        postDAO.save(postDTO);
        postDTO.getFiles().forEach(file -> {
            file.setPostId(postDTO.getId());
            fileDAO.save(file);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<PostDTO> read(Long id) {
        final Optional<PostDTO> foundPost = postDAO.findById(id);
        if(foundPost.isPresent()){
            foundPost.get().setFiles(fileDAO.findAll(foundPost.get().getId()));
        }
        return foundPost;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(PostDTO postDTO) {
        postDAO.setPostDTO(postDTO);
//        추가
        postDTO.getFiles().forEach(file -> {
            file.setPostId(postDTO.getId());
            fileDAO.save(file);
        });
//        삭제
        postDTO.getFileIdsForDelete().forEach(fileDAO::delete);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Long id) {
        postDAO.delete(id);
        replyDAO.deleteAll(id);
        fileDAO.deleteAll(id);
    }

    @Override
    public int getTotal(Search search) {
        return postDAO.findCountOfPost(search);
    }
}

















