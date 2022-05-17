package com.example.demo.controller;
import com.example.demo.entity.Collo;
import com.example.demo.repository.ColloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/colli/")
public class ColloController {
    @Autowired
    ColloRepository colloRepository;

    @GetMapping("/new")
    public String inserimentoForm(Model model){
        Collo collo = new Collo();
        model.addAttribute("collo", collo);
        return "inserimento-colli";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String aggiungiCollo(@Valid Collo collo, BindingResult result, Model model){
        if (result.hasErrors()){
            return "inserimento-colli";
        }
        else{
            colloRepository.save(collo);
            return "redirect:../colli/";
        }
    }
    @GetMapping(value = { "/", ""})
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

    @RequestMapping(value =("/delete/{id}"))
    public String delete(@PathVariable Long id){
        //colloRepository.findById(id);
        if (!colloRepository.findById(id).equals(Optional.empty())) {
            colloRepository.deleteById(id);
        }
        return "redirect:../";
    }
}