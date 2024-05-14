package Jeonbuk.contest.service;

import Jeonbuk.contest.entity.TownStroll;
import Jeonbuk.contest.exception.CustomException;
import Jeonbuk.contest.exception.ErrorCode;
import Jeonbuk.contest.repository.TownStrollRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class TownStrollService {
    private final TownStrollRepository townStrollRepository;

    public TownStroll findById(Long id) {
        return townStrollRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.TOWN_STROLL_NOT_FOUND_ID));
    }

    public ResponseEntity<Map<String, List<TownStroll>>> getAllTownStrollWithinRadius(float latitude, float longitude, float radius) {
        Map<String, List<TownStroll>> response = new HashMap<>();
        response.put("townStrollList", townStrollRepository.findWithinRadius(latitude, longitude, radius));
        return ResponseEntity.ok().body(response);
    }
}
