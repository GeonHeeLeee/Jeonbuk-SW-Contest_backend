package Jeonbuk.contest.controller;

import Jeonbuk.contest.domain.SafeReturnRequestDTO;
import Jeonbuk.contest.domain.SafeReturnResponseDTO;
import Jeonbuk.contest.exception.ErrorDTO;
import Jeonbuk.contest.service.SafeReturnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "안심 귀가", description = "자신이 등록한 안심 귀가")
@Slf4j
@RestController
@RequestMapping("/SafeHome")
@RequiredArgsConstructor
public class SafeReturnController {

    private final SafeReturnService safeReturnService;

    @Operation(summary = "안심 귀가 등록",
            description = "출발점과 도착점을 받아 안심 귀가 등록")
    @PostMapping("/add")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 성공",
                    content = @Content(schema = @Schema(implementation = SafeReturnRequestDTO.class)
                    )),
            @ApiResponse(responseCode = "400", description = "해당 MemberId의 사용자 존재하지 않음", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<SafeReturnRequestDTO> addSafeReturn(SafeReturnRequestDTO safeReturnRequestDTO) {
        return safeReturnService.addSafeReturn(safeReturnRequestDTO);
    }

    @Operation(summary = "안심 귀가 조회",
            description = "자신이 등록한 안심 귀가 리스트 조회")
    @GetMapping("/list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "json 형태로 응답 반환 - key: safeReturnList, value: safeReturn이 담긴 리스트",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(name = "SafeReturnList", value = "{\"safeReturnList\": \"[]]\"}")
                    ))})
    public ResponseEntity<Map<String, List<SafeReturnResponseDTO>>> getSafeReturnList(@Parameter(description = "사용자 ID") @RequestParam("memberId") String memberId) {
        return safeReturnService.getSafeReturnList(memberId);
    }

    @Operation(summary = "안심 귀가 삭제",
            description = "자신이 등록한 안심 귀가 삭제")
    @DeleteMapping("/delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "삭제 성공"),
            @ApiResponse(responseCode = "400", description = "해당 안심 귀가 존재하지 않음")
    })
    public ResponseEntity<Void> deleteSafeReturn(@Parameter(description = "안심 귀가 ID") @RequestParam("safeReturnId") Long safeReturnId) {
        return safeReturnService.deleteSafeReturn(safeReturnId);
    }
}
