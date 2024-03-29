package Jeonbuk.contest.service;

import Jeonbuk.contest.domain.MemberInfoDTO;
import Jeonbuk.contest.domain.MemberAuthDTO;
import Jeonbuk.contest.entity.Member;
import Jeonbuk.contest.exception.CustomException;
import Jeonbuk.contest.exception.ErrorCode;
import Jeonbuk.contest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return memberAuthDTO.getId();
    }

    @Transactional
    public ResponseEntity<Void> registerMemberInfo(MemberInfoDTO memberInfoDTO) {
        Member member = memberRepository.findById(memberInfoDTO.getId())
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.MEMBER_NOT_FOUND_ID));
        member.setName(memberInfoDTO.getName());
        member.setEmergencyNumber(memberInfoDTO.getEmergencyNumber());
        member.setPhoneNumber(memberInfoDTO.getPhoneNumber());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> checkDuplicateId(String memberId) {
        if (memberRepository.existsById(memberId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
