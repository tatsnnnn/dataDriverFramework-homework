import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.sql.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class DBgettingStarted {

    @Test
    public static  void fillInformation() throws ClassNotFoundException {

        String url = "jdbc:sqlserver://localhost:1433";
        String user = "testAutomation";
        String password = "Testautomation123";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Configuration.startMaximized = true;
        String urlOfWebPage = "https://demoqa.com/automation-practice-form";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM students";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                open(urlOfWebPage);
                $(By.xpath("//*[@id='firstName']")).sendKeys(rs.getString("firstName"));
                $(By.xpath("//*[@id='lastName']")).sendKeys(rs.getString("lastName"));
                $(By.xpath("//*[@id='userNumber']")).sendKeys(rs.getString("phone"));
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
