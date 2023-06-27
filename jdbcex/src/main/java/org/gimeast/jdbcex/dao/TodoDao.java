package org.gimeast.jdbcex.dao;

import lombok.Cleanup;
import org.gimeast.jdbcex.domain.TodoVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDao {

    public String getTime() {

        String now = null;

        try(
            Connection connection = ConnectionUtil.INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement("select now()");
            ResultSet rs = ps.executeQuery();
        ) {
            rs.next();
            now = rs.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return now;
    }

    public String getTime2() throws Exception {

        String now = null;

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ps = connection.prepareStatement("select now()");
        @Cleanup ResultSet rs = ps.executeQuery();

        rs.next();
        now = rs.getString(1);

        return now;
    }

    public void insert(TodoVo todoVo) throws Exception {
        String sql = "insert into tbl_todo (title, dueDate, finished) values (?, ?, ?)";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, todoVo.getTitle());
        ps.setDate(2, Date.valueOf(todoVo.getDueDate()));
        ps.setBoolean(3, todoVo.isFinished());

        ps.executeUpdate();
    }

    public List<TodoVo> selectAll() throws Exception {
        String sql = "select * from tbl_todo";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ps = connection.prepareStatement(sql);
        @Cleanup ResultSet rs = ps.executeQuery();

        List<TodoVo> list = new ArrayList<>();

        while (rs.next()) {
            TodoVo todoVo = TodoVo.builder()
                    .tno(rs.getLong("tno"))
                    .title(rs.getString("title"))
                    .dueDate(rs.getDate("dueDate").toLocalDate())
                    .finished(rs.getBoolean("finished"))
                    .build();

            list.add(todoVo);
        }

        return list;
    }

    public TodoVo selectOne(Long tno) throws Exception {
        String sql = "select * from tbl_todo where tno = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ps = connection.prepareStatement(sql);
        ps.setLong(1, tno);

        @Cleanup ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            TodoVo todoVo = TodoVo.builder()
                    .tno(rs.getLong("tno"))
                    .title(rs.getString("title"))
                    .dueDate(rs.getDate("dueDate").toLocalDate())
                    .finished(rs.getBoolean("finished"))
                    .build();
            return todoVo;
        }

        return null;
    }

    public void deleteOne(Long tno) throws Exception {
        String sql = "delete from tbl_todo where tno = ?";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, tno);
        ps.executeUpdate();
    }

    public void updateOne(TodoVo vo) throws Exception {
        String sql = "update tbl_todo set title = ?, dueDate = ?, finished = ? where tno = ?";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, vo.getTitle());
        ps.setDate(2, Date.valueOf(vo.getDueDate()));
        ps.setBoolean(3, vo.isFinished());
        ps.setLong(4, vo.getTno());

        ps.executeUpdate();
    }


}
