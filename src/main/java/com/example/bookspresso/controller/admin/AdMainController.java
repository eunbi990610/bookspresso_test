package com.example.bookspresso.controller.admin;

import com.example.bookspresso.service.admin.AdminService;
import com.example.bookspresso.service.admin.ManageDebateService;
import com.example.bookspresso.service.admin.ManageMemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdMainController {

    private final AdminService adminService;
    private final ManageMemberService manageMemberService;
    private final ManageDebateService manageDebateService;

    @GetMapping("/join")
    public String join(HttpSession session){
        if(session.getAttribute("adminId")!=null){
            return "redirect:/admin/main";
        }
        return "admin/main/join";
    }

    @GetMapping("/login")
    public String login(){
        return "admin/main/login";
    }

    @PostMapping("/login")
    public String login(String adminLoginId, String adminPassword,
                        HttpSession session){
        Long adminId = null;

        try {
            adminId = adminService.findAdminId(adminLoginId, adminPassword);
        } catch (IllegalStateException e) {
            System.out.println("존재하지 않는 관리자 정보");
        } catch (Exception e) {
            System.out.println("예상치 못한 예외");
        }

        session.setAttribute("adminId", adminId);

        return "redirect:/admin/main";
    }


    @GetMapping("/main")
    public String mainPage(HttpSession session, Model model){

        Long adminId = (Long)session.getAttribute("adminId");
        System.out.println("adminId = " + adminId);

        String adminLoginId = adminService.findAdminLoginId(adminId);
        int memberCount = manageMemberService.findListCount();
        int debateCount = manageDebateService.findDebateCount() + manageDebateService.findEndDebateCount() + manageDebateService.findRecruitingDebateCount();



        model.addAttribute("adminId", adminId);
        model.addAttribute("adminLoginId", adminLoginId);
        model.addAttribute("memberCount", memberCount);
        model.addAttribute("debateCount", debateCount);
        return "admin/main/mainPage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/admin/login";
    }






}
