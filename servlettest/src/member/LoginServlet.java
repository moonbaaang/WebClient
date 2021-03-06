package member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
아이디 입력 : <input type=text name=id><br>
암호 입력 : <input type=password name=pw><br>
*/
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		login(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 브라우저에서 한글 입력 시 인코딩 (받아올 때 인코딩)
		request.setCharacterEncoding("utf-8");
		login(request, response);
	}
		// 폼 입력 파라미터 이름 출력
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()) {
			String name = names.nextElement();
			System.out.println(name);
		}
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		//String location = request.getParameter("location"); // 첫번째 꺼만 선택
		String[] locations = request.getParameterValues("location"); //모두 선택(배열)
		// 로그인 처리 - id=user, pw = 1234일 때 정상 로그인
		String result ="";
		if(id.equals("user")&&pw.equals("1234")) {
			result = "<h3 style=color:green> 정상로그인되었습니다.</h3>";
		} 
		else {
			result = "<h3 style=color:green> 비정상로그인되었습니다.</h3>";
			result += "<h3><a href='loginform.html'>로그인창으로 이동</a><h3>";
		}
		//응답 결과 브라우저 출력 text/html -mime type image/gif
		response.setContentType("text/html; charset=utf-8"); //PrintWriter 이전에 설정해야한다.

		PrintWriter out = response.getWriter();
		out.println(result);
		
		out.println("<h3>선택한 장소는 다음과 같습니다.</h3>");
		for(String loc : locations){
			out.println(loc+"<br>");
		}	
	} 
}
