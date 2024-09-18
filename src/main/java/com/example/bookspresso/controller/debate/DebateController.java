package com.example.bookspresso.controller.debate;

import com.example.bookspresso.dto.debate.DebateInpoDTO;
import com.example.bookspresso.dto.debate.N_N.DebateBookDTO;
import com.example.bookspresso.dto.debate.N_N.MemberdebateDTO;
import com.example.bookspresso.dto.debate.board.DebateBoardAsideDTO;
import com.example.bookspresso.dto.debate.board.DebateBoardDTO;
import com.example.bookspresso.dto.debate.board.DebateBoardUpdateDTO;
import com.example.bookspresso.dto.debate.board.DebateWriteDTO;
import com.example.bookspresso.dto.debate.debateMain.DebateMainPageDTO;
import com.example.bookspresso.dto.debate.page.DebatePageRequestDTO;
import com.example.bookspresso.dto.debate.page.PageRequestDTO;
import com.example.bookspresso.dto.debate.page.PageSetDTO;
import com.example.bookspresso.service.debate.DebateBoardService;
import com.example.bookspresso.service.debate.Debateservice;
import com.example.bookspresso.service.post.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/debate")
public class DebateController {
    private final Debateservice debateservice;
    private final DebateBoardService debateBoardService;


    @GetMapping("/")
    public String debate(PageRequestDTO pageRequestDTO, Model model) {
//        메인페이지

        List<DebateMainPageDTO> debateMain = debateservice.findMainPage(pageRequestDTO);
        int total = debateservice.findTotal();
        PageSetDTO pageNum = new PageSetDTO(total, pageRequestDTO);
        model.addAttribute("debateMain", debateMain);
        model.addAttribute("pageNum", pageNum);

        return "debate/debateboard/debatemain";
    }


    @GetMapping("/make")
    public String make() {
        return "debate/debateinpo/debatemake";
    }

    @PostMapping("/make")
    public String makePost(Long meetingId, HttpSession session) {
        if (meetingId == 1 || meetingId == 2) {
            session.setAttribute("meetingId", meetingId);
            return "debate/debateinpo/debatetype";
        } else {
            session.setAttribute("meetingId", meetingId);
            session.setAttribute("debateType", 4L);

            return "debate/debateinpo/debateoutline";
        }

    }

    @GetMapping("/type")
    public String type() {
        return "debate/debateinpo/debatetype";
    }

    @PostMapping("/type")
    public String typePost(Long debateType, HttpSession session) {
        session.setAttribute("debateType", debateType);
        return "debate/debateinpo/debateoutline";
    }

    @GetMapping("/inpo")
    public String inpo() {
//        @ModelAttribute("debateInpoDTO")DebateInpoDTO debateInpoDTO
        return "debate/debateinpo/debateoutline";
    }

    @PostMapping("/inpo")
    public String inpoPost(DebateInpoDTO debateinpoDTO,
                           DebateBookDTO debateBookDTO,
                           MemberdebateDTO memberDebateDTO,
                           DebatePageRequestDTO debatePageRequestDTO,
                           HttpSession session,
                           Model model) {
        //세션에서 값을 꺼내 저장
        Long memberId = (Long) session.getAttribute("memberId");
        Long meetingId = (Long) session.getAttribute("meetingId");
        Long debateType = (Long) session.getAttribute("debateType");
//        페이지를 넘기면서 세션에 meetingId, debateType 을 저장했기때문에 set 으로 직접 넣어줌
        debateinpoDTO.setMeetingId(meetingId);
        debateinpoDTO.setDebateTypeId(debateType);
//        멤버 아이디도 동일하게 직접 넣어줌
        debateBookDTO.setMemberId(memberId);
        memberDebateDTO.setMemberId(memberId);
//        검색 페이징 처리
//        int total= debateservice.findTotal();
//        DebatePageSetDTO debatePageSetDTO = new DebatePageSetDTO(total, debatePageRequestDTO);
//        debateservice.addInpo(debateinpoDTO);
//        model.addAttribute("pageNum", debateinpoDTO);
        debateservice.addInpo(debateinpoDTO);
//     debateId 값은 시퀀스로 생성하기 때문에 위의 코드가 실행되야 생성되서 먼저 실행후 값을꺼내여 직접 넣어준다
        Long debateId = debateinpoDTO.getDebateId();
        debateBookDTO.setDebateId(debateId);
        memberDebateDTO.setDebateId(debateId);
        debateservice.addConnection(debateBookDTO, memberDebateDTO);

//    세션에서 meetingId, debateType 제거
        session.removeAttribute("debateType");
        session.removeAttribute("debateType");
        return "redirect:/debate/board?debateId=" + debateId;
    }


    @GetMapping("/board")
    public String debateboard(Long debateId, HttpSession session, Model model) {


//        로그인 체크
        Long memberId = (Long) session.getAttribute("memberId");
        if (memberId == null) {
            return "redirect:/member/login";
        }
//        if(debateId==null) {
//        }
        DebateBoardAsideDTO aside = debateBoardService.selectAside(debateId);
        List<DebateBoardDTO> debateBoard = debateBoardService.selectcentent(debateId);
        model.addAttribute("aside", aside);
        model.addAttribute("debateBoard", debateBoard);

        return "debate/debateboard/debateboard";
    }

    @GetMapping("/write")
    public String debatewrite(Long debateId, HttpSession session, Model model) {

        Long memberId = (Long) session.getAttribute("memberId");
        if (memberId == null) {
            return "redirect:/member/login";
        }
        DebateBoardAsideDTO aside = debateBoardService.selectAside(debateId);
        model.addAttribute("aside", aside);
        return "debate/debateboard/debatewrite";
    }

    @PostMapping("/write")
    public String debatewrite(DebateWriteDTO debateWriteDTO, HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");
        if (memberId == null) {
            return "redirect:/member/login";
        }
        Long debateId = debateWriteDTO.getDebateId();
        debateWriteDTO.setMemberId(memberId);
        debateWriteDTO.setDebateId(debateId);
        debateBoardService.addWrite(debateWriteDTO);
        return "redirect:/debate/board?debateId=" + debateId;
    }

    @GetMapping("/detail")
    public String debatedetail(Long noticeId, Long debateId, HttpSession session, Model model) {


        Long memberId = (Long) session.getAttribute("memberId");
        if (memberId == null) {
            return "redirect:/member/login";
        }
        DebateBoardDTO detail = debateBoardService.selectDetaill(debateId, noticeId);
        DebateBoardAsideDTO aside = debateBoardService.selectAside(debateId);
        model.addAttribute("aside", aside);
        model.addAttribute("detail", detail);
        return "debate/debateboard/debatedetail";
    }

    @GetMapping("/modify")
    public String debatemodify(@SessionAttribute("memberId") Long memberId,
                               Long debateId,
                               Model model,
                               Long noticeId) {

        if (memberId == null) {
            return "redirect:/member/login";
        }
        DebateBoardDTO detail = debateBoardService.selectDetaill(debateId,noticeId);
        System.out.println("detail = " + detail);
        DebateBoardAsideDTO aside = debateBoardService.selectAside(debateId);
        model.addAttribute("aside", aside);
        model.addAttribute("detail", detail);
        return "debate/debateboard/debatemodify";
    }

    @PostMapping("/modify")
    public String debatemodify(DebateBoardUpdateDTO debateBoardUpdateDTO, @SessionAttribute("memberId") Long memberId) {
        if (memberId == null) {
            return "redirect:/member/login";
        }
        debateBoardUpdateDTO.setMemberId(memberId);
        debateBoardService.modifyBoard(debateBoardUpdateDTO);
        Long noticeId = debateBoardUpdateDTO.getNoticeId();
        Long debateId = debateBoardUpdateDTO.getDebateId();
        return "redirect:/debate/detail?debateId="+debateId+"&noticeId="+noticeId;
    }
    @GetMapping("/remove")
    public String remove(@SessionAttribute("memberId") Long memberId,Long noticeId,Long debateId){
        if (memberId == null) {
            return "redirect:/member/login";
        }
        debateBoardService.removeBoard(memberId,noticeId);
        return "redirect:/debate/board?debateId="+debateId;
    }

}

