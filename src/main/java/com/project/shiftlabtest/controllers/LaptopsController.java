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
@RequestMapping("categories/laptop")
public class LaptopsController {

    private final LaptopRepository laptopRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final LaptopSizeRepository laptopSizeRepository;

    @Autowired
    public LaptopsController(LaptopRepository laptopRepository,
                             ManufacturerRepository manufacturerRepository,
                             LaptopSizeRepository laptopSizeRepository) {
        this.laptopRepository = laptopRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.laptopSizeRepository = laptopSizeRepository;
    }

    @GetMapping()
    public String showAll(Model model) {
        Iterable<Laptop> laptops = laptopRepository.findAll();
        model.addAttribute("laptops", laptops);
        model.addAttribute("findId", new FindId());
        return "categories/laptop/laptopsView";
    }

    @PostMapping()
    public String find(Model model, @ModelAttribute("findId") @Valid FindId findId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return showAll(model);
        }

        Laptop laptop = null;
        try {
            laptop = laptopRepository.findById(findId.getId()).orElseThrow();
        } catch (Exception ex) {
            model.addAttribute("findError", "Значение не найдено");
            return showAll(model);
        }

        model.addAttribute("laptops", laptop);
        return "categories/laptop/laptopsView";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        laptopRepository.deleteById(id);
        return "redirect:/categories/laptop";
    }

    public void passToModel(Model model, Laptop laptop) {
        model.addAttribute("laptop", laptop);

        Iterable<Manufacturer> manufacturers = manufacturerRepository.findAll();
        model.addAttribute("manufacturers", manufacturers);

        Iterable<LaptopSize> laptopSizes = laptopSizeRepository.findAll();
        model.addAttribute("laptopSizes", laptopSizes);
    }

    @GetMapping("/new")
    public String create(Model model) {
        passToModel(model, new Laptop());
        return "categories/laptop/laptopNew";
    }

    @PostMapping("/new")
    public String insert(@ModelAttribute("laptop") @Valid Laptop laptop, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            passToModel(model, laptop);
            return "categories/laptop/laptopNew";
        }

        laptopRepository.save(laptop);
        return "redirect:/categories/laptop";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        Laptop laptop = laptopRepository.findById(id).orElseThrow();
        passToModel(model, laptop);
        return "categories/laptop/laptopEdit";
    }

    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute("laptop") @Valid Laptop laptop, BindingResult bindingResult, @PathVariable("id") int id, Model model) {
        if (bindingResult.hasErrors()) {
            passToModel(model, laptop);
            return "categories/laptop/laptopEdit";
        }

        Laptop laptopToUpdate = laptopRepository.findById(id).orElseThrow();

        laptopToUpdate.setSerialNumber(laptop.getSerialNumber());
        laptopToUpdate.setManufacturer(laptop.getManufacturer());
        laptopToUpdate.setStockQuantity(laptop.getStockQuantity());
        laptopToUpdate.setPrice(laptop.getPrice());
        laptopToUpdate.setLaptopSize(laptop.getLaptopSize());

        laptopRepository.save(laptopToUpdate);

        return "redirect:/categories/laptop";
    }

}
