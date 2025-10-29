package kelasd.util;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Koneksi {

    public static Connection Go() {
        try {
            MysqlDataSource MDS = new MysqlDataSource();
            MDS.setServerName("localhost");
            MDS.setDatabaseName("posd"); 
            MDS.setUser("root");
            MDS.setPassword("");
            MDS.setPort(3306);
            MDS.setServerTimezone("Asia/Jakarta");
            return MDS.getConnection();
        } catch (SQLException e) {
            System.err.println("Gagal Terhubung. \n"
                    + "Error: "+e.getMessage());
        }
        return null;
    }
    
}
