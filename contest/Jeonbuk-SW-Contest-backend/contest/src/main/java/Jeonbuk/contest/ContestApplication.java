package Jeonbuk.contest;

import Jeonbuk.contest.csv.CSVService;
import Jeonbuk.contest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class ContestApplication implements CommandLineRunner {
    private final CSVService csvService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(ContestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        csvService.saveCCTV();
//        csvService.saveStreetLamp();
//        csvService.saveWarningBell();
//        csvService.saveRestaurant();
//        csvService.saveDiscountStore();
//        csvService.saveTownStroll();
//        csvService.saveFestival();
//        Member member = Member.builder()
//                .id("abcd1234")
//                .password(passwordEncoder.encode("abcd1234"))
//                .phoneNumber("01012341234")
//                .emergencyNumber("01012341234")
//                .name("이승훈")
//                .build();
//        Member memberString = Member.builder()
//                .id("string")
//                .password(passwordEncoder.encode("string"))
//                .name("string")
//                .build();
//        memberRepository.save(memberString);
//        memberRepository.save(member);
    }

}
