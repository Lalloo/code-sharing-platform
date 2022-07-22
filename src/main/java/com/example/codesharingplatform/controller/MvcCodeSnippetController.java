package com.example.codesharingplatform.controller;

import com.example.codesharingplatform.domain.CodeSnippet;
import com.example.codesharingplatform.service.CodeFragmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/code")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Controller
public class MvcCodeSnippetController {

    CodeFragmentService codeFragmentService;

    @GetMapping("/new")
    public ModelAndView postCode() {
        return new ModelAndView("submit");
    }

    @GetMapping("/latest")
    public ModelAndView returnLatestHtmlPages(Model model) {
        model.addAttribute("codeList", codeFragmentService.findLatest10());
        return new ModelAndView("latest");
    }

    @GetMapping("/{id}")
    public ModelAndView returnLatestHtmlPage(Model model, @PathVariable Long id) {
        var saved = codeFragmentService.findSnippetById(id);
        model.addAttribute("code", saved.getCode());
        model.addAttribute("createAt", saved.getCreateAt());
        return new ModelAndView("fragment");
    }
}
