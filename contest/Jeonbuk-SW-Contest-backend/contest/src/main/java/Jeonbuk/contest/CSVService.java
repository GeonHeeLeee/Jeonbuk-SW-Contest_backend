package Jeonbuk.contest;

import Jeonbuk.contest.entity.GoodPriceRestaurant;
import Jeonbuk.contest.entity.ModelRestaurant;
import Jeonbuk.contest.repository.GoodPriceRestaurantRepository;
import Jeonbuk.contest.repository.ModelRestaurantRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CSVService {

    private static String BASIC_FILE_LOCATION = "C:\\Users\\LeeGeonHee\\Desktop\\after_school\\24년상반기 SW개발공모전\\data\\2\\";
    private static String MODEL_RESTAURANT_LOCATION = BASIC_FILE_LOCATION + "model_restaurant.csv";
    private static String GOOD_PRICE_RESTAURANT_LOCATION = BASIC_FILE_LOCATION + "good_price_restaurant.csv";
    private final ModelRestaurantRepository modelRestaurantRepository;
    private final GoodPriceRestaurantRepository goodPriceRestaurantRepository;


    @Transactional
    public void saveModelRestaurant() throws IOException, CsvException {
        List<String[]> rows = readCSV(MODEL_RESTAURANT_LOCATION);
        for (String[] row : rows) {
            ModelRestaurant modelRestaurant = new ModelRestaurant();
            modelRestaurant.setStoreName(row[5]);
            modelRestaurant.setRoadAddress(row[7]);
            modelRestaurant.setLocationAddress(row[8]);
            modelRestaurant.setFoodType(row[18]);
            modelRestaurant.setMainFood(row[19]);
            modelRestaurant.setPhoneNumber(row[24]);
            modelRestaurantRepository.save(modelRestaurant);
        }
    }

    @Transactional
    public void saveGoodPriceRestaurant() throws IOException, CsvException {
        List<String[]> rows = readCSV(GOOD_PRICE_RESTAURANT_LOCATION);
        for (String[] row : rows) {
            GoodPriceRestaurant goodPriceRestaurant = new GoodPriceRestaurant();
            goodPriceRestaurant.setFoodType(row[1]);
            goodPriceRestaurant.setStoreName(row[2]);
            goodPriceRestaurant.setOwnerName(row[3]);
            goodPriceRestaurant.setRoadAddress(row[4]);
            goodPriceRestaurant.setMainFood(row[5]);
            goodPriceRestaurant.setMainFoodPrice(row[6]);
            goodPriceRestaurant.setOpeningHours(row[7]);
            goodPriceRestaurant.setDelivery(Boolean.parseBoolean(row[8]));
            goodPriceRestaurant.setParkable(Boolean.parseBoolean(row[9]));
            goodPriceRestaurant.setPromotion(row[10]);
            goodPriceRestaurantRepository.save(goodPriceRestaurant);
        }
    }

    public List<String[]> readCSV(String fileLocation) throws IOException, CsvException {
        try (CSVReader reader = new CSVReader(
                new InputStreamReader(new FileInputStream(fileLocation), StandardCharsets.UTF_8))) {
            return reader.readAll();
        }
    }
}
