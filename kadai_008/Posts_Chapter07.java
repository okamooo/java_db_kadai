package kadai_008;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Posts_Chapter07 {

	public static void main(String[] args) {
		
		Connection con = null;
        PreparedStatement statement = null;
        Statement statement2 =null;
        
        
        try {
            // データベースに接続
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost/challenge_java",
                "root",
                "Okamo@0180"
            );
            
            System.out.println("データベース接続成功:"+con);
            
            // SQLクエリを準備
            String sql = "INSERT INTO posts ( user_id, posted_at, post_content, likes) VALUES "
            		+ "(\"1001\",\"2023-02-09\",\"無理は禁物ですよ！\", \"17\"),"
            		+ "(\"1002\",\"2023-02-08\",\"お疲れ様です！\", \"12\"),"
            		+ "(\"1002\",\"2023-02-10\",\"明日から連休ですね！\", \"20\"),"
            		+ "(\"1003\",\"2023-02-08\",\"昨日の夜は徹夜でした・・\", \"13\"),"
            		+ "(\"1003\",\"2023-02-09\",\"今日も頑張ります！\", \"18\");";
            statement = con.prepareStatement(sql);
            
            int rowCnt = 0;
            
                rowCnt = statement.executeUpdate();

            
            
            System.out.println("レコード追加を実行します");
            System.out.println( rowCnt + "件のレコードが追加されました"); 
            
            
            String sql2 = "SELECT posted_at,post_content,likes FROM posts WHERE user_id=1002";
            statement2 = con.createStatement();
            
            ResultSet result = statement2.executeQuery(sql2);
            
            System.out.println("ユーザーIDが1002のレコードを検索しました");
            
            while (result.next()) {
                Date posteAt = result.getDate("posted_at");
                String content = result.getString("post_content"); 
                int likes = result.getInt("likes");
             
                System.out.println(result.getRow() + "件目:投稿日時=" + posteAt + "/投稿内容=" + content + "/いいね数=" + likes);
            }
            
        } catch(SQLException e) {
            System.out.println("エラー発生：" + e.getMessage());
        } finally {
            // 使用したオブジェクトを解放
            if( statement != null ) {
                try { statement.close(); } catch(SQLException ignore) {}
            }if( statement2 != null ) {
                try { statement2.close(); } catch(SQLException ignore) {}
            }
            if( con != null ) {
                try { con.close(); } catch(SQLException ignore) {}
            }
            
        }
	}
}

