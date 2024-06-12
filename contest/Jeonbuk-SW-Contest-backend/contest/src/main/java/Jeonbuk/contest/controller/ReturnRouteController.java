package Jeonbuk.contest.controller;

import Jeonbuk.contest.domain.ReturnRouteRequestDTO;
import Jeonbuk.contest.domain.ReturnRouteResponseDTO;
import Jeonbuk.contest.exception.ErrorDTO;
import Jeonbuk.contest.service.ReturnRouteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "귀가 경로", description = "자신이 등록한 귀가 경로")
@Slf4j
@RestController
@RequestMapping("/returnRoute")
@RequiredArgsConstructor
public class ReturnRouteController {

    private final ReturnRouteService returnRouteService;

    @Operation(summary = "귀가 경로 등록",
            description = "출발점과 도착점(위도, 경도)을 받아 귀가 경로 등록")
    @PostMapping("/add")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 성공",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(name = "returnRouteId", value = "{\"returnRouteId\": \"(Long)returnRouteId\"}")
                    )),
            @ApiResponse(responseCode = "400", description = "해당 MemberId의 사용자 존재하지 않음", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<Map<String, Long>> addReturnRoute(@Parameter(name = "ReturnRouteRequestDTO") ReturnRouteRequestDTO returnRouteRequestDTO) {
        return returnRouteService.addReturnRoute(returnRouteRequestDTO);
    }

    @Operation(summary = "귀가 경로 조회",
            description = "자신이 등록한 귀가 경로 리스트 조회")
    @GetMapping("/list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "json 형태로 응답 반환 - key: ReturnRouteList, value: returnRoute가 담긴 리스트",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(name = "ReturnRouteList", value = "{\"ReturnRouteList\": \"[]\"}")
                    ))})
    public ResponseEntity<Map<String, List<ReturnRouteResponseDTO>>> getSafeReturnList(@Parameter(description = "사용자 ID") @RequestParam("memberId") String memberId) {
        return returnRouteService.getReturnRouteList(memberId);
    }

    @Operation(summary = "귀가 경로 삭제",
            description = "자신이 등록한 귀가 경로 삭제")
    @DeleteMapping("/delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "삭제 성공"),
            @ApiResponse(responseCode = "400", description = "해당 귀가 경로 존재하지 않음")
    })
    public ResponseEntity<Void> deleteSafeReturn(@Parameter(description = "귀가 경로 ID") @RequestParam("returnRouteId") Long returnRouteId) {
        return returnRouteService.deleteReturnRoute(returnRouteId);
    }
}
