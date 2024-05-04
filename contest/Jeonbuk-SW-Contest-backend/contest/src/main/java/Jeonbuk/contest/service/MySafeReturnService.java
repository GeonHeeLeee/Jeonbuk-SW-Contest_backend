package Jeonbuk.contest.service;

import Jeonbuk.contest.entity.safeReturn.MySafeReturn;
import Jeonbuk.contest.repository.MySafeReturnRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

}
