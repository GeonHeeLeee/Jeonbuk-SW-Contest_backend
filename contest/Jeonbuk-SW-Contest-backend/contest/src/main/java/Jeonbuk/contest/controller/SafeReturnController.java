package Jeonbuk.contest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "안심 귀가", description = "자신이 등록한 안심 귀가")
@Slf4j
@RestController
@RequestMapping("/SafeHome")
@RequiredArgsConstructor
public class SafeReturnController {

    @Operation(summary = "안심 귀가 등록",
            description = "출발점과 도착점을 받아 안심 귀가 등록")
    @PostMapping("/add")
    public ResponseEntity<> addSafeReturn()

}
