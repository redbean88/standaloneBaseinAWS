package me.redbean88.standAlone.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor // final 필스 생성자 생성
public class HelloResponseDto {

    private final String name;
    private final int amount;

}
