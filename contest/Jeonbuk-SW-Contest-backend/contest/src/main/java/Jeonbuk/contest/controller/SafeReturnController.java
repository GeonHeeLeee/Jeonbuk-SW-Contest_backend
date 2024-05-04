package Jeonbuk.contest.controller;

import Jeonbuk.contest.domain.SafeReturnDTO;
import Jeonbuk.contest.exception.ErrorDTO;
import Jeonbuk.contest.repository.SafeReturnRepository;
import Jeonbuk.contest.service.SafeReturnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                    content = @Content(schema = @Schema(implementation = SafeReturnDTO.class)
                    )),
            @ApiResponse(responseCode = "400", description = "해당 MemberId의 사용자 존재하지 않음", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<SafeReturnDTO> addSafeReturn(SafeReturnDTO safeReturnDTO) {
        return safeReturnService.addSafeReturn(safeReturnDTO);
    }

}
