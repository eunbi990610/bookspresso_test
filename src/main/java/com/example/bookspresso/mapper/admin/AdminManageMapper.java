package com.example.bookspresso.mapper.admin;

import com.example.bookspresso.dto.admin.admin.AdminDTO;
import com.example.bookspresso.dto.admin.admin.WaitingAdminDTO;
import com.example.bookspresso.dto.admin.page.AdminPageRequestDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface AdminManageMapper {

    void insertAdmin(AdminDTO adminDTO);

    List<WaitingAdminDTO> selectWaitingAdminList(AdminPageRequestDTO adminPageRequestDTO);
    int waitingAdminCount();

    void updateStatus(Long adminId);
    void updateApprovalDate(Long adminId);

    List<AdminDTO> selectAdminList(@Param("keyword") String keyword,
                                   @Param("searchType") String searchType,
                                   @Param("page") int page,
                                   @Param("amount") int amount);
    int searchAdminListCount(@Param("keyword") String keyword,
                             @Param("searchType") String searchType,
                             @Param("page") int page,
                             @Param("amount") int amount);

    void deleteStaff(Long adminId);
}
