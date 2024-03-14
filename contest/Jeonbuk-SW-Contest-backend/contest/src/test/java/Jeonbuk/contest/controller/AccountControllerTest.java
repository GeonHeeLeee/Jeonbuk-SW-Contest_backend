package Jeonbuk.contest.controller;

import Jeonbuk.contest.csv.CSVService;
import Jeonbuk.contest.domain.MemberRegisterDTO;
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

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc
public class AccountControllerTest {

    @MockBean
    AccountService accountService;
    @MockBean
    CSVService csvService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("회원가입 테스트")
    @WithMockUser("member")
    void registerUserTest() throws Exception {
        given(accountService.registerMember(new MemberRegisterDTO("testId", "testPassword")))
                .willReturn("testId");

        MemberRegisterDTO memberRegisterDTO = MemberRegisterDTO.builder()
                .id("testId")
                .password("testPassword")
                .build();

        Gson gson = new Gson();
        String requestBody = gson.toJson(memberRegisterDTO);

        mockMvc.perform(post("/account/register")
                        .with(csrf())
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberId").value("testId"))
                .andDo(print());

        verify(accountService).registerMember(new MemberRegisterDTO("testId", "testPassword"));
    }

    @Test
    @DisplayName("중복 ID 성공 테스트")
    @WithMockUser("member")
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
    @WithMockUser("member")
    void checkDuplicateIdFailTest() throws Exception {
        given(accountService.checkDuplicateId("notExistingMember"))
                .willReturn(new ResponseEntity(HttpStatus.CONFLICT));

        String memberId = "notExistingMember";

        mockMvc.perform(get("/account/register/" + memberId))
                .andExpect(status().isConflict())
                .andDo(print());

        verify(accountService).checkDuplicateId(memberId);
    }
}
