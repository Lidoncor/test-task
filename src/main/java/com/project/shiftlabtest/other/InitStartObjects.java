package com.project.shiftlabtest.other;

import com.project.shiftlabtest.models.*;
import com.project.shiftlabtest.repositories.*;
import org.springframework.stereotype.Component;

@Component
public class InitStartObjects {
    private final DesktopRepository desktopRepository;
    private final LaptopRepository laptopRepository;
    private final MonitorRepository monitorRepository;
    private final HddRepository hddRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final FormFactorRepository formFactorRepository;
    private final LaptopSizeRepository laptopSizeRepository;

    public InitStartObjects(DesktopRepository desktopRepository,
                            LaptopRepository laptopRepository,
                            MonitorRepository monitorRepository,
                            HddRepository hddRepository,
                            ManufacturerRepository manufacturerRepository,
                            FormFactorRepository formFactorRepository,
                            LaptopSizeRepository laptopSizeRepository) {
        this.desktopRepository = desktopRepository;
        this.laptopRepository = laptopRepository;
        this.monitorRepository = monitorRepository;
        this.hddRepository = hddRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.formFactorRepository = formFactorRepository;
        this.laptopSizeRepository = laptopSizeRepository;
    }

    public void initialize() {
        Manufacturer manufacturerAcer = manufacturerRepository.findById(1).orElseThrow();
        Manufacturer manufacturerAsus = manufacturerRepository.findById(2).orElseThrow();
        Manufacturer manufacturerSams = manufacturerRepository.findById(3).orElseThrow();
        Manufacturer manufacturerWd = manufacturerRepository.findById(4).orElseThrow();

        FormFactor formFactorDesk = formFactorRepository.findById(1).orElseThrow();
        FormFactor formFactorNett = formFactorRepository.findById(2).orElseThrow();
        FormFactor formFactorMono = formFactorRepository.findById(3).orElseThrow();

        LaptopSize s13 = laptopSizeRepository.findById(1).orElseThrow();
        LaptopSize s14 = laptopSizeRepository.findById(2).orElseThrow();
        LaptopSize s15 = laptopSizeRepository.findById(3).orElseThrow();
        LaptopSize s17 = laptopSizeRepository.findById(4).orElseThrow();

        desktopRepository.save(new Desktop(1122, manufacturerAcer, 500, 29990, formFactorDesk));
        desktopRepository.save(new Desktop(3344, manufacturerAsus, 232, 15990, formFactorNett));
        desktopRepository.save(new Desktop(4455, manufacturerSams, 149, 20990, formFactorMono));

        laptopRepository.save(new Laptop(789, manufacturerAcer, 123, 29990, s13));
        laptopRepository.save(new Laptop(756, manufacturerAsus, 555, 15990, s14));
        laptopRepository.save(new Laptop(745, manufacturerSams, 63, 20990, s15));
        laptopRepository.save(new Laptop(723, manufacturerSams, 80, 10990, s17));

        monitorRepository.save(new Monitor(223, manufacturerAcer, 500, 29990, 27));
        monitorRepository.save(new Monitor(289, manufacturerSams, 149, 20990, 43));
        monitorRepository.save(new Monitor(247, manufacturerSams, 149, 20990, 24));

        hddRepository.save(new Hdd(789, manufacturerWd, 500, 7890, 4096));
        hddRepository.save(new Hdd(456, manufacturerWd, 232, 2459, 256));
        hddRepository.save(new Hdd(123, manufacturerSams, 149, 5990, 1024));
        hddRepository.save(new Hdd(101, manufacturerSams, 149, 3149, 512));
    }
}
