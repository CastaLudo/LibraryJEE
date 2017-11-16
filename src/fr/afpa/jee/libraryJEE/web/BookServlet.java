package fr.afpa.jee.libraryJEE.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import fr.afpa.jee.libraryJEE.dao.DaoLibraryMySql;
import fr.afpa.jee.libraryJEE.model.Book;
import fr.afpa.jee.libraryJEE.model.Copy;
import fr.afpa.jee.libraryJEE.service.IServiceLibrary;
import fr.afpa.jee.libraryJEE.service.ServiceLibrary;

public class BookServlet extends HttpServlet{

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
				if (action.equals("/book")) {
					gotoBooksPage(request, response);
				}
				if (action.equals("/searchBookByName")) {
					booksWanted(request, response);
				}
				if (action.equals("/bookDetails")) {
					innerBookDetails(request, response);
				}
				if (action.equals("/nbCopies")) {
					returnCopies(request, response);
				}

	}

	private void returnCopies(HttpServletRequest request, HttpServletResponse response) throws IOException {
		long isbn = Long.valueOf(request.getParameter("id"));
		System.out.println(isbn);
		ArrayList<Copy> copies = new ArrayList<Copy>();
		System.out.println("ask for serviceLib");
		copies = serviceLib.getCopies(isbn);
		System.out.println("return " + copies);
		ObjectMapper objectMapper = new ObjectMapper();
		// Set pretty printing of json
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		// 1. Convert List of Person objects to JSON
		String arrayJson = objectMapper.writeValueAsString(copies);
		// System.out.println("1. Convert List of Book objects to JSON :");
		System.out.println(arrayJson);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(arrayJson);
	}

	private void innerBookDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
		long id = Long.valueOf(request.getParameter("id"));
		System.out.println(id);
		Book selectedBook = serviceLib.getBook(id);
		System.out.println(selectedBook.toString());
		ObjectMapper objectMapper = new ObjectMapper();
		// Set pretty printing of json
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		// 1. Convert List of Details objects to JSON
		String arrayToJson = objectMapper.writeValueAsString(selectedBook);
		// System.out.println("1. Convert List of Book objects to JSON :");
		System.out.println(arrayToJson);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(arrayToJson);
		System.out.println("book's details sent by Servlet");
	}

	private void booksWanted(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Book> books = new ArrayList<Book>();
		books = serviceLib.searchBook(request.getParameter("searchBook")); //on récupère la liste des livres avec le mot clé
		request.setAttribute("books", books); //on insère le résultat pour l'utiliser dans la JSP
		try {
			getServletContext().getRequestDispatcher("/WEB-INF/views/book/Book.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void gotoBooksPage(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Book> books = new ArrayList<Book>();
		books = serviceLib.allBooks(); //on récupère la liste des livres existants
		request.setAttribute("books", books); //on insère le résultat pour l'utiliser dans la JSP
		try {
			getServletContext().getRequestDispatcher("/WEB-INF/views/book/Book.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
