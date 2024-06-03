package Jeonbuk.contest.service;

import Jeonbuk.contest.domain.ReturnRouteRequestDTO;
import Jeonbuk.contest.domain.ReturnRouteResponseDTO;
import Jeonbuk.contest.entity.Member;
import Jeonbuk.contest.entity.safeReturn.ReturnRoute;
import Jeonbuk.contest.exception.CustomException;
import Jeonbuk.contest.exception.ErrorCode;
import Jeonbuk.contest.repository.ReturnRouteRepository;
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
public class ReturnRouteService {
    private final ReturnRouteRepository returnRouteRepository;
    private final MemberService memberService;

    public ResponseEntity<Map<String, Long>> addReturnRoute(ReturnRouteRequestDTO returnRouteRequestDTO) {
        Member member = memberService.findById(returnRouteRequestDTO.getMemberId());
        ReturnRoute returnRoute = ReturnRoute.builder()
                .name(returnRouteRequestDTO.getName())
                .member(member)
                .startLatitude(returnRouteRequestDTO.getStartLatitude())
                .startLongitude(returnRouteRequestDTO.getStartLongitude())
                .endLatitude(returnRouteRequestDTO.getEndLatitude())
                .endLongitude(returnRouteRequestDTO.getEndLongitude())
                .build();
        returnRouteRepository.save(returnRoute);
        return ResponseEntity.ok().body(Collections.singletonMap("returnRouteId", returnRoute.getId()));
    }

    public ResponseEntity<Map<String, List<ReturnRouteResponseDTO>>> getReturnRouteList(String memberId) {
        List<ReturnRouteResponseDTO> returnRouteResponseDTOList = returnRouteRepository.findAllByMemberId(memberId);
        Map<String, List<ReturnRouteResponseDTO>> json = new HashMap<>();
        json.put("ReturnRouteList", returnRouteResponseDTOList);
        return ResponseEntity.ok().body(json);
    }

    public ResponseEntity<Void> deleteReturnRoute(Long returnRouteId) {
        ReturnRoute returnRoute = returnRouteRepository.findById(returnRouteId)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.RETURN_ROUTE_NOT_FOUND_ID));
        returnRouteRepository.delete(returnRoute);
        return ResponseEntity.ok().build();
    }
}
