package com.example.demo.controller;
import com.example.demo.entity.Collo;
import com.example.demo.repository.ColloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/colli/")
public class ColloController {

    private Long IdGlobale;

    @Autowired
    ColloRepository colloRepository;

    @GetMapping("/new")
    public String inserimentoForm(Model model){
        Collo collo = new Collo();
        model.addAttribute("collo", collo);
        return "inserimento-colli";
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

    @GetMapping(value = {"/edit/{id}"})
    public String edit(@PathVariable("id") Long id, Model model){
        Optional<Collo> collo = colloRepository.findById(id);
        model.addAttribute("collo", collo.get());
        IdGlobale = id;
        return "colli/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String modificaCollo(@Validated Collo newCollo, BindingResult result, Model model){
        if(!colloRepository.findById(newCollo.getColliId()).equals(Optional.empty())){ 
            Collo colloInDB = colloRepository.findById(newCollo.getColliId()).get();
            colloInDB.setAltezza(newCollo.getAltezza());
            colloInDB.setLarghezza(newCollo.getLarghezza());
            colloInDB.setProfondita(newCollo.getProfondita());
            colloInDB.setPeso(newCollo.getPeso());
            colloInDB.setBolla(newCollo.getBolla());
            colloRepository.save(colloInDB);

            return "redirect:../colli/";
        }
        return "redirect:../colli/";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String aggiungiCollo(@Validated Collo collo, BindingResult result, Model model){
        if (result.hasErrors()){
            return "inserimento-colli";
        }
        else{
            colloRepository.save(collo);
            return "redirect:../colli/";
        }
    }
    

    @RequestMapping(value =("/delete/{id}"))
    public String delete(@PathVariable Long id){
        if (!colloRepository.findById(id).equals(Optional.empty())) {
            colloRepository.deleteById(id);
        }
        return "redirect:../";
    }
}