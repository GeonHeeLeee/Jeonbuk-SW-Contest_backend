package Jeonbuk.contest.controller;

import Jeonbuk.contest.csv.CSVService;
import Jeonbuk.contest.domain.MemberAuthDTO;
import Jeonbuk.contest.domain.MemberInfoDTO;
import Jeonbuk.contest.entity.Member;
import Jeonbuk.contest.service.AccountService;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@WebMvcTest(AccountController.class)
@WithMockUser("member")
class AccountControllerTest {

    private final Gson gson = new Gson();

    @MockBean
    AccountService accountService;
    @MockBean
    MemberRepository memberRepository;
    @MockBean
    CSVService csvService;
    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("회원가입 테스트")
    void registerUserTest() throws Exception {
        given(accountService.registerMember(new MemberAuthDTO("testId", "testPassword")))
                .willReturn("testId");

        MemberAuthDTO memberAuthDTO = MemberAuthDTO.builder()
                .id("testId")
                .password("testPassword")
                .build();

        String requestBody = gson.toJson(memberAuthDTO);

        mockMvc.perform(post("/account/register")
                        .with(csrf())
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberId").value("testId"))
                .andDo(print());

        verify(accountService).registerMember(new MemberAuthDTO("testId", "testPassword"));
    }

    @Test
    @DisplayName("중복 ID 성공 테스트")
    void checkDuplicateIdSuccessTest() throws Exception {
        given(accountService.checkDuplicateId("existingMember"))
                .willReturn(new ResponseEntity(HttpStatus.OK));

        String memberId = "existingMember";

        mockMvc.perform(get("/account/register/" + memberId))
                .andExpect(status().isOk())
                .andDo(print());

        verify(accountService).checkDuplicateId(memberId);
    }

    @Test
    @DisplayName("중복 ID 실패 테스트")
    void checkDuplicateIdFailTest() throws Exception {
        given(accountService.checkDuplicateId("notExistingMember"))
                .willReturn(new ResponseEntity(HttpStatus.CONFLICT));

        String memberId = "notExistingMember";

        mockMvc.perform(get("/account/register/" + memberId))
                .andExpect(status().isConflict())
                .andDo(print());

        verify(accountService).checkDuplicateId(memberId);
    }

    @Test
    @DisplayName("사용자 정보 입력 성공 테스트")
    void registerMemberInfoSuccessTest() throws Exception {
        given(accountService.registerMemberInfo(new MemberInfoDTO("testId", "testName", "01012341234", "01043214321")))
                .willReturn(new ResponseEntity(HttpStatus.OK));

        MemberInfoDTO memberInfoDTO = MemberInfoDTO.builder()
                .id("testId")
                .name("testName")
                .phoneNumber("01012341234")
                .emergencyNumber("01043214321")
                .build();

        String requestBody = gson.toJson(memberInfoDTO);

        mockMvc.perform(post("/account/register/info")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andDo(print());

        verify(accountService).registerMemberInfo(memberInfoDTO);
    }

    @Test
    @DisplayName("사용자 정보 입력 실패 테스트")
    void registerMemberInfoFailTest() throws Exception {
        given(accountService.registerMemberInfo(new MemberInfoDTO("notExistingId", "testName", "01012341234", "01043214321")))
                .willReturn(new ResponseEntity(HttpStatus.NO_CONTENT));

        MemberInfoDTO memberInfoDTO = MemberInfoDTO.builder()
                .id("notExistingId")
                .name("testName")
                .phoneNumber("01012341234")
                .emergencyNumber("01043214321")
                .build();


        String requestBody = gson.toJson(memberInfoDTO);

        mockMvc.perform(post("/account/register/info")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNoContent())
                .andDo(print());

        verify(accountService).registerMemberInfo(memberInfoDTO);
    }

    @Test
    @DisplayName("JWT를 이용한 로그인 테스트")
    void memberLogin() throws Exception {
        given(memberRepository.findById("member1"))
                .willReturn(Optional.ofNullable(Member.builder()
                        .id("member1")
                        .password("member1")
                        .phoneNumber("01012341234")
                        .emergencyNumber("01043214321")
                        .name("member1")
                        .build()));

        Map<String, String> map = new HashMap<>();
        map.put("id", "member1");
        map.put("password", "member1");

        String requestBody = gson.toJson(map);

        mockMvc.perform(post("/account/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(header().exists("Authorization"))
                .andDo(print());
    }
}
