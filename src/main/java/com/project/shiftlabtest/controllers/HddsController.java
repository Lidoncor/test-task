package com.project.shiftlabtest.controllers;

import com.project.shiftlabtest.models.*;
import com.project.shiftlabtest.other.FindId;
import com.project.shiftlabtest.repositories.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("categories/hdd")
public class HddsController {

    private final HddRepository hddRepository;
    private final ManufacturerRepository manufacturerRepository;

    @Autowired
    public HddsController(HddRepository hddRepository, ManufacturerRepository manufacturerRepository) {
        this.hddRepository = hddRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @GetMapping()
    public String showAll(Model model) {
        Iterable<Hdd> hdds = hddRepository.findAll();
        model.addAttribute("hdds", hdds);
        model.addAttribute("findId", new FindId());
        return "categories/hdd/hddsView";
    }

    @PostMapping()
    public String find(Model model, @ModelAttribute("findId") @Valid FindId findId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return showAll(model);
        }

        Hdd hdd = null;
        try {
            hdd = hddRepository.findById(findId.getId()).orElseThrow();
        } catch (Exception ex) {
            model.addAttribute("findError", "Значение не найдено");
            return showAll(model);
        }

        model.addAttribute("hdds", hdd);
        return "categories/hdd/hddsView";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        hddRepository.deleteById(id);
        return "redirect:/categories/hdd";
    }

    public void passToModel(Model model, Hdd hdd) {
        model.addAttribute("hdd", hdd);

        Iterable<Manufacturer> manufacturers = manufacturerRepository.findAll();
        model.addAttribute("manufacturers", manufacturers);
    }

    @GetMapping("/new")
    public String create(Model model) {
        passToModel(model, new Hdd());
        return "categories/hdd/hddNew";
    }

    @PostMapping("/new")
    public String insert(@ModelAttribute("hdd") @Valid Hdd hdd, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            passToModel(model, hdd);
            return "categories/hdd/hddNew";
        }

        hddRepository.save(hdd);
        return "redirect:/categories/hdd";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        Hdd hdd = hddRepository.findById(id).orElseThrow();
        passToModel(model, hdd);
        return "categories/hdd/hddEdit";
    }

    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute("hdd") @Valid Hdd hdd, BindingResult bindingResult, @PathVariable("id") int id, Model model) {
        if (bindingResult.hasErrors()) {
            passToModel(model, hdd);
            return "categories/hdd/hddEdit";
        }

        Hdd hddToUpdate = hddRepository.findById(id).orElseThrow();

        hddToUpdate.setSerialNumber(hdd.getSerialNumber());
        hddToUpdate.setManufacturer(hdd.getManufacturer());
        hddToUpdate.setStockQuantity(hdd.getStockQuantity());
        hddToUpdate.setPrice(hdd.getPrice());
        hddToUpdate.setCapacity(hdd.getCapacity());

        hddRepository.save(hddToUpdate);

        return "redirect:/categories/hdd";
    }

}
