package com.example.bookspresso.controller.admin;

import com.example.bookspresso.service.admin.AdminService;
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

    @GetMapping("/join")
    public String join(){
        return "admin/main/adminJoin";
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
    public String main(@SessionAttribute("adminId") Long adminId,
                       Model model){

        String adminLoginId = adminService.findAdminLoginId(adminId);

        model.addAttribute("adminId", adminId);
        model.addAttribute("adminLoginId", adminLoginId);
        return "admin/main/mainPage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/admin/login";
    }






}
