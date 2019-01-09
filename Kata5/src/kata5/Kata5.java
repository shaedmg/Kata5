package kata5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Kata5 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection
                            ("jdbc:sqlite:C:\\Users\\usuario\\Downloads\\SQLiteDatabaseBrowserPortable\\Data\\KATA5.db");
        
        Statement st = con.createStatement();
        
        String query =  "SELECT * FROM PEOPLE";
        ResultSet rs = st.executeQuery(query);
        
        while(rs.next()){
            System.out.print(rs.getInt(1));
            System.out.print(" Nombre: " + rs.getString(2));
            System.out.print(" " + rs.getString(3));
            System.out.println(", Departamento de: " + rs.getString(4));
        }
        
        query = "CREATE	TABLE IF NOT EXISTS MAIL ('Id' INTEGER PRIMARY KEY AUTOINCREMENT,'Mail' TEXT NOT NULL)";
        
        st.execute(query);
        
        String fileString = "C:\\Users\\usuario\\Downloads\\SQLiteDatabaseBrowserPortable\\Other\\Source\\emailsfile.txt";
        BufferedReader reader = new BufferedReader(new FileReader(new File(fileString)));
        String mail;
        while((mail = reader.readLine()) != null){
            if(!mail.contains("@")) continue;
            query = "INSERT INTO MAIL (Mail) VALUES ('" + mail + "');";
            st.executeUpdate(query);
        }        
        
        rs.close();
        st.close();
        con.close();
    }
}
