package Jeonbuk.contest.csv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class CSVService {


//    @Transactional
//    public void saveChildLikeCard() throws IOException, CsvException {
//        List<String[]> rows = readCSV(CHILD_LIKE_CARD.getLocation());
//        List<ChildLikeCard> childLikeCardList = new ArrayList<>();
//        for (String[] row : rows) {
//            ChildLikeCard childLikeCard = ChildLikeCard.builder()
//                    .businessType(row[2])
//                    .storeName(row[3])
//                    .roadAddress(row[4])
//                    .phoneNumber(row[5])
//                    .promotion(row[6])
//                    .build();
//            childLikeCardList.add(childLikeCard);
//        }
//        childLikeCardRepository.saveAll(childLikeCardList);
//    }


    public List<String[]> readCSV(String fileLocation) throws IOException, CsvException {
        try (CSVReader reader = new CSVReader(
                new InputStreamReader(new FileInputStream(fileLocation), StandardCharsets.UTF_8))) {
            reader.readNext(); //컬럼 패스
            return reader.readAll();
        }
    }
}
