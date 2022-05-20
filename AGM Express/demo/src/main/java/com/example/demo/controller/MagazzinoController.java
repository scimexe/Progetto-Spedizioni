package com.example.demo.controller;

import com.example.demo.entity.Magazzino;
import com.example.demo.repository.MagazzinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MagazzinoController {
    @Autowired
    MagazzinoRepository magazzinoRepository;

    @RequestMapping("/magazzini")
    public String index(Model model){
        List<Magazzino> results = (List<Magazzino>) magazzinoRepository.findAll();
        model.addAttribute("magazzini", results);
        return "magazzini/index";
    }
}