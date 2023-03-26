package com.project.shiftlabtest.controllers;

import com.project.shiftlabtest.models.*;
import com.project.shiftlabtest.other.InitStartObjects;
import com.project.shiftlabtest.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoriesController implements ApplicationRunner {

    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final FormFactorRepository formFactorRepository;
    private final LaptopSizeRepository laptopSizeRepository;

    private final InitStartObjects initStartObjects;
    @Autowired
    public CategoriesController(CategoryRepository categoryRepository,
                                ManufacturerRepository manufacturerRepository,
                                FormFactorRepository formFactorRepository,
                                LaptopSizeRepository laptopSizeRepository,
                                InitStartObjects initStartObjects) {
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.formFactorRepository = formFactorRepository;
        this.laptopSizeRepository = laptopSizeRepository;

        this.initStartObjects = initStartObjects;
    }

    @Override
    public void run(ApplicationArguments args) {
        categoryRepository.save(new Category("Настольные компьютеры", "desktop"));
        categoryRepository.save(new Category("Ноутбуки", "laptop"));
        categoryRepository.save(new Category("Мониторы", "monitor"));
        categoryRepository.save(new Category("Жесткие диски", "hdd"));

        manufacturerRepository.save(new Manufacturer("Acer"));
        manufacturerRepository.save(new Manufacturer("Asus"));
        manufacturerRepository.save(new Manufacturer("Samsung"));
        manufacturerRepository.save(new Manufacturer("Western Digital"));

        formFactorRepository.save(new FormFactor("Десктоп"));
        formFactorRepository.save(new FormFactor("Неттоп"));
        formFactorRepository.save(new FormFactor("Моноблок"));

        laptopSizeRepository.save(new LaptopSize(13));
        laptopSizeRepository.save(new LaptopSize(14));
        laptopSizeRepository.save(new LaptopSize(15));
        laptopSizeRepository.save(new LaptopSize(17));

        initStartObjects.initialize();
    }

    @GetMapping()
    public String showAll(Model model) {
        Iterable<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "categories/categoriesView";
    }

}
