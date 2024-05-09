package Jeonbuk.contest.service;

import Jeonbuk.contest.entity.safeReturn.CCTV;
import Jeonbuk.contest.entity.safeReturn.MySafeReturn;
import Jeonbuk.contest.entity.safeReturn.StreetLamp;
import Jeonbuk.contest.entity.safeReturn.WarningBell;
import Jeonbuk.contest.exception.CustomException;
import Jeonbuk.contest.exception.ErrorCode;
import Jeonbuk.contest.repository.MySafeReturnRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MySafeReturnService {

    private final MySafeReturnRepository mySafeReturnRepository;

    public ResponseEntity<?> getSafeReturnAround(float latitude, float longitude, float radius) {
        List<MySafeReturn> mySafeReturnList = mySafeReturnRepository.findWithinRadius(latitude, longitude, radius);
        return ResponseEntity.ok().body(Collections.singletonMap("content", mySafeReturnList));
    }

    public ResponseEntity<?> getSafeReturnAroundByType(float latitude, float longitude, float radius, String type) {
        Class<?> classType;
        if (type.equals("CCTV")) {
            classType = CCTV.class;
        } else if (type.equals("StreetLamp")) {
            classType = StreetLamp.class;
        } else if (type.equals("WarningBell")) {
            classType = WarningBell.class;
        } else {
            throw new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.MY_SAFE_RETURN_TYPE_NOT_VALID);
        }
        List<MySafeReturn> mySafeReturnList = mySafeReturnRepository.findWithinRadiusByType(latitude, longitude, radius, classType);
        return ResponseEntity.ok().body(Collections.singletonMap(type, mySafeReturnList));
    }

}
