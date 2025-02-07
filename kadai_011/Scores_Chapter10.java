package kadai_011;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scores_Chapter10 {

	public static void main(String[] args) {
		
		 Connection con = null;
	     Statement statement = null;
	     Statement statement2 = null;

	        try {
	            // データベースに接続
	            con = DriverManager.getConnection(
	                "jdbc:mysql://localhost/challenge_java",
	                "root",
	                "Okamo@0180"
	            );

	            System.out.println("データベース接続成功"+con);
	            
	            statement = con.createStatement();
	            String sql = "UPDATE scores SET score_math = 95,score_English = 80 WHERE id = 5;";
	            
	            
	            int rowCnt = statement.executeUpdate(sql);
	            System.out.println( rowCnt + "件のレコードが更新されました");
	            
	            statement2 = con.createStatement();
	            String sql2 = "SELECT * FROM scores ORDER BY score_math DESC, score_English DESC"; 
	            
	            ResultSet result2 = statement.executeQuery(sql2);
	            
	            System.out.println("数学・英語の点数が高い順に並べ替えました");
	            while(result2.next()) {
	                int id = result2.getInt("id");
	                String name = result2.getString("name");
	                int score_math = result2.getInt("score_math");
	                int score_English = result2.getInt("score_English");
	             
	                System.out.println(result2.getRow() + "件目：生徒ID=" + id + "／氏名=" + name + "／数学=" + score_math + "／英語=" + score_English );
	            }
	        } catch(SQLException e) {
	            System.out.println("エラー発生：" + e.getMessage());
	        } finally {
	            if( statement != null ) {
	                try { statement.close(); } catch(SQLException ignore) {}
	            }if( statement2 != null ) {
	                try { statement.close(); } catch(SQLException ignore) {}
	            }
	            if( con != null ) {
	                try { con.close(); } catch(SQLException ignore) {}
	            }
	    }
	}
}
