package com.example.bookspresso.controller.main;

import com.example.bookspresso.api.Aladen.Aladinitem;
import com.example.bookspresso.dto.mainPage.DebateMainDTO;
import com.example.bookspresso.dto.post.PostMainDTO;
import com.example.bookspresso.service.aladen.AladinService;
import com.example.bookspresso.service.mainPage.MainPageService;
import com.example.bookspresso.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final AladinService aladinService;
    private final MainPageService mainPageService;


    @GetMapping("/")
    public String mainPage(Model model)throws Exception{
        List<Aladinitem> aladin = aladinService.getApialadinMain();
        List<DebateMainDTO> addonline = mainPageService.addonline();
        List<DebateMainDTO> addoffline = mainPageService.addoffline();
        List<PostMainDTO> addpost = mainPageService.addpost();

        model.addAttribute("aladin", aladin);
        model.addAttribute("online", addonline);
        model.addAttribute("offline", addoffline);
        model.addAttribute("addmain", addpost);


        return "mainpage/mainpage";
    }
}
