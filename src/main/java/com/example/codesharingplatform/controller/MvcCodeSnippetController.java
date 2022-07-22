package com.example.codesharingplatform.controller;

import domain.CodeSnippet;
import com.example.codesharingplatform.repository.CodeSnippets;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/code")
@Controller
public class MvcCodeSnippetController {


    private final CodeSnippets codeFragments;

    public MvcCodeSnippetController(CodeSnippets codeFragments) {
        this.codeFragments = codeFragments;
    }

    @GetMapping("/new")
    public ModelAndView postCode() {
        return new ModelAndView("submit");
    }

    @GetMapping("/latest")
    public ModelAndView returnLatestHtmlPages(Model model) {
        model.addAttribute("codeList", codeFragments.getLatest());
        return new ModelAndView("latest");
    }

    @GetMapping("/{id}")
    public ModelAndView returnLatestHtmlPage(Model model, @PathVariable int id) {
        CodeSnippet saved = codeFragments.getById(id);
        model.addAttribute("code", saved.getCode());
        model.addAttribute("createAt",saved.getCreateAt());
        return new ModelAndView("fragment");
    }
}
