package Jeonbuk.contest.service;

import Jeonbuk.contest.entity.DiscountStore;
import Jeonbuk.contest.entity.Festival;
import Jeonbuk.contest.exception.CustomException;
import Jeonbuk.contest.repository.FestivalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static Jeonbuk.contest.exception.ErrorCode.DISCOUNT_STORE_NOT_FOUND_ID;
import static Jeonbuk.contest.exception.ErrorCode.FESTIVAL_NOT_FOUND_ID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FestivalService {
    private final FestivalRepository festivalRepository;

    public Festival findById(Long id) {
        return festivalRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, FESTIVAL_NOT_FOUND_ID));
    }

    public ResponseEntity<Page<Festival>> getAllFestivalPage(int page) {
        Page<Festival> festivalPage = festivalRepository.findAll(PageRequest.of(page, 10));
        return ResponseEntity.ok().body(festivalPage);
    }

}
