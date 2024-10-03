package com.example.bookspresso.controller.admin;

import com.example.bookspresso.dto.admin.member.MemberManageDTO;
import com.example.bookspresso.dto.admin.member.MemberSearchDTO;
import com.example.bookspresso.dto.admin.page.AdminPageRequestDTO;
import com.example.bookspresso.dto.admin.page.AdminPageSetDTO;
import com.example.bookspresso.service.admin.ManageMemberService;
import com.example.bookspresso.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/member")
@RequiredArgsConstructor
public class AdMemberController {

    private final ManageMemberService manageMemberService;

    // 전체 회원 관리
    @GetMapping("/allList")
    public String totalList(AdminPageRequestDTO adminPageRequestDTO, Model model){

        List<MemberManageDTO> list = manageMemberService.findMemberList(adminPageRequestDTO);
        int total = manageMemberService.findListCount();

        AdminPageSetDTO adminPageSetDTO = new AdminPageSetDTO(adminPageRequestDTO, total);

        model.addAttribute("total", total);
        model.addAttribute("list", list);
        model.addAttribute("adminPageSetDTO", adminPageSetDTO);


        return "admin/member/allList";
    }

    @GetMapping("/allList/search")
    public String searchList(AdminPageRequestDTO adminPageRequestDTO,
                             MemberSearchDTO memberSearchDTO,
                             Model model){
        List<MemberManageDTO> list = manageMemberService.findSearchMemberList(adminPageRequestDTO.getPage(), adminPageRequestDTO.getAmount(),
                memberSearchDTO.getSearchType(), memberSearchDTO.getKeyword());
        int searchTotal = manageMemberService.findSearchCount(memberSearchDTO.getSearchType(), memberSearchDTO.getKeyword());

        AdminPageSetDTO adminPageSetDTO = new AdminPageSetDTO(adminPageRequestDTO, searchTotal);

        int total = manageMemberService.findListCount();

        model.addAttribute("total", total);
        model.addAttribute("list", list);
        model.addAttribute("adminPageSetDTO", adminPageSetDTO);
        model.addAttribute("keyword", memberSearchDTO.getKeyword());

        return "admin/member/allList";
    }

    // 활동 중지 회원 관리
    @GetMapping("/stop-activity")
    public String stopActivity(){

        return "admin/member/stopActivity";
    }

    // 강제 탈퇴된 회원 명단
    @GetMapping("/forced-out")
    public String forcedOut(){
        return "admin/member/forcedOut";
    }






}
