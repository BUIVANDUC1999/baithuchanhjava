package dao;

import db.DBConnection;
import model.National;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NationalDAO {
    public void insertNational(National national) throws Exception {
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "INSERT INTO National (NationalName) VALUES ('" + national.getNationalName() + "')";
        stmt.executeUpdate(sql);
        System.out.println("Thêm National thành công!");
        conn.close();
    }

    public void deleteNational(int nationalId) throws Exception {
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "DELETE FROM National WHERE NationalId = " + nationalId;
        stmt.executeUpdate(sql);
        System.out.println("Xóa National thành công!");
        conn.close();
    }

    public List<National> getAllNational() throws Exception {
        List<National> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM National";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            National n = new National(
                    rs.getInt("NationalId"),
                    rs.getString("NationalName")
            );
            list.add(n);
        }
        conn.close();
        return list;
    }
}
