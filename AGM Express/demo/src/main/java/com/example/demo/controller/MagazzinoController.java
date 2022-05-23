package com.example.demo.controller;

import com.example.demo.entity.Magazzino;
import com.example.demo.repository.MagazzinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RequestMapping("/magazzini")
@Controller
public class MagazzinoController {
    @Autowired
    MagazzinoRepository magazzinoRepository;

    private long IdGlobale;

    @GetMapping("/new")
    public String inserimentoForm(Model model){
        Magazzino magazzino = new Magazzino();
        model.addAttribute("magazzino", magazzino);
        return "magazzini/inserimento-magazzini";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String aggiungiCollo(@Valid @ModelAttribute("magazzino") Magazzino magazzino, BindingResult result){
        if (result.hasErrors()){
            return "magazzini/inserimento-magazzini";
        }
        else{
            magazzinoRepository.save(magazzino);
            return "redirect:../magazzini/";
        }
    }

    @GetMapping(value = { "/", ""})
    public String index(Model model){
        List<Magazzino> results = (List<Magazzino>) magazzinoRepository.findAll();
        model.addAttribute("magazzini", results);
        return "magazzini/index";

    }

    @GetMapping(value = {"/{id}"})
    public String show(@PathVariable("id") Long id, Model model){
        Optional<Magazzino> magazzino = magazzinoRepository.findById(id);
        model.addAttribute("magazzino", magazzino.get());
        return "magazzini/show";
    }

    @RequestMapping(value =("/delete/{id}"))
    public String delete(@PathVariable Long id){
        //colloRepository.findById(id);
        if (!magazzinoRepository.findById(id).equals(Optional.empty())) {
            magazzinoRepository.deleteById(id);
        }
        return "redirect:../";
    }

    @GetMapping(value = {"/edit/{id}"})
    public String edit(@PathVariable("id") Long id, Model model){
        IdGlobale = id;
        Optional<Magazzino> magazzino = magazzinoRepository.findById(id);
        model.addAttribute("magazzino", magazzino.get());
        return "magazzini/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String modificaCollo(@Validated Magazzino newMagazzino, BindingResult result, Model model){

        if(!magazzinoRepository.findById(IdGlobale).equals(Optional.empty())){
            Magazzino magazzinoInDB = magazzinoRepository.findById(IdGlobale).get();
            magazzinoInDB.setIndirizzo(newMagazzino.getIndirizzo());
            magazzinoInDB.setNome(newMagazzino.getNome());
            magazzinoInDB.setNColli(newMagazzino.getNColli());
            magazzinoRepository.save(magazzinoInDB);
            return "redirect:../magazzini/";
        }
        return "redirect:../magazzini/";
    }

}