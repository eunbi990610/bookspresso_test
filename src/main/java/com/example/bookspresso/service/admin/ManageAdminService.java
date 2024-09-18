package com.example.bookspresso.service.admin;

import com.example.bookspresso.dto.admin.admin.AdminDTO;
import com.example.bookspresso.dto.admin.admin.WaitingAdminDTO;
import com.example.bookspresso.dto.admin.page.AdminPageRequestDTO;
import com.example.bookspresso.mapper.admin.AdminManageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ManageAdminService {
    private final AdminManageMapper adminManageMapper;

    public void addAdmin(AdminDTO adminDTO){
        adminManageMapper.insertAdmin(adminDTO);
    }

    public List<WaitingAdminDTO> findWaitingAdminDTOList(AdminPageRequestDTO adminPageRequestDTO){
        return adminManageMapper.selectWaitingAdminList(adminPageRequestDTO);
    }

    public int waitingAdminCount(){
        return adminManageMapper.waitingAdminCount();
    }

    public List<AdminDTO> findAdminDTOList(String keyword,
                                           String searchType,
                                           int page,
                                           int amount){
        return adminManageMapper.selectAdminList(keyword, searchType, page, amount);
    }

    public int searchAdminListCount(String keyword,
                                    String searchType,
                                    int page,
                                    int amount){
        return adminManageMapper.searchAdminListCount(keyword, searchType, page, amount);
    }

    public void updateAdminStatus(Long adminId){
        adminManageMapper.updateStatus(adminId);
        adminManageMapper.updateApprovalDate(adminId);
    }

    public void revokeStaff(Long adminId){
        adminManageMapper.deleteStaff(adminId);
    }

}
