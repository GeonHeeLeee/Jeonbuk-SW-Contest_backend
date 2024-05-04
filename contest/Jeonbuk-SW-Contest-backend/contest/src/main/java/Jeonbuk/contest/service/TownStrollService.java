package Jeonbuk.contest.service;

import Jeonbuk.contest.entity.TownStroll;
import Jeonbuk.contest.exception.CustomException;
import Jeonbuk.contest.exception.ErrorCode;
import Jeonbuk.contest.repository.FestivalRepository;
import Jeonbuk.contest.repository.TownStrollRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TownStrollService {
    private final TownStrollRepository townStrollRepository;
    public TownStroll findById(Long id) {
        return townStrollRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.TOWN_STROLL_NOT_FOUND_ID));
    }
}
