package com.example.bookspresso.service.admin;

import com.example.bookspresso.dto.admin.admin.AdminDTO;
import com.example.bookspresso.dto.admin.admin.WaitingAdminDTO;
import com.example.bookspresso.dto.admin.page.AdminPageRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ManageAdminServiceTest2 {

    @Autowired
    ManageAdminService manageAdminService;
    AdminPageRequestDTO adminPageRequestDTO;
    AdminDTO adminDTO;
    @BeforeEach
    void setUp() {
        adminDTO = AdminDTO.builder()
                .adminLoginId("aaaa")
                .adminPassword("dddd")
                .email("emksdlf@gmail.com")
                .name("짱구구")
                .build();

        adminPageRequestDTO = new AdminPageRequestDTO();
        adminPageRequestDTO.setPage(1);
        adminPageRequestDTO.setAmount(5);
    }

    @Test
    void addAdmin() {

        manageAdminService.addAdmin(adminDTO);
        System.out.println("@@@@"+adminDTO.getAdminId());
    }

    @Test
    void findWaitingAdminDTOList() {
        manageAdminService.addAdmin(adminDTO);
        System.out.println("@@@@"+adminDTO.getAdminId());

        List<WaitingAdminDTO> list = manageAdminService.findWaitingAdminDTOList(adminPageRequestDTO);
        System.out.println("list = " + list);
    }
    @Test
    void waitingAdminDTOList() {
        int count = manageAdminService.waitingAdminCount();
        System.out.println("#### count = " + count);
    }

    @Test
    void findAdminDTOList() {
        List<AdminDTO> list = manageAdminService.findAdminDTOList("김", "name", adminPageRequestDTO.getPage(), adminPageRequestDTO.getAmount());
        System.out.println("list = " + list);
        int count = manageAdminService.searchAdminListCount("김", "name", adminPageRequestDTO.getPage(), adminPageRequestDTO.getAmount());
        System.out.println("count = " + count);
    }

    @Test
    void updateAdminStatus() {
//        manageAdminService.addAdmin(adminDTO);
//        System.out.println("@@@@"+adminDTO.getAdminId());
//        manageAdminService.updateAdminStatus(adminDTO.getAdminId());
//        System.out.println("####" + adminDTO.isStatus());
//        List<AdminDTO> lists = manageAdminService.findAdminDTOList();
//        System.out.println("lists = " + lists);
    }

    @Test
    void deleteAdmin() {
        manageAdminService.addAdmin(adminDTO);
        manageAdminService.updateAdminStatus(adminDTO.getAdminId());

        List<AdminDTO> list = manageAdminService.findAdminDTOList(null, null, adminPageRequestDTO.getPage(), adminPageRequestDTO.getAmount());
        System.out.println("list = " + list);
        manageAdminService.revokeStaff(adminDTO.getAdminId());
        List<AdminDTO> afterList = manageAdminService.findAdminDTOList(null, null, adminPageRequestDTO.getPage(), adminPageRequestDTO.getAmount());
        System.out.println("afterList = " + afterList);

    }




}