package fr.afpa.jee.libraryJEE.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import fr.afpa.jee.libraryJEE.dao.DaoLibraryMySql;
import fr.afpa.jee.libraryJEE.model.Copy;
import fr.afpa.jee.libraryJEE.model.Subscriber;
import fr.afpa.jee.libraryJEE.service.IServiceLibrary;
import fr.afpa.jee.libraryJEE.service.ServiceLibrary;

public class BorrowServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IServiceLibrary serviceLib;

	public void init() throws ServletException {
		DaoLibraryMySql daoCatalog = new DaoLibraryMySql();
		serviceLib = new ServiceLibrary(daoCatalog);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//On récupère l'action à effectuer
				String action = request.getServletPath();
				if (action.equals("/borrow")) {
					gotoBorrowsPage(request, response);
				}
				if (action.equals("/copyBorrowed")) {
					borrowedCopyDetails(request, response);
				}
				if (action.equals("/subscriberBorrowing")) {
				borrowerDetails(request, response);
				}

	}

	private void gotoBorrowsPage(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Copy> borrows = serviceLib.searchBorrow();
		request.setAttribute("borrows", borrows);
		try {
			getServletContext().getRequestDispatcher("/WEB-INF/views/borrow/Borrow.jsp").forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void borrowedCopyDetails(HttpServletRequest req, HttpServletResponse rep) throws JsonProcessingException, IOException {
		int id = Integer.valueOf(req.getParameter("id"));
		Copy borrowedCopy = serviceLib.getCopy(id);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		String arrayJson = objectMapper.writeValueAsString(borrowedCopy);
		rep.setContentType("application/json");
		rep.setCharacterEncoding("UTF-8");
		rep.getWriter().write(arrayJson);
	}
	
	private void borrowerDetails(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		Subscriber borrower = serviceLib.getBorrower(id);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		String arrayToJson = objectMapper.writeValueAsString(borrower);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(arrayToJson);
	}

}
