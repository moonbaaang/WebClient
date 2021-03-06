package servlettest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/DataSourceServlet")
public class DataSourceServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
/*		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		for(int i=1;i<=100;i++) {
			Connection con = DriverManager.getConnection
			("jdbc:oracle:thin:@127.0.0.1:1521:xe", "jdbc", "jdbc");
			System.out.println(i+" 번째 연결 생성 -"+con);
			con.close(); //db연결해제, 메모리 con 삭제(즉각 삭제하지 않음, 나중에 모아서 삭제 작업 일괄적)
		} 
		일반적으로 db를 불러오면 반복이 되지 않는 문제 발생
	*/	
			//1. context = 현재의 dynamic web project
			//servlettest 관련설정 가능 
			Context initContext = new InitialContext();//현재작업다이나믹웹 프로젝트  찾아와
			//2. server.xml 설정 찾아와
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			//3. name=jdbc/myoracle 설정 객체 읽어와라
			//ds = connectionpool 객체 
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle"); //name="jdbc/myoracle >> server.xml과 동일해야한다.	
			
			for(int i = 1; i <= 100; i++){
				Connection con = ds.getConnection(); // 기존과 다르게 데이터 소스에서 데이터를 가져감
				System.out.println(i+" 번째 연결 생성 = "+con);
				con.close();
			}
					
			}catch(NamingException e) {
				System.out.println("이름 문제");
			}catch(SQLException e) {
				e.printStackTrace();
			}
	}
}
