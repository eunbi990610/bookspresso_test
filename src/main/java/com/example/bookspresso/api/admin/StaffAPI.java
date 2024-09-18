package com.example.bookspresso.api.admin;

import com.example.bookspresso.service.admin.ManageAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class StaffAPI {

    private final ManageAdminService manageAdminService;

    //관리자 승인 api
    @PostMapping("/change/staff/{preList}")
    public void changeStaff(@PathVariable ArrayList<Long> preList){
        for(Long admin : preList){
            manageAdminService.updateAdminStatus(admin);
        }
    }

    @PostMapping("/staff/revoke/{revokeList}")
    public void revokeStaff(@PathVariable ArrayList<Long> revokeList){
        for (Long admin : revokeList){
            manageAdminService.revokeStaff(admin);
        }
    }

}
