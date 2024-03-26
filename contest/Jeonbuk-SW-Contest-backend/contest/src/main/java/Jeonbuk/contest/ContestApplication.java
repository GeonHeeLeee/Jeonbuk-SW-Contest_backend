package Jeonbuk.contest;

import Jeonbuk.contest.csv.CSVService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class ContestApplication implements CommandLineRunner {
    private final CSVService csvService;

    public static void main(String[] args) {
        SpringApplication.run(ContestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        csvService.saveCCTV();
        csvService.saveStreetLamp();
        csvService.saveWarningBell();
    }

}
