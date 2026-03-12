package com.barkhas.soap.service;

import com.barkhas.soap.util.DBConnection;
import jakarta.jws.WebService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

@WebService(endpointInterface = "com.barkhas.soap.service.AuthService")
public class AuthServiceImpl implements AuthService {
	
	@Override
	public int getUserIdByToken(String token) {
	    String sql = "SELECT id FROM users WHERE token = ?";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, token);
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            return rs.getInt("id");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return -1;
	}

    @Override
    public String registerUser(String username, String password) {
        if (username == null || password == null) {
            return "Нэвтрэх нэр эсвэл нууц үгээ оруулна уу!";
        }

        String sql = "INSERT INTO users(username, password, token) VALUES(?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, null);
            pstmt.executeUpdate();

            return "Хэрэглэгч амжилттай бүртгэгдлээ.";

        } catch (Exception e) {
            e.printStackTrace();
            return "Бүртгэлт амжилтгүй: " + e.getMessage();
        }
    }

    @Override
    public String loginUser(String username, String password) {
        if (username == null || password == null) {
            return "Нэвтрэх нэр эсвэл нууц үг оруулна уу.";
        }

        String selectSql = "SELECT * FROM users WHERE username = ? AND password = ?";
        String updateSql = "UPDATE users SET token = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {

            selectStmt.setString(1, username);
            selectStmt.setString(2, password);

            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("id");
                String token = UUID.randomUUID().toString();

                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setString(1, token);
                    updateStmt.setInt(2, userId);
                    updateStmt.executeUpdate();
                }

                return token;
            } else {
                return "Нэвтрэх нэр эсвэл нууц үг буруу байна.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Нэвтрэлт амжилтгүй: " + e.getMessage();
        }
    }

    @Override
    public boolean validateToken(String token) {
        String sql = "SELECT id FROM users WHERE token = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, token);
            ResultSet rs = pstmt.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}