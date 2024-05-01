package Jeonbuk.contest.controller;

import Jeonbuk.contest.domain.MemberInfoDTO;
import Jeonbuk.contest.domain.MemberAuthDTO;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Slf4j
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
    public ResponseEntity<Object> registerUser(@RequestBody MemberAuthDTO memberAuthDTO) {
        String memberId = accountService.registerMember(memberAuthDTO);
        return ResponseEntity.ok(Collections.singletonMap("memberId", memberId));
    }

    @Operation(summary = "중복 아이디 검사")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "중복 아이디 존재하지 않음", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "409", description = "중복 아이디 존재", content = @Content(schema = @Schema(hidden = true)))})
    @GetMapping("/register/{memberId}")
    public ResponseEntity<Void> checkDuplicateId(@Parameter(description = "검사할 ID") @PathVariable(value = "memberId") String memberId) {
        return accountService.checkDuplicateId(memberId);
    }

    @Operation(summary = "이름, 전화번호, 비상연락망 등록")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정보 등록 성공", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "400", description = "핸드폰 번호, 비상 연락망 형식 일치하지 않거나 해당 Id의 사용자가 존재하지 않음", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @PostMapping("/register/info")
    public ResponseEntity<Void> registerInfo(@Valid @RequestBody MemberInfoDTO memberInfoDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.PHONE_NUMBER_NOT_VALID);
        }
        return accountService.registerMemberInfo(memberInfoDTO);
    }

    @Operation(summary = "사용자 로그인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공-Authorization Header에 JWT 응답", content = @Content(schema = @Schema(implementation = MemberInfoDTO.class))),
            @ApiResponse(responseCode = "401", description = "로그인 실패", content = @Content(schema = @Schema(hidden = true)))})
    @PostMapping("/login")
    public void login(@RequestBody MemberAuthDTO memberAuthDTO) {
        //Login은 Security Filter에서 처리
    }


    @Operation(summary = "이름, 전화번호, 비상연락망 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정보 등록 성공", content = @Content(schema = @Schema(implementation = MemberInfoDTO.class))),
            @ApiResponse(responseCode = "400", description = "핸드폰 번호, 비상 연락망 형식 일치하지 않거나 해당 Id의 사용자가 존재하지 않음", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @PostMapping("/register/info")
    public ResponseEntity<Void> modifyUserInfo(@Valid @RequestBody MemberInfoDTO memberInfoDTO) {
        return accountService.modifyUserInfo(memberInfoDTO);
    }

}


