package com.gimeast.w2.domain;

import lombok.*;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberVo {

    private String mid;
    private String mpw;
    private String mname;
    private String uuid;
}
