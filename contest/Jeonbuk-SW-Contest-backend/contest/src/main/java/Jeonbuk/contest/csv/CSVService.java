package Jeonbuk.contest.csv;

import Jeonbuk.contest.entity.safeReturn.CCTV;
import Jeonbuk.contest.entity.safeReturn.StreetLamp;
import Jeonbuk.contest.entity.safeReturn.WarningBell;
import Jeonbuk.contest.repository.CCTVRepository;
import Jeonbuk.contest.repository.StreetLampRepository;
import Jeonbuk.contest.repository.WarningBellRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static Jeonbuk.contest.csv.FILE_LOCATION.RELATIVE_PATH;


@Slf4j
@RequiredArgsConstructor
@Service
public class CSVService {

    private final CCTVRepository cctvRepository;
    private final StreetLampRepository streetLampRepository;
    private final WarningBellRepository warningBellRepository;

    @Transactional
    public void saveCCTV() throws IOException, CsvException {
        List<String[]> rows = readCSV(RELATIVE_PATH.getLocation() + "cctv.csv");
        List<CCTV> cctvList = new ArrayList<>();
        for (String[] row : rows) {
            CCTV cctv = CCTV.builder()
                    .roadAddress(row[1])
                    .localAddress(row[2])
                    .cameraCount(parseIntOrDefault(row[3]))
                    .cameraPixels(parseIntOrDefault(row[4]))
                    .filmingInformation(row[5])
                    .storageDays(parseIntOrDefault(row[6]))
                    .installDate(parseDateOrDefault(row[7]))
                    .latitude(parseFloatOrDefault(row[8]))
                    .longitude(parseFloatOrDefault(row[9]))
                    .build();
            cctvList.add(cctv);
        }
        cctvRepository.saveAll(cctvList);
    }

    @Transactional
    public void saveWarningBell() throws IOException, CsvException {
        List<String[]> rows = readCSV(RELATIVE_PATH.getLocation() + "warningBell.csv");
        List<WarningBell> warningBellList = new ArrayList<>();
        for (String[] row : rows) {
            WarningBell warningBell = WarningBell.builder()
                    .managementNumber(row[1])
                    .installPurpose(row[2])
                    .installtype(row[3])
                    .installLocation(row[4])
                    .roadAddress(row[5])
                    .latitude(parseFloatOrDefault(row[6]))
                    .longitude(parseFloatOrDefault(row[7]))
                    .linkageMethod(row[8])
                    .isPoliceLinked(parseBoolean(row[9]))
                    .isManagingOfficeLinked(parseBoolean(row[10]))
                    .isSecurityLinked(parseBoolean(row[11]))
                    .installYear(row[12])
                    .managingOfficeName(row[13])
                    .managingOfficePhoneNumber(row[14])
                    .build();
            warningBellList.add(warningBell);
        }
        warningBellRepository.saveAll(warningBellList);
    }

    public void saveStreetLamp() throws IOException, CsvException {
        List<String[]> rows = readCSV(RELATIVE_PATH.getLocation() + "streetlamp.csv");
        List<StreetLamp> streetLampList = new ArrayList<>();
        for (String[] row : rows) {
            StreetLamp streetLamp = StreetLamp.builder()
                    .latitude(parseFloatOrDefault(row[1]))
                    .longitude(parseFloatOrDefault(row[2]))
                    .administrativeCode(row[3])
                    .build();
            streetLampList.add(streetLamp);
        }
        streetLampRepository.saveAll(streetLampList);

    }

    public List<String[]> readCSV(String fileLocation) throws IOException, CsvException {
        ClassPathResource resource = new ClassPathResource(fileLocation);
        try (CSVReader reader = new CSVReader(
                new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            reader.readNext(); //컬럼 패스
            return reader.readAll();
        }
    }

    public int parseIntOrDefault(String str) {
        if (str == null || str.trim().isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(str);
        }
    }

    public float parseFloatOrDefault(String str) {
        if (str == null || str.trim().isEmpty()) {
            return 0;
        } else {
            return Float.parseFloat(str);
        }
    }

    public LocalDate parseDateOrDefault(String str) {
        if (str == null || str.trim().isEmpty()) {
            return LocalDate.parse("1900-01-01");
        } else {
            return LocalDate.parse(str);
        }
    }

    public Boolean parseBoolean(String str) {
        if("Y".equalsIgnoreCase(str)){
            return true;
        } else if ("N".equalsIgnoreCase(str)){
            return false;
        }
        throw new IllegalArgumentException("입력은 Y 또는 N 이어야 합니다");
    }
}
