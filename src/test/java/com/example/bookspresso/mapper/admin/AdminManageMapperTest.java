package com.example.bookspresso.mapper.admin;

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
class AdminManageMapperTest {

    @Autowired AdminManageMapper adminManageMapper;
    WaitingAdminDTO waitingAdminDTO;
    AdminPageRequestDTO adminPageRequestDTO;
    AdminDTO adminDTO1;


    @BeforeEach
    void setUp() {
        adminDTO1 = AdminDTO.builder()
                .name("이이이")
                .adminPassword("123456")
                .adminLoginId("admin1111")
                .createdDate("2024-09-14")
                .email("sldfksd@gmail.com")
                .build();

        adminPageRequestDTO = new AdminPageRequestDTO();
        adminPageRequestDTO.setPage(1);
        adminPageRequestDTO.setAmount(5);
    }


    @Test
    void insertAdmin() {


    }

    @Test
    void selectWaitingAdminList() {
        adminManageMapper.insertAdmin(adminDTO1);
        List<WaitingAdminDTO> waitingAdminDTOS = adminManageMapper.selectWaitingAdminList(adminPageRequestDTO);
        System.out.println("waitingAdminDTOS = " + waitingAdminDTOS);
    }
    @Test
    void waitingAdminCount(){
        int count = adminManageMapper.waitingAdminCount();
        System.out.println("####### count = " + count);
    }

    @Test
     void updateStatus() {
        adminManageMapper.insertAdmin(adminDTO1);
        System.out.println("adminID = "+ adminDTO1.isStatus());
        adminManageMapper.updateStatus(adminDTO1.getAdminId());
        System.out.println("adminID = "+ adminDTO1.isStatus());
        System.out.println("@@adminDTO1 = " + adminDTO1);
//
//        List<AdminDTO> adminDTOS = adminManageMapper.selectAdminList();
//        System.out.println("adminDTOS = " + adminDTOS);
    }
    @Test
    void updateApprovalDate(){
        adminManageMapper.insertAdmin(adminDTO1);
        adminManageMapper.updateApprovalDate(adminDTO1.getAdminId());
    }

    @Test
    void selectAdminList() {
//        List<AdminDTO> list = adminManageMapper.selectAdminList("김", "name", adminPageRequestDTO.getPage(), adminPageRequestDTO.getAmount());
//        System.out.println("list = " + list);
        int count = adminManageMapper.searchAdminListCount("김", "name", adminPageRequestDTO.getPage(), adminPageRequestDTO.getAmount());
        System.out.println("count = " + count);

    }

    @Test
    void deleteStaff(){
        adminManageMapper.insertAdmin(adminDTO1);
        adminDTO1.setStatus(true);
        List<AdminDTO> list = adminManageMapper.selectAdminList("", "", adminPageRequestDTO.getPage(), adminPageRequestDTO.getAmount());
        System.out.println("list = " + list);

        adminManageMapper.deleteStaff(adminDTO1.getAdminId());
        List<AdminDTO> afterList = adminManageMapper.selectAdminList("", "", adminPageRequestDTO.getPage(), adminPageRequestDTO.getAmount());
        System.out.println("afterList = " + afterList);
    }
}