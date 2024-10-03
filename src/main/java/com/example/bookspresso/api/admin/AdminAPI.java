package com.example.bookspresso.api.admin;

import com.example.bookspresso.service.admin.AdminService;
import com.example.bookspresso.service.admin.ManageMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class AdminAPI {

    private final AdminService adminService;
    private final ManageMemberService manageMemberService;

    @PostMapping("/login/findAdminId/{loginId}/{password}")
    public boolean login(@PathVariable String loginId,
                         @PathVariable String password) {
        try {
            adminService.findAdminId(loginId, password);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @PostMapping("/login/check/{loginId}")
    public boolean checkStatus(@PathVariable String loginId) {
        int status = adminService.findStatus(loginId);
        return (status==1);
    }


    //회원 삭제
    @PostMapping("admin/member/drop/{dropMemberLists}")
    public void dropMember(@PathVariable ArrayList<Long> dropMemberLists) {
        for(Long memberId : dropMemberLists){
            manageMemberService.dropMember(memberId);
        }
    }

    //





}
