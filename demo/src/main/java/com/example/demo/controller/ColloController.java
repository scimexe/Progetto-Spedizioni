package com.example.demo.controller;

import com.example.demo.entity.Collo;
import com.example.demo.repository.ColloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelExtensionsKt;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/colli/")
public class ColloController {
    @Autowired
    ColloRepository colloRepository;

    @GetMapping(value = { "/index/", "/", ""})
    public String index(Model model){
        List<Collo> results = (List<Collo>) colloRepository.findAll();
        model.addAttribute("colli", results);
        return "colli/index";
    }

    @GetMapping(value = {"/{id}"})
    public String show(@PathVariable("id") Long id, Model model){
        Optional<Collo> collo = colloRepository.findById(id);
        model.addAttribute("collo", collo.get());
        return "colli/show";
    }
    /*
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        colloRepository.deleteById(id);
    }*/

}