package dao;

import db.DBConnection;
import model.Player;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    public void insertPlayer(Player p) throws Exception {
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "INSERT INTO Player (NationalId, PlayerName, HighScore, Level) VALUES (" +
                p.getNationalId() + ", '" + p.getPlayerName() + "', " + p.getHighScore() + ", " + p.getLevel() + ")";
        stmt.executeUpdate(sql);
        System.out.println("Thêm Player thành công!");
        conn.close();
    }

    public void deletePlayer(int playerId) throws Exception {
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "DELETE FROM Player WHERE PlayerId = " + playerId;
        stmt.executeUpdate(sql);
        System.out.println("Xóa Player thành công!");
        conn.close();
    }

    public List<Player> displayAll() throws Exception {
        List<Player> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM Player";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Player p = new Player(
                    rs.getInt("PlayerId"),
                    rs.getInt("NationalId"),
                    rs.getString("PlayerName"),
                    rs.getInt("HighScore"),
                    rs.getInt("Level")
            );
            list.add(p);
        }
        conn.close();
        return list;
    }

    public List<Player> displayByName(String name) throws Exception {
        List<Player> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM Player WHERE PlayerName LIKE '%" + name + "%'";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Player p = new Player(
                    rs.getInt("PlayerId"),
                    rs.getInt("NationalId"),
                    rs.getString("PlayerName"),
                    rs.getInt("HighScore"),
                    rs.getInt("Level")
            );
            list.add(p);
        }
        conn.close();
        return list;
    }

    public List<Player> displayTop10() throws Exception {
        List<Player> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM Player ORDER BY HighScore DESC LIMIT 10";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Player p = new Player(
                    rs.getInt("PlayerId"),
                    rs.getInt("NationalId"),
                    rs.getString("PlayerName"),
                    rs.getInt("HighScore"),
                    rs.getInt("Level")
            );
            list.add(p);
        }
        conn.close();
        return list;
    }
}
