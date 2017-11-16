package fr.afpa.jee.libraryJEE.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.afpa.jee.libraryJEE.dao.DaoLibraryMySql;
import fr.afpa.jee.libraryJEE.model.Copy;
import fr.afpa.jee.libraryJEE.service.IServiceLibrary;
import fr.afpa.jee.libraryJEE.service.ServiceLibrary;

public class BorrowServlet extends HttpServlet{

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

}
