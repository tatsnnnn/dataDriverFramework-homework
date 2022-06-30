import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import org.testng.asserts.SoftAssert;

public class DBgettingStarted {
    public DBgettingStarted() {
    }
    public static <SoftAssert> int insertCandidate(String ID, String Name, String surName, String phone) {
        String insert = "INSERT INTO students(id,firstName,lastName,phone) VALUES(?,?,?,?)";
        String url = "jdbc:sqlserver://localhost:1433";
        String user = "testAutomation";
        String password = "Testautomation123";
        String sqlUpdate = "UPDATE students SET firstName = ? WHERE id = ?";
        int candidateId = 0;

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM students";
            stmt.executeQuery(sql);
            candidateId = 0;
            PreparedStatement pstmt = conn.prepareStatement(insert, 1);
            pstmt.setString(1, ID);
            pstmt.setString(2, Name);
            pstmt.setString(3, surName);
            pstmt.setString(4, phone);
            int rowAffected = pstmt.executeUpdate();
            if (rowAffected == 1) {
                System.out.println("Row has added SUCCSSEFULLY");
                ++candidateId;
            } else {
                System.out.println("row didnt add");
            }

            conn.commit();
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals("1004", ID);
            softAssert.assertEquals("tato", Name);
            softAssert.assertEquals("jixvashvili", surName);
            softAssert.assertEquals("579134049", phone);
            softAssert.assertAll();
            PreparedStatement pstmt1 = conn.prepareStatement(sqlUpdate);
            String firstName = "Name";
            pstmt1.setString(1, firstName);
            pstmt1.setString(2, "1004");
            int rowAffected1 = pstmt1.executeUpdate();
            softAssert.assertEquals("Name", firstName);
            softAssert.assertAll();
            conn.commit();
        } catch (SQLException var20) {
            System.out.println(var20.getMessage());
        }

        return candidateId;
    }

    public static void main(String[] args) {
        int id = insertCandidate("1004", "tato", "jixvashvili", "579134049");
        System.out.println("A new candidate with has been inserted");
    }
}
