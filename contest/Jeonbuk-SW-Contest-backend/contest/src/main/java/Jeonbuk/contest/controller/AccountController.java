package Jeonbuk.contest.controller;

import Jeonbuk.contest.domain.MemberInfoDTO;
import Jeonbuk.contest.domain.MemberRegisterDTO;
import Jeonbuk.contest.exception.CustomException;
import Jeonbuk.contest.exception.ErrorCode;
import Jeonbuk.contest.exception.ErrorDTO;
import Jeonbuk.contest.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
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
    public ResponseEntity<Object> registerUser(@RequestBody MemberRegisterDTO memberRegisterDTO) {
        String memberId = accountService.registerMember(memberRegisterDTO);
        return ResponseEntity.ok(Collections.singletonMap("memberId", memberId));
    }

    @Operation(summary = "중복 아이디 검사")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "중복 아이디 존재하지 않음", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "409", description = "중복 아이디 존재", content = @Content(schema = @Schema(hidden = true)))})
    @GetMapping("/register/{memberId}")
    public ResponseEntity<String> checkDuplicateId(@Parameter(description = "검사할 ID") @PathVariable(value = "memberId") String memberId) {
        return accountService.checkDuplicateId(memberId);
    }

    @Operation(summary = "이름, 전화번호, 비상연락망 등록")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정보 등록 성공", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "400", description = "핸드폰 번호, 비상 연락망 형식 일치하지 않거나 해당 Id의 사용자가 존재하지 않음", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @PostMapping("/register/info")
    public ResponseEntity<MemberInfoDTO> registerInfo(@Valid @RequestBody MemberInfoDTO memberInfoDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.PHONE_NUMBER_NOT_VALID);
        }
        return accountService.registerMemberInfo(memberInfoDTO);
    }
}


