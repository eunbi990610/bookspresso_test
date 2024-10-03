package com.example.bookspresso.controller.admin;

import com.example.bookspresso.dto.admin.page.AdminPageRequestDTO;
import com.example.bookspresso.dto.admin.page.AdminPageSetDTO;
import com.example.bookspresso.dto.admin.poster.PosterManageDTO;
import com.example.bookspresso.dto.admin.poster.PosterSearchDTO;
import com.example.bookspresso.dto.debate.page.PageRequestDTO;
import com.example.bookspresso.dto.debate.page.PageSetDTO;
import com.example.bookspresso.mapper.admin.PosterManageMapper;
import com.example.bookspresso.service.admin.AdminService;
import com.example.bookspresso.service.admin.PosterManageService;
import com.example.bookspresso.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/poster")
@RequiredArgsConstructor
public class PosterController {

    private final PosterManageService posterManageService;
//    private final AdminService adminService;

    //공개된 포스터
    @GetMapping("/public")
    public String publicPoster(AdminPageRequestDTO adminPageRequestDTO,
                               Model model){

        int total = posterManageService.findPosterCount();
        List<PosterManageDTO> list = posterManageService.findPosterList(adminPageRequestDTO);

        AdminPageSetDTO adminPageSetDTO = new AdminPageSetDTO(adminPageRequestDTO, total);

        model.addAttribute("total", total);
        model.addAttribute("list", list);
        model.addAttribute("adminPageSetDTO", adminPageSetDTO);



        return "admin/poster/publicPoster";
    }

    @GetMapping("/public/search")
    public String publicSearchPoster(AdminPageRequestDTO adminPageRequestDTO,
                                     String searchType, String keyword,
                                     Model model){

        int total = posterManageService.findSearchPosterCount(searchType, keyword);
        List<PosterManageDTO> list = posterManageService.findSearchPosterList(searchType, keyword,adminPageRequestDTO.getPage(), adminPageRequestDTO.getAmount());

        AdminPageSetDTO adminPageSetDTO = new AdminPageSetDTO(adminPageRequestDTO, total);

        model.addAttribute("total", total);
        model.addAttribute("list", list);
        model.addAttribute("adminPageSetDTO",adminPageSetDTO);
        model.addAttribute("keyword",keyword);
        model.addAttribute("searchType",searchType);
        return "admin/poster/publicPoster";
    }


    //비공개된 포스터
//    @GetMapping("/private")
//    public String privatePoster(){
//        return "admin/poster/privatePoster";
//    }
//
//
//    @GetMapping("/drop")
//    public String dropPoster(){
//        return "admin/poster/dropPoster";
//    }

}
