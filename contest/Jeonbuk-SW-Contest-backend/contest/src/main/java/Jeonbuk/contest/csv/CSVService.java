package Jeonbuk.contest.csv;

import Jeonbuk.contest.entity.restaurant.type.*;
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
import java.util.ArrayList;
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
        List<Model> modelRestaurantsList = new ArrayList<>();
        for (String[] row : rows) {
            Model modelRestaurant = Model.builder()
                    .storeName(row[5])
                    .roadAddress(row[7])
                    .locationAddress(row[8])
                    .foodType(row[18])
                    .mainFood(row[19])
                    .phoneNumber(row[24])
                    .build();
            modelRestaurantsList.add(modelRestaurant);
        }
        modelRestaurantRepository.saveAll(modelRestaurantsList);
    }

    @Transactional
    public void saveGoodPriceRestaurant() throws IOException, CsvException {
        List<String[]> rows = readCSV(GOOD_PRICE_RESTAURANT.getLocation());
        List<GoodPrice> goodPriceRestaurantList = new ArrayList<>();
        for (String[] row : rows) {

            GoodPrice goodPriceRestaurant = GoodPrice.builder()
                    .foodType(row[1])
                    .storeName(row[2])
                    .ownerName(row[3])
                    .roadAddress(row[4])
                    .mainFood(row[5])
                    .mainFoodPrice(row[6])
                    .openingHours(row[7])
                    .supportsDelivery(Boolean.parseBoolean(row[8]))
                    .isParkable(Boolean.parseBoolean(row[9]))
                    .promotion(row[10])
                    .build();

            goodPriceRestaurantList.add(goodPriceRestaurant);
        }
        goodPriceRestaurantRepository.saveAll(goodPriceRestaurantList);
    }

    @Transactional
    public void saveCultureNuriCard() throws IOException, CsvException {
        List<String[]> rows = readCSV(CULTURE_NURI_CARD.getLocation());
        List<CultureNuriCard> cultureNuriCardList = new ArrayList<>();
        for (String[] row : rows) {
            CultureNuriCard cultureNuriCard = CultureNuriCard.builder()
                    .storeName(row[2])
                    .storeType(row[3])
                    .roadAddress(row[4])
                    .isOnline(Boolean.parseBoolean(row[5]))
                    .phoneNumber(row[6])
                    .build();
            cultureNuriCardList.add(cultureNuriCard);
        }
        cultureNuriCardRepository.saveAll(cultureNuriCardList);
    }

    @Transactional
    public void saveChildMealCard() throws IOException, CsvException {
        List<String[]> rows = readCSV(CHILD_MEAL_CARD.getLocation());
        List<ChildMealCard> childMealCardList = new ArrayList<>();
        for (String[] row : rows) {
            ChildMealCard childMealCard = ChildMealCard.builder()
                    .storeName(row[2])
                    .roadAddress(row[3])
                    .build();
            childMealCardList.add(childMealCard);
        }
        childMealCardRepository.saveAll(childMealCardList);
    }

    @Transactional
    public void saveChildLikeCard() throws IOException, CsvException {
        List<String[]> rows = readCSV(CHILD_LIKE_CARD.getLocation());
        List<ChildLikeCard> childLikeCardList = new ArrayList<>();
        for (String[] row : rows) {
            ChildLikeCard childLikeCard = ChildLikeCard.builder()
                    .businessType(row[2])
                    .storeName(row[3])
                    .roadAddress(row[4])
                    .phoneNumber(row[5])
                    .promotion(row[6])
                    .build();
            childLikeCardList.add(childLikeCard);
        }
        childLikeCardRepository.saveAll(childLikeCardList);
    }

    public List<String[]> readCSV(String fileLocation) throws IOException, CsvException {
        try (CSVReader reader = new CSVReader(
                new InputStreamReader(new FileInputStream(fileLocation), StandardCharsets.UTF_8))) {
            reader.readNext(); //컬럼 패스
            return reader.readAll();
        }
    }
}
