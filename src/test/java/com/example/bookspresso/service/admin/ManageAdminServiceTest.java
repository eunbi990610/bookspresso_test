package com.example.bookspresso.service.admin;

import com.example.bookspresso.dto.admin.admin.AdminDTO;
import com.example.bookspresso.dto.admin.admin.WaitingAdminDTO;
import com.example.bookspresso.mapper.admin.AdminManageMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ManageAdminServiceTest {

//    @Mock
//    AdminManageMapper adminManageMapper;
//
//    @InjectMocks
//    ManageAdminService manageAdminService;
//
//    @Test
//    void addAdmin() {
//        doNothing().when(adminManageMapper).insertAdmin(any());
//        manageAdminService.addAdmin(new AdminDTO());
//        verify(adminManageMapper, times(1)).insertAdmin(any());
//    }
//
//    @Test
//    void findWaitingAdminDTOList() {
//
//        WaitingAdminDTO waitingAdminDTO = new WaitingAdminDTO();
//
//        //given
//        doReturn(List.of(waitingAdminDTO)).when(adminManageMapper).selectWaitingAdminList();
//
//        //when
//        List<WaitingAdminDTO> waitingAdminDTOList = manageAdminService.findWaitingAdminDTOList();
//
//        //then
//
//        assertThat(waitingAdminDTOList)
//                .isNotEmpty()
//                .hasSize(1);
//
//    }

    @Test
    void findAdminDTOList() {
    }

    @Test
    void updateAdminStatus() {

    }
}