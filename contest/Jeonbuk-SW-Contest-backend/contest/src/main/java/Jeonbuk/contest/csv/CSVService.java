package Jeonbuk.contest.csv;

import Jeonbuk.contest.entity.*;
import Jeonbuk.contest.repository.*;
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

import static Jeonbuk.contest.csv.FILE_LOCATION.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class CSVService {

    private final ModelRestaurantRepository modelRestaurantRepository;
    private final GoodPriceRestaurantRepository goodPriceRestaurantRepository;
    private final CultureNuriCardRepository cultureNuriCardRepository;
    private final ChildMealCardRepository childMealCardRepository;
    private final ChildLikeCardRepository childLikeCardRepository;


    @Transactional
    public void saveModelRestaurant() throws IOException, CsvException {
        List<String[]> rows = readCSV(MODEL_RESTAURANT.getLocation());
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
        List<String[]> rows = readCSV(GOOD_PRICE_RESTAURANT.getLocation());
        for (String[] row : rows) {
            GoodPriceRestaurant goodPriceRestaurant = new GoodPriceRestaurant();
            goodPriceRestaurant.setFoodType(row[1]);
            goodPriceRestaurant.setStoreName(row[2]);
            goodPriceRestaurant.setOwnerName(row[3]);
            goodPriceRestaurant.setRoadAddress(row[4]);
            goodPriceRestaurant.setMainFood(row[5]);
            goodPriceRestaurant.setMainFoodPrice(row[6]);
            goodPriceRestaurant.setOpeningHours(row[7]);
            goodPriceRestaurant.setSupportsDelivery(Boolean.parseBoolean(row[8]));
            goodPriceRestaurant.setParkable(Boolean.parseBoolean(row[9]));
            goodPriceRestaurant.setPromotion(row[10]);
            goodPriceRestaurantRepository.save(goodPriceRestaurant);
        }
    }

    @Transactional
    public void saveCultureNuriCard() throws IOException, CsvException {
        List<String[]> rows = readCSV(CULTURE_NURI_CARD.getLocation());
        for (String[] row : rows) {
            CultureNuriCard cultureNuriCard = new CultureNuriCard();
            cultureNuriCard.setStoreName(row[2]);
            cultureNuriCard.setStoreType(row[3]);
            cultureNuriCard.setRoadAddress(row[4]);
            cultureNuriCard.setOnline(Boolean.parseBoolean(row[5]));
            cultureNuriCard.setPhoneNumber(row[6]);
            cultureNuriCardRepository.save(cultureNuriCard);
        }
    }

    @Transactional
    public void saveChildMealCard() throws IOException, CsvException {
        List<String[]> rows = readCSV(CHILD_MEAL_CARD.getLocation());
        for (String[] row : rows) {
            ChildMealCard childMealCard = new ChildMealCard();
            childMealCard.setStoreName(row[2]);
            childMealCard.setRoadName(row[3]);
            childMealCardRepository.save(childMealCard);
        }
    }

    @Transactional
    public void saveChildLikeCard() throws IOException, CsvException {
        List<String[]> rows = readCSV(CHILD_LIKE_CARD.getLocation());
        for (String[] row : rows) {
            ChildLikeCard childLikeCard = new ChildLikeCard();
            childLikeCard.setBusinessType(row[2]);
            childLikeCard.setStoreName(row[3]);
            childLikeCard.setRoadName(row[4]);
            childLikeCard.setPhoneNumber(row[5]);
            childLikeCard.setPromotion(row[6]);
            childLikeCardRepository.save(childLikeCard);
        }
    }

    public List<String[]> readCSV(String fileLocation) throws IOException, CsvException {
        try (CSVReader reader = new CSVReader(
                new InputStreamReader(new FileInputStream(fileLocation), StandardCharsets.UTF_8))) {
            reader.readNext(); //컬럼 패스
            return reader.readAll();
        }
    }
}
