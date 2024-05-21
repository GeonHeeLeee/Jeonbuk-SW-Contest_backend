package Jeonbuk.contest.service;

import Jeonbuk.contest.domain.MemberDTO;
import Jeonbuk.contest.domain.MemberInfoDTO;
import Jeonbuk.contest.domain.MemberAuthDTO;
import Jeonbuk.contest.entity.Member;
import Jeonbuk.contest.exception.CustomException;
import Jeonbuk.contest.exception.ErrorCode;
import Jeonbuk.contest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public String registerMember(MemberAuthDTO memberAuthDTO) {
        Member member = Member.builder()
                .id(memberAuthDTO.getId())
                .password(passwordEncoder.encode(memberAuthDTO.getPassword()))
                .build();
        memberRepository.save(member);
        log.info("[registerUser] 회원가입 성공 - memberId: {}", member.getId());
        return memberAuthDTO.getId();
    }

    public ResponseEntity<Map<String, String>> deleteAccount(MemberAuthDTO memberAuthDTO){
        Member member = memberRepository.findById(memberAuthDTO.getId())
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.MEMBER_NOT_FOUND_ID));
        if(member.getPassword().matches(passwordEncoder.encode(memberAuthDTO.getPassword()))) {
            memberRepository.delete(member);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body(Collections.singletonMap("errorMessage", "비밀번호가 일치하지 않습니다."));
        }
    }

    public ResponseEntity<Object> findPassword(MemberDTO memberDTO) {
        Member member = memberRepository.findById(memberDTO.getId()).orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.MEMBER_NOT_FOUND_ID));
        if(member.getName().equals(memberDTO.getName()) && member.getPhoneNumber().equals(memberDTO.getPhoneNumber()) && member.getEmergencyNumber().equals(memberDTO.getEmergencyNumber())) {
            member.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body(Collections.singletonMap("errorMessage", "회원정보가 일치하지 않습니다."));
        }
    }

    @Transactional
    public ResponseEntity<Void> registerMemberInfo(MemberInfoDTO memberInfoDTO) {
        Member member = memberRepository.findById(memberInfoDTO.getId())
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.MEMBER_NOT_FOUND_ID));
        member.setName(memberInfoDTO.getName());
        member.setEmergencyNumber(memberInfoDTO.getEmergencyNumber());
        member.setPhoneNumber(memberInfoDTO.getPhoneNumber());
        log.info("[registerMemberInfo] 회원정보 등록 성공 - name: {}, emergencyNumber: {}, phoneNumber: {}", memberInfoDTO.getName(), memberInfoDTO.getEmergencyNumber(), memberInfoDTO.getPhoneNumber());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> checkDuplicateId(String memberId) {
        if (memberRepository.existsById(memberId)) {
            log.info("[checkDuplicateId] 중복 ID 검사 실패 - memberId: {}", memberId);
            return ResponseEntity.badRequest().build();
        } else {
            log.info("[checkDuplicateId] 중복 ID 검사 성공 - memberId: {}", memberId);
            return ResponseEntity.ok().build();
        }
    }

    @Transactional
    public ResponseEntity<Void> modifyUserInfo(MemberInfoDTO memberInfoDTO) {
        Member member = memberRepository.findById(memberInfoDTO.getId())
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.MEMBER_NOT_FOUND_ID));
        member.setEmergencyNumber(memberInfoDTO.getEmergencyNumber());
        member.setPhoneNumber(memberInfoDTO.getPhoneNumber());
        member.setName(member.getName());
        return ResponseEntity.ok().build();
    }
}
