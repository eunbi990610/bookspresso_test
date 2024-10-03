package com.example.bookspresso.controller.admin;

import com.example.bookspresso.dto.admin.admin.AdminDTO;
import com.example.bookspresso.dto.admin.admin.WaitingAdminDTO;
import com.example.bookspresso.dto.admin.page.AdminPageRequestDTO;
import com.example.bookspresso.dto.admin.page.AdminPageSetDTO;
import com.example.bookspresso.dto.admin.poster.PosterSearchDTO;
import com.example.bookspresso.service.admin.ManageAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/staff")
public class AdminCotroller {

    private final ManageAdminService manageAdminService;

    @GetMapping("/standBy/list")
    public String standByList(AdminPageRequestDTO adminPageRequestDTO,
                              Model model){
        int total = manageAdminService.waitingAdminCount();
        List<WaitingAdminDTO> list = manageAdminService.findWaitingAdminDTOList(adminPageRequestDTO);
        AdminPageSetDTO adminPageSetDTO = new AdminPageSetDTO(adminPageRequestDTO, total);
        System.out.println("adminPageSetDTO = " + adminPageSetDTO);
        model.addAttribute("list", list);
        model.addAttribute("adminPageSetDTO", adminPageSetDTO);
        model.addAttribute("total", total);
        return "admin/admin/standByList";
    }

    @GetMapping("/list")
    public String staffList(AdminPageRequestDTO adminPageRequestDTO,
                            PosterSearchDTO posterSearchDTO,
                            Model model){

        int total = manageAdminService.searchAdminListCount(
                posterSearchDTO.getKeyword(),
                posterSearchDTO.getSearchType(),
                adminPageRequestDTO.getPage(),
                adminPageRequestDTO.getAmount());
        System.out.println("####total = " + total);

        List<AdminDTO> list = manageAdminService.findAdminDTOList(
                posterSearchDTO.getKeyword(),
                posterSearchDTO.getSearchType(),
                adminPageRequestDTO.getPage(),
                adminPageRequestDTO.getAmount());
        System.out.println("list = " + list);
        System.out.println("adminPageRequestDTO = " + adminPageRequestDTO + ", posterSearchDTO = " + posterSearchDTO);
        AdminPageSetDTO adminPageSetDTO = new AdminPageSetDTO(adminPageRequestDTO, total);
        System.out.println("adminPageSetDTO.prev = " + adminPageSetDTO.isPrev());

        model.addAttribute("adminPageSetDTO", adminPageSetDTO);
        model.addAttribute("list", list);
        model.addAttribute("total", total);
        model.addAttribute("keyword", posterSearchDTO.getKeyword());




        return "admin/admin/staffList";
    }


}
