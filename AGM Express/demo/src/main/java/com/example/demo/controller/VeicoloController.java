package com.example.demo.controller;
import com.example.demo.entity.Veicolo;
import com.example.demo.repository.VeicoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class VeicoloController {
    @Autowired
    VeicoloRepository veicoloRepository;

    @RequestMapping("/veicoli")
    public String index(Model model){
        List<Veicolo> results = (List<Veicolo>) veicoloRepository.findAll();
        model.addAttribute("veicoli", results);
        return "veicoli/index";
    }
}