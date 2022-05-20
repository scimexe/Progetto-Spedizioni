package com.example.demo.controller;

import com.example.demo.entity.Spedizione;
import com.example.demo.repository.SpedizioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class SpedizioneController {
    @Autowired
    SpedizioneRepository spedizioneRepository;

    @GetMapping("/spedizioni")
    public String index(){
        return "spedizioni/index";
    }
    @GetMapping("/spedizioni/view")
    public String index(Model model){
        List<Spedizione> results = (List<Spedizione>) spedizioneRepository.findAll();
        model.addAttribute("spedizioni", results);
        return "spedizioni/showAll";
    }
    @RequestMapping(value = "spedizioni/new", method = RequestMethod.GET)
    public String nuovaSpedizione(Model model){
        Spedizione spedizione = new Spedizione();
        model.addAttribute("spedizione", spedizione);
        return "spedizioni/newSpedizione";
    }
    @PostMapping(value = "/spedizioni/new")
    public String aggiungiSpedizione(Spedizione spedizione, BindingResult result){
        if (result.hasErrors()){
            return "spedizioni/newSpedizione";
        }
        else{
            System.out.println(spedizioneRepository.save(spedizione));
            return "spedizioni/showAll";
        }
    }

}
