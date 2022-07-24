package com.example.codesharingplatform.controller;

import com.example.codesharingplatform.controller.mapper.SnippetResponseMapper;
import com.example.codesharingplatform.exception.CodeNotFoundException;
import com.example.codesharingplatform.service.CodeFragmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
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
    SnippetResponseMapper snippetResponseMapper;

    @GetMapping("/new")
    public ModelAndView postCode() {
        return new ModelAndView("submit");
    }

    @GetMapping("/latest")
    public ModelAndView returnLatestHtmlPages(Model model) {
        model.addAttribute("codeList", snippetResponseMapper.allToDto(codeFragmentService.findLatest10()));
        return new ModelAndView("latest");
    }

    @GetMapping("/{token}")
    public ModelAndView returnLatestHtmlPage(Model model, @PathVariable String token) {
        val snippet = codeFragmentService.findSnippetByToken(token);
        model.addAttribute("snippet", snippet);
        return new ModelAndView("fragment");
    }
}
