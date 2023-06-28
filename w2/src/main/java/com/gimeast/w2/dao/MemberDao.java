package com.gimeast.w2.dao;

import com.gimeast.w2.domain.MemberVo;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDao {
    public MemberVo getWithPassword(String mid, String mpw) throws Exception {
        String query = "select mid, mpw, mname from tbl_member where mid = ? and mpw = ?";

        MemberVo vo = null;

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, mid);
        pstmt.setString(2, mpw);

        @Cleanup ResultSet rs = pstmt.executeQuery();

        if(rs.next()) {
            vo = MemberVo.builder()
                    .mid(rs.getString("mid"))
                    .mpw(rs.getString("mpw"))
                    .mname(rs.getString("mname"))
                    .build();
        }

        return vo;
    }

    public void updateUuid(String mid, String uuid) throws Exception {
        String sql = "update tbl_member set uuid = ? where mid = ?";
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, uuid);
        pstmt.setString(2, mid);
        pstmt.executeUpdate();
    }

    public MemberVo selectUuid(String uuid) throws Exception {
        String query = "select mid, mpw, mname, uuid from tbl_member where uuid = ?";
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, uuid);

        @Cleanup ResultSet rs = pstmt.executeQuery();
        rs.next();

        MemberVo vo = MemberVo.builder()
                .mid(rs.getString("mid"))
                .mpw(rs.getString("mpw"))
                .mname(rs.getString("mname"))
                .uuid(rs.getString("uuid"))
                .build();

        return vo;
    }


}
