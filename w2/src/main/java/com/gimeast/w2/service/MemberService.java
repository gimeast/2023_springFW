package com.gimeast.w2.service;

import com.gimeast.w2.dao.MemberDao;
import com.gimeast.w2.domain.MemberVo;
import com.gimeast.w2.dto.MemberDto;
import com.gimeast.w2.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

@Log4j2
public enum MemberService {
    INSTANCE;

    private MemberDao dao;
    private ModelMapper modelMapper;

    MemberService() {
        dao = new MemberDao();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public MemberDto login(String mid, String mpw) throws Exception {
        MemberVo vo = dao.getWithPassword(mid, mpw);
        MemberDto dto = modelMapper.map(vo, MemberDto.class);

        return dto;
    }

    public void updateUuid(String mid, String uuid) throws Exception {
        dao.updateUuid(mid, uuid);
    }

    public MemberDto getByUuid(String uuid) throws Exception {
        MemberVo memberVo = dao.selectUuid(uuid);
        MemberDto dto = modelMapper.map(memberVo, MemberDto.class);
        return dto;
    }

}
