package Jeonbuk.contest.controller;

import Jeonbuk.contest.domain.MemberRegisterDTO;
import Jeonbuk.contest.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
@Tag(name = "로그인/회원가입", description = "로그인 및 회원가입 관련 API")
public class AccountController {

    private final AccountService accountService;

    @Operation(summary = "회원가입")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(name = "회원가입 응답 예시", value = "{\"memberId\": \"value\"}")
                    ))})
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Map> registerUser(@RequestBody MemberRegisterDTO memberRegisterDTO) {
        String memberId = accountService.registerMember(memberRegisterDTO);
        return ResponseEntity.ok(Collections.singletonMap("memberId", memberId));
    }

    @Operation(summary = "중복 아이디 검사")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "중복 아이디 존재하지 않음", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "409", description = "중복 아이디 존재", content = @Content(schema = @Schema(hidden = true)))})
    @GetMapping("/register/{memberId}")
    public ResponseEntity checkDuplicateId(@Parameter(description = "검사할 ID") @PathVariable(value = "memberId") String memberId) {
        return accountService.checkDuplicateId(memberId);
    }
}


