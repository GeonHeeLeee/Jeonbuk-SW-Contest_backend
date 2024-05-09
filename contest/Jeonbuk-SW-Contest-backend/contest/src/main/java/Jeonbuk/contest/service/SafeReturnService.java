package Jeonbuk.contest.service;

import Jeonbuk.contest.domain.SafeReturnRequestDTO;
import Jeonbuk.contest.domain.SafeReturnResponseDTO;
import Jeonbuk.contest.entity.Member;
import Jeonbuk.contest.entity.safeReturn.SafeReturn;
import Jeonbuk.contest.exception.CustomException;
import Jeonbuk.contest.exception.ErrorCode;
import Jeonbuk.contest.repository.SafeReturnRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class SafeReturnService {
    private final SafeReturnRepository safeReturnRepository;
    private final MemberService memberService;

    public ResponseEntity<Map<String, Long>> addSafeReturn(SafeReturnRequestDTO safeReturnRequestDTO) {
        Member member = memberService.findById(safeReturnRequestDTO.getMemberId());
        SafeReturn safeReturn = SafeReturn.builder()
                .name(safeReturnRequestDTO.getName())
                .member(member)
                .startLatitude(safeReturnRequestDTO.getStartLatitude())
                .startLongitude(safeReturnRequestDTO.getStartLongitude())
                .endLatitude(safeReturnRequestDTO.getEndLatitude())
                .endLongitude(safeReturnRequestDTO.getEndLongitude())
                .build();
        safeReturnRepository.save(safeReturn);
        return ResponseEntity.ok().body(Collections.singletonMap("safeReturnId", safeReturn.getId()));
    }

    public ResponseEntity<Map<String, List<SafeReturnResponseDTO>>> getSafeReturnList(String memberId) {
        List<SafeReturnResponseDTO> safeReturnResponseDTOList = safeReturnRepository.findAllByMemberId(memberId);
        Map<String, List<SafeReturnResponseDTO>> json = new HashMap<>();
        json.put("safeReturnList", safeReturnResponseDTOList);
        return ResponseEntity.ok().body(json);
    }

    public ResponseEntity<Void> deleteSafeReturn(Long safeReturnId) {
        SafeReturn safeReturn = safeReturnRepository.findById(safeReturnId)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.SAFE_RETURN_NOT_FOUND_ID));
        safeReturnRepository.delete(safeReturn);
        return ResponseEntity.ok().build();
    }
}
