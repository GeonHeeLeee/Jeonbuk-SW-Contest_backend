package Jeonbuk.contest.service;

import Jeonbuk.contest.domain.SafeReturnDTO;
import Jeonbuk.contest.entity.Member;
import Jeonbuk.contest.entity.safeReturn.SafeReturn;
import Jeonbuk.contest.repository.MemberRepository;
import Jeonbuk.contest.repository.SafeReturnRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SafeReturnService {
    private final SafeReturnRepository safeReturnRepository;
    private final MemberService memberService;

    public ResponseEntity<SafeReturnDTO> addSafeReturn(SafeReturnDTO safeReturnDTO) {
        Member member = memberService.findById(safeReturnDTO.getMemberId());
        SafeReturn safeReturn = SafeReturn.builder()
                .name(safeReturnDTO.getName())
                .member(member)
                .startLatitude(safeReturnDTO.getStartLatitude())
                .startLongitude(safeReturnDTO.getStartLongitude())
                .endLatitude(safeReturnDTO.getEndLatitude())
                .endLongitude(safeReturnDTO.getEndLongitude())
                .build();
        safeReturnRepository.save(safeReturn);
        return ResponseEntity.ok().body(safeReturnDTO);
    }
}
