package com.example.demo.controller;
import com.example.demo.entity.Collo;
import com.example.demo.entity.Magazzino;
import com.example.demo.repository.ColloRepository;
import com.example.demo.repository.MagazzinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/colli")
public class ColloController {
    private Long IdGlobale;

    @Autowired
    ColloRepository colloRepository;

    @Autowired
    MagazzinoRepository magazzinoRepository;
    
    @GetMapping("/new")
    public String inserimentoForm(Model model, @ModelAttribute("magazzini") Magazzino magazzino){
        Collo collo = new Collo();
        List<Magazzino> magazzini = (List<Magazzino>) magazzinoRepository.findAll();
        model.addAttribute("magazzino", magazzini);
        model.addAttribute("collo", collo);
        return "colli/inserimento-colli";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String aggiungiCollo(@Valid @ModelAttribute("collo") Collo collo, BindingResult result){
        if (result.hasErrors()){
            return "colli/inserimento-colli";
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


    @GetMapping(value = {"/edit/{id}"})
    public String edit(@PathVariable("id") Long id, Model model){
        IdGlobale = id;
        Optional<Collo> collo = colloRepository.findById(id);
        model.addAttribute("collo", collo.get());
        return "colli/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String modificaCollo(@Validated Collo newCollo, BindingResult result, Model model){

        if(!colloRepository.findById(IdGlobale).equals(Optional.empty())){
            Collo colloInDB = colloRepository.findById(IdGlobale).get();
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

}