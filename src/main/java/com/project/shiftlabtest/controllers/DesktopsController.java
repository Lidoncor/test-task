package com.project.shiftlabtest.controllers;

import com.project.shiftlabtest.abstracts.Product;
import com.project.shiftlabtest.models.Desktop;
import com.project.shiftlabtest.models.FormFactor;
import com.project.shiftlabtest.other.FindId;
import com.project.shiftlabtest.models.Manufacturer;
import com.project.shiftlabtest.repositories.DesktopRepository;
import com.project.shiftlabtest.repositories.FormFactorRepository;
import com.project.shiftlabtest.repositories.ManufacturerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("categories/desktop")
public class DesktopsController {

    private final DesktopRepository desktopRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final FormFactorRepository formFactorRepository;

    @Autowired
    public DesktopsController(DesktopRepository desktopRepository,
                              ManufacturerRepository manufacturerRepository,
                              FormFactorRepository formFactorRepository) {
        this.desktopRepository = desktopRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.formFactorRepository = formFactorRepository;
    }

    @GetMapping()
    public String showAll(Model model) {
        Iterable<Desktop> desktops = desktopRepository.findAll();
        model.addAttribute("desktops", desktops);
        model.addAttribute("findId", new FindId());
        return "categories/desktop/desktopsView";
    }

    @PostMapping()
    public String find(Model model, @ModelAttribute("findId") @Valid FindId findId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return showAll(model);
        }

        Desktop desktop = null;
        try {
            desktop = desktopRepository.findById(findId.getId()).orElseThrow();
        } catch (Exception ex) {
            model.addAttribute("findError", "Значение не найдено");
            return showAll(model);
        }

        model.addAttribute("desktops", desktop);
        return "categories/desktop/desktopsView";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        desktopRepository.deleteById(id);
        return "redirect:/categories/desktop";
    }

    public void passToModel(Model model, Desktop desktop) {
        model.addAttribute("desktop", desktop);

        Iterable<Manufacturer> manufacturers = manufacturerRepository.findAll();
        model.addAttribute("manufacturers", manufacturers);

        Iterable<FormFactor> formFactors = formFactorRepository.findAll();
        model.addAttribute("formFactors", formFactors);
    }

    @GetMapping("/new")
    public String create(Model model) {
        passToModel(model, new Desktop());
        return "categories/desktop/desktopNew";
    }

    @PostMapping("/new")
    public String insert(@ModelAttribute("desktop") @Valid Desktop desktop, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            passToModel(model, desktop);
            return "categories/desktop/desktopNew";
        }

        desktopRepository.save(desktop);
        return "redirect:/categories/desktop";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        Desktop desktop = desktopRepository.findById(id).orElseThrow();
        passToModel(model, desktop);
        return "categories/desktop/desktopEdit";
    }

    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute("desktop") @Valid Desktop desktop, BindingResult bindingResult, @PathVariable("id") int id, Model model) {
        if (bindingResult.hasErrors()) {
            passToModel(model, desktop);
            return "categories/desktop/desktopEdit";
        }

        Desktop desktopToUpdate = desktopRepository.findById(id).orElseThrow();

        desktopToUpdate.setSerialNumber(desktop.getSerialNumber());
        desktopToUpdate.setManufacturer(desktop.getManufacturer());
        desktopToUpdate.setStockQuantity(desktop.getStockQuantity());
        desktopToUpdate.setPrice(desktop.getPrice());
        desktopToUpdate.setFormFactor(desktop.getFormFactor());

        desktopRepository.save(desktopToUpdate);

        return "redirect:/categories/desktop";
    }

}
