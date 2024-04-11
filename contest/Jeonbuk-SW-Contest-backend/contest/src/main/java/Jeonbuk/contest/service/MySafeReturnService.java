package Jeonbuk.contest.service;

import Jeonbuk.contest.entity.safeReturn.SafeReturn;
import Jeonbuk.contest.repository.SafeReturnRepository;
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

    private final SafeReturnRepository safeReturnRepository;
    public ResponseEntity<?> getSafeReturnAround(float latitude, float longitude, float radius) {
        List<SafeReturn> safeReturnList = safeReturnRepository.findWithinRadius(latitude, longitude, radius);
        return ResponseEntity.ok().body(Collections.singletonMap("content", safeReturnList));
    }

    public ResponseEntity<?> test() {
        SafeReturn list = safeReturnRepository.findById(Long.valueOf(22)).get();
        return ResponseEntity.ok().body(list);
    }
}
