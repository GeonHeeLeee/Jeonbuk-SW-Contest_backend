package Jeonbuk.contest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class ContestApplication implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		csvService.saveModelRestaurant();
		csvService.saveGoodPriceRestaurant();
		csvService.saveCultureNuriCard();
		csvService.saveChildMealCard();
		csvService.saveChildLikeCard();
	}

	private final CSVService csvService;

	public static void main(String[] args) {
		SpringApplication.run(ContestApplication.class, args);
	}

}
