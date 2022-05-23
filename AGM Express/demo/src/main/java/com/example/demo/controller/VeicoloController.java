package com.example.demo.controller;
import com.example.demo.entity.Veicolo;
import com.example.demo.repository.VeicoloRepository;
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

@Controller
@RequestMapping("/veicoli")
public class VeicoloController {
    @Autowired
    VeicoloRepository veicoloRepository;

    private long IdGlobale;

    @GetMapping(value = { "/", ""})
    public String index(Model model){
        List<Veicolo> results = (List<Veicolo>) veicoloRepository.findAll();
        model.addAttribute("veicoli", results);
        return "veicoli/index";
    }

    @GetMapping("/new")
    public String inserimentoForm(Model model){
        Veicolo veicolo = new Veicolo();
        model.addAttribute("veicolo", veicolo);
        return "veicoli/inserimento-veicoli";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String aggiungiCollo(@Valid @ModelAttribute("magazzino") Veicolo veicolo, BindingResult result){
        if (result.hasErrors()){
            return "veicoli/inserimento-veicoli";
        }
        else{
            veicoloRepository.save(veicolo);
            return "redirect:../veicoli/";
        }
    }

    @GetMapping(value = {"/{id}"})
    public String show(@PathVariable("id") Long id, Model model){
        Optional<Veicolo> veicolo = veicoloRepository.findById(id);
        model.addAttribute("veicolo", veicolo.get());
        return "veicolo/show";
    }

    @RequestMapping(value =("/delete/{id}"))
    public String delete(@PathVariable Long id){
        //colloRepository.findById(id);
        if (!veicoloRepository.findById(id).equals(Optional.empty())) {
            veicoloRepository.deleteById(id);
        }
        return "redirect:../";
    }

    @GetMapping(value = {"/edit/{id}"})
    public String edit(@PathVariable("id") Long id, Model model){
        IdGlobale = id;
        Optional<Veicolo> veicolo = veicoloRepository.findById(id);
        model.addAttribute("veicolo", veicolo.get());
        return "veicoli/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String modificaCollo(@Validated Veicolo newVeicolo, BindingResult result, Model model){

        if(!veicoloRepository.findById(IdGlobale).equals(Optional.empty())){
            Veicolo veicoloInDB = veicoloRepository.findById(IdGlobale).get();
            veicoloInDB.setColliDaConsegnare(newVeicolo.getColliDaConsegnare());
            veicoloInDB.setMarca(newVeicolo.getMarca());
            veicoloInDB.setModello(newVeicolo.getModello());
            veicoloRepository.save(veicoloInDB);
            return "redirect:../veicoli/";
        }
        return "redirect:../veicoli/";
    }
}