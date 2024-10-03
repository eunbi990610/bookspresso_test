package com.example.bookspresso.controller.member;

import com.example.bookspresso.dto.member.MemberJoinDTO;
import com.example.bookspresso.dto.member.ProfleJoinDTO;
import com.example.bookspresso.service.member.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String join(HttpSession session){
        if (session.getAttribute("memberId") != null){
            return "redirect:/";
        }
        return "member/join";
    }

    //회원가입 시 멤버 테이블의 데이터와 프로필 테이블의 데이터가 모두 들어가야함

    @PostMapping("/join")
    public String join(MemberJoinDTO memberJoinDTO, ProfleJoinDTO profleJoinDTO){
        System.out.println("@@@@@ memberJoinDTO = " + memberJoinDTO);

        try {
            System.out.println("addmember실행");
            memberService.addMember(memberJoinDTO);
            profleJoinDTO.setMemberId(memberJoinDTO.getMemberId());
            System.out.println("memberJoinDTO = " + profleJoinDTO);
            memberService.addProfle(profleJoinDTO);
            System.out.println("프로필실행");
        } catch (IllegalStateException e) {
            log.error(e.toString());

            return "member/join";
        }


        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login(){
        return "member/login";
    }

    @PostMapping("/login")
    public String login(String loginId, String password,
                        HttpSession session){
        Long memberId = null;

        try {
            memberId = memberService.findMemberId(loginId, password);
        } catch (IllegalArgumentException e) {
            System.out.println("존재하지 않는 회원정보");
            return "member/login";
        } catch (Exception e) {
            System.out.println("예상치 못한 예외");
            return "member/login";
        }
        session.setAttribute("memberId", memberId);
        System.out.println(loginId+" 회원의 회원번호 = " + session.getAttribute("memberId"));
        return "redirect:/";
    }
// 이메일 검증 => 백 : 검증 라이브러리 사용 and 프론트 : js에서 정규표현식


    @GetMapping("/findId")
    public String findId(){
        return "member/findId";
    }
    @PostMapping("/findId")
    public String findId(String email, Model model){
        //js 에서 아이디 존재여부, 인증번호 일치 여부를 모두 확인했음
        //이메일과 일치하는 로그인을 넘겨주면 됨
        System.out.println("email = " + email);
        String login = memberService.findLoginIdEmail(email);
        String loginId = "";
        char[] charArray = login.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if(i < charArray.length - 3){
                loginId += charArray[i];
            }else{
                loginId += "*";
            }
            System.out.println("loginId = " + loginId);
        }
        System.out.println("loginId = " + loginId);
        model.addAttribute("loginId", loginId);

        return "member/checkId";
    }


    @GetMapping("/findPassword")
    public String findPassword(){
        return "member/findPassword";
    }

//    @PostMapping("/findPassword")
//    public String findPassword(String loginId,
//                               RedirectAttributes redirectAttributes){
//
//        System.out.println("loginId = " + loginId);
//        redirectAttributes.addAttribute("loginId", loginId);
//
//        return "redirect:/member/changePw";
//    }
//    @GetMapping("member/changePw")
//    public String changePw(String loginId, Model model){
//        model.addAttribute("loginId", loginId);
//
//        return "member/checkPw";
//    }

    @PostMapping("/changePw")
    public String changePw(String loginId, Model model){

        model.addAttribute("loginId", loginId);

        return "member/checkPw";
    }

    @PostMapping("toLogin")
    public String toLogin(){
        System.out.println("비밀번호 변경 성공!");
        return "redirect:/member/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }


}
