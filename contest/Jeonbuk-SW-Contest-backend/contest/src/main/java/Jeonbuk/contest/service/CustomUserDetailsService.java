package Jeonbuk.contest.service;

import Jeonbuk.contest.entity.Member;
import Jeonbuk.contest.jwt.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findById(memberId);
        log.info("memberId : {}", memberId);
        if (member.isPresent()) {
            return new CustomUserDetails(member.get());
        }
        throw new UsernameNotFoundException("memberId not Found: " + memberId);
    }
}
