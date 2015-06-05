package edu.ahjzu.app.notice.servlet.controler;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ahjzu.app.notice.dao.impl.NewsDaoImpl;
import edu.ahjzu.app.notice.pojo.News;


public class jstlOper {
	public void handle(HttpServletRequest request, HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("id:"+id);
		NewsDaoImpl newsDB=new NewsDaoImpl();
		ArrayList<News> result=newsDB.getNews(id);
		//String re=newsXML.createXML(result);
		request.setAttribute("newses", result);
		try {
			request.getRequestDispatcher("newses.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
