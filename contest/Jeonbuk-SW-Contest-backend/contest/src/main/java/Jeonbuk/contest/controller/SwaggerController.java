package Jeonbuk.contest.controller;

import Jeonbuk.contest.entity.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Tag(name = "데모", description = "Swagger Demo API")
@RequestMapping("/swagger")
public class SwaggerController {

    @Operation(summary = "GET 메서드 예제", description = "Demo 조회 메서드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = Member.class))),
            @ApiResponse(responseCode = "400", description = "조회 실패", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @GetMapping("/test")
    public ResponseEntity getTest() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
