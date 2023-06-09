package com.smhrd.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smhrd.model.WebMember;
import com.smhrd.model.WebMemberDAO;


public class LoginController implements Command {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		String url ="";
		try {
			//인코딩 방식 지정
			request.setCharacterEncoding("UTF-8");
			
			String email =request.getParameter("email");
			String pw =request.getParameter("pw");
			System.out.println(email);
			
			// 가져온 두개의 값 하나로 묶어주기
			WebMember member = new WebMember(email,pw);
			WebMemberDAO dao = new WebMemberDAO();
			WebMember loginMember = dao.login(member);
			
//		System.out.print("닉네임 : " + member2.getEmail());
			
			//없는 정보 입력했을 때 => null
			if(loginMember!=null) {//로그인 성공
				//세션에 member2저장
				//index.jsp 로 이동
				HttpSession session=request.getSession();
				session.setAttribute("loginMember",loginMember);
				url ="index.jsp";
				System.out.println("로그인성공");
				
				
				//indec.jsp -> 닉네임님 환영합니다 
			}else {//로그인 실패
				//login.html 로 이동
				url ="index.jsp";
				System.out.println("로그인실패");
				
			}
			
		}catch(UnsupportedEncodingException e){
			System.out.println("인코딩 방식 잘못 지정함 ");
		}catch(IOException e){
			System.out.println("리다이렉팅 경로 잘못 지정함 ");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return url;
	}

}
