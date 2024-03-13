package Jeonbuk.contest.service;

import Jeonbuk.contest.domain.MemberRegisterDTO;
import Jeonbuk.contest.entity.Member;
import Jeonbuk.contest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public String registerMember(MemberRegisterDTO memberRegisterDTO) {
        Member member = Member.builder()
                .id(memberRegisterDTO.getId())
                .password(passwordEncoder.encode(memberRegisterDTO.getPassword()))
                .build();
        memberRepository.save(member);
        return memberRegisterDTO.getId();
    }

    public ResponseEntity checkDuplicateId(String memberId) {
        if (memberRepository.existsById(memberId)) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity(HttpStatus.OK);
        }
    }
}
