package Jeonbuk.contest.jwt;

import Jeonbuk.contest.entity.Member;
import Jeonbuk.contest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByMemberId(memberId);

        if (member.isPresent()) {
            return new CustomUserDetails(member.get());
        }

        return null;
    }
}
