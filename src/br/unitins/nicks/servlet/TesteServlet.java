package br.unitins.nicks.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/exemplo")
public class TesteServlet extends HttpServlet{
	
	private static final long serialVersionUID = -3369063430890792256L;

	@Override
	protected void doGet(HttpServletRequest request, 
						HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter writer =  response.getWriter();
		writer.println("<html>");
		writer.println("  <head>");
		writer.println("  </head>");
		writer.println("  <body>");
		writer.println("  	<h1> Teste Servlet </h1>");
		writer.println("  </body>");
		writer.println("</html>");
		
	}

}