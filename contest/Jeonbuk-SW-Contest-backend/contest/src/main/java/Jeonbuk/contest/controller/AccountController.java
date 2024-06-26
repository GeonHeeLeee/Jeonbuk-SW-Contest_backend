package Jeonbuk.contest.controller;

import Jeonbuk.contest.domain.MemberAuthDTO;
import Jeonbuk.contest.domain.MemberDTO;
import Jeonbuk.contest.domain.MemberInfoDTO;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody MemberDTO memberDTO) {
        return accountService.registerMember(memberDTO);
    }

    @Operation(summary = "중복 아이디 검사")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "중복 아이디 존재하지 않음", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "400", description = "중복 아이디 존재", content = @Content(schema = @Schema(hidden = true)))})
    @GetMapping("/register/{memberId}")
    public ResponseEntity<Void> checkDuplicateId(@Parameter(description = "검사할 ID") @PathVariable(value = "memberId") String memberId) {
        return accountService.checkDuplicateId(memberId);
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
    @PostMapping("/modify")
    public ResponseEntity<Void> modifyUserInfo(@Valid @RequestBody MemberInfoDTO memberInfoDTO) {
        return accountService.modifyUserInfo(memberInfoDTO);
    }

    @Operation(summary = "회원 탈퇴")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 탈퇴 성공", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "400", description = "회원 탈퇴 실패(아이디 존재하지 않음, 비밀번호 불일치)", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))})
    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, String>> deleteAccount(@RequestBody MemberAuthDTO memberAuthDTO) {
        return accountService.deleteAccount(memberAuthDTO);
    }

    @Operation(summary = "비밀번호 찾기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "비밀번호 변경 성공", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "400", description = "비밀번호 찾기 실패(회원정보 불일치)", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))})
    @PostMapping("/password/find")
    public ResponseEntity<Object> findPassword(@RequestBody MemberDTO memberDTO) {
        return accountService.findPassword(memberDTO);
    }


}


