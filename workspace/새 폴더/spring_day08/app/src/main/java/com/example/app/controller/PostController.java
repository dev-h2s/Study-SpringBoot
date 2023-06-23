package com.example.app.controller;

import com.example.app.domain.dto.Pagination;
import com.example.app.domain.dto.PostDTO;
import com.example.app.domain.dto.Search;
import com.example.app.domain.vo.PostVO;
import com.example.app.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/post/*")
public class PostController {
    private final PostService postService;

    @GetMapping("list")
    public void list(int page, Search search, Model model){
        final Pagination pagination = new Pagination(3);
        pagination.setPage(page);
        model.addAttribute("posts", postService.getList(pagination, search));
    }

    @GetMapping("write")
    public void goToWriteForm(PostVO postVO){;}

    @PostMapping("write")
    public RedirectView write(PostDTO postDTO){
        postService.write(postDTO);
        return new RedirectView("/post/list");
    }

    @GetMapping(value = {"read", "modify"})
    public void read(Long id, Model model){
        model.addAttribute("post", postService.read(id));
    }

    @PostMapping("modify")
    public RedirectView modify(PostDTO postDTO, RedirectAttributes redirectAttributes){
        postService.modify(postDTO);
        redirectAttributes.addAttribute("id", postDTO.getId());
        return new RedirectView("/post/read");
    }

    @PostMapping("remove")
    public RedirectView remove(Long id){
        postService.remove(id);
        return new RedirectView("/post/list");
    }
}





















