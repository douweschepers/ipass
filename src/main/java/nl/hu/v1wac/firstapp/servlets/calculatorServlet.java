package nl.hu.v1wac.firstapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/calculatorServlet.do")
public class calculatorServlet extends HttpServlet{
		 protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			 throws ServletException, IOException {
			 String button1 = req.getParameter("optellen");
			 String button2 = req.getParameter("aftrekken");
			 double number1 = Double.parseDouble(req.getParameter("firstNumber"));
			 double number2 = Double.parseDouble(req.getParameter("secondNumber"));
			 
			 double uitkomst = 0;
			 String soort = "0";
			 
			 String act = req.getParameter("act");
			 if (act.equals("optellen")) {
			     uitkomst = number1+ number2;
			     soort = " + ";
			 } else if (act.equals("aftrekken")) {
			     uitkomst = number1 - number2;
			     soort = " - ";
			 } else if(act.equals("keer")){
			     uitkomst = number1* number2;
			     soort = " * ";
			 } else if(act.equals("delen")){
			     uitkomst = number1/ number2;
			     soort = " / ";
			 }
			 
			 PrintWriter out = resp.getWriter();
			 resp.setContentType("text/html");
			 out.println("<!DOCTYPE html>");
			 out.println("<html>");
			 out.println(" <title>Calculator</title>");
			 out.println(" <body>");
			 out.println(" <h2>Calculation try</h2>");
			 out.println(" <h2>Uw uitkomst van uw getallen is:</h2>");
			 out.println(number1 + soort+ number2 +" = "+uitkomst);
			 out.println(" </body>");
			 out.println("</html>");
		 }
	}
