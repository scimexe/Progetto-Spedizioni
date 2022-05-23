package com.example.demo.controller;

import com.example.demo.entity.Spedizione;
import com.example.demo.entity.Veicolo;
import com.example.demo.entity.Collo;
import com.example.demo.repository.ColloRepository;
import com.example.demo.repository.SpedizioneRepository;
import com.example.demo.repository.VeicoloRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/spedizioni")
public class SpedizioneController {
    @Autowired
    SpedizioneRepository spedizioneRepository;
    @Autowired
    VeicoloRepository veicoloRepository;
    @Autowired
    ColloRepository colloRepository;

    @GetMapping(value = { "/", ""})
    public String index(Model model){
        List<Spedizione> results = (List<Spedizione>) spedizioneRepository.findAll();
        model.addAttribute("spedizioni", results);
        return "spedizioni/index";
    }

    @RequestMapping(value =("/delete/{id}"))
    public String delete(@PathVariable Long id){
        if (!spedizioneRepository.findById(id).equals(Optional.empty())) {
            spedizioneRepository.deleteById(id);
        }
        return "redirect:../";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String nuovaSpedizione(Model model){
        Spedizione spedizione = new Spedizione();
        List<Veicolo> veicoli = (List<Veicolo>) veicoloRepository.findAll();
        List<Collo> colli = (List<Collo>) colloRepository.findAll();

        model.addAttribute("veicolo", veicoli);
        model.addAttribute("collo", colli);
        model.addAttribute("spedizione", spedizione);
        return "spedizioni/newSpedizione";
    }

    @PostMapping(value = "/new")
    public String aggiungiSpedizione(Spedizione spedizione, BindingResult result){
        if (result.hasErrors()){
            return "spedizioni/newSpedizione";
        }
        else{
            System.out.println(spedizioneRepository.save(spedizione));
            return "redirect:../spedizioni/";
        }
    }

    @GetMapping(value = {"/{id}"})
    public String show(@PathVariable("id") Long id, Model model){
        Optional<Spedizione> spedizione = spedizioneRepository.findById(id);
        model.addAttribute("spedizione", spedizione.get());
        return "spedizioni/show";
    }

}
