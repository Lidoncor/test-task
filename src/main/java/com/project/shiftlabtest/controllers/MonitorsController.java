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
@RequestMapping("categories/monitor")
public class MonitorsController {

    private final MonitorRepository monitorRepository;
    private final ManufacturerRepository manufacturerRepository;

    @Autowired
    public MonitorsController(MonitorRepository monitorRepository, ManufacturerRepository manufacturerRepository) {
        this.monitorRepository = monitorRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @GetMapping()
    public String showAll(Model model) {
        Iterable<Monitor> monitors = monitorRepository.findAll();
        model.addAttribute("monitors", monitors);
        model.addAttribute("findId", new FindId());
        return "categories/monitor/monitorsView";
    }

    @PostMapping()
    public String find(Model model, @ModelAttribute("findId") @Valid FindId findId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return showAll(model);
        }

        Monitor monitor = null;
        try {
            monitor = monitorRepository.findById(findId.getId()).orElseThrow();
        } catch (Exception ex) {
            model.addAttribute("findError", "Значение не найдено");
            return showAll(model);
        }

        model.addAttribute("monitors", monitor);
        return "categories/monitor/monitorsView";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        monitorRepository.deleteById(id);
        return "redirect:/categories/monitor";
    }

    public void passToModel(Model model, Monitor monitor) {
        model.addAttribute("monitor", monitor);

        Iterable<Manufacturer> manufacturers = manufacturerRepository.findAll();
        model.addAttribute("manufacturers", manufacturers);
    }

    @GetMapping("/new")
    public String create(Model model) {
        passToModel(model, new Monitor());
        return "categories/monitor/monitorNew";
    }

    @PostMapping("/new")
    public String insert(@ModelAttribute("monitor") @Valid Monitor monitor, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            passToModel(model, monitor);
            return "categories/monitor/monitorNew";
        }

        monitorRepository.save(monitor);
        return "redirect:/categories/monitor";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        Monitor monitor = monitorRepository.findById(id).orElseThrow();
        passToModel(model, monitor);
        return "categories/monitor/monitorEdit";
    }

    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute("monitor") @Valid Monitor monitor, BindingResult bindingResult, @PathVariable("id") int id, Model model) {
        if (bindingResult.hasErrors()) {
            passToModel(model, monitor);
            return "categories/monitor/monitorEdit";
        }

        Monitor monitorToUpdate = monitorRepository.findById(id).orElseThrow();

        monitorToUpdate.setSerialNumber(monitor.getSerialNumber());
        monitorToUpdate.setManufacturer(monitor.getManufacturer());
        monitorToUpdate.setStockQuantity(monitor.getStockQuantity());
        monitorToUpdate.setPrice(monitor.getPrice());
        monitorToUpdate.setSize(monitor.getSize());

        monitorRepository.save(monitorToUpdate);

        return "redirect:/categories/monitor";
    }

}
