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
import fr.afpa.jee.libraryJEE.model.Author;
import fr.afpa.jee.libraryJEE.model.Book;
import fr.afpa.jee.libraryJEE.service.IServiceLibrary;
import fr.afpa.jee.libraryJEE.service.ServiceLibrary;

public class AuthorServlet extends HttpServlet{

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
				if (action.equals("/author")) {
					gotoAuthorsPage(request, response);
				}
				
				if(action.equals("/searchAuthorByName")) {
					searchAuthorByName(request, response);
				}
				
				if (action.equals("/getAuthorDetails")) {
					getAuthorDetails(request, response);
				}
				
				if (action.equals("/getAuthorSBooks")) {
					getAuthorSBooks(request, response);
				}

	}

	private void getAuthorSBooks(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, IOException{
		//recovery of the id
		int id = Integer.valueOf(request.getParameter("id"));
		ArrayList<Book> booksList = serviceLib.getBookListByAuthor(id);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		String arrayJson = objectMapper.writeValueAsString(booksList);
		//sending response
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(arrayJson);
	}

	private void getAuthorDetails(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, IOException {
		//recovery of the id
		int id = Integer.valueOf(request.getParameter("id"));
		//asking author @serviceLib
		Author selectedAuthor = serviceLib.getAuthor(id);
		//create the object mapper
		ObjectMapper objectMapper = new ObjectMapper();
		//set printing to Json
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		//Convert List of Details objects to JSON
		String arrayToJson = objectMapper.writeValueAsString(selectedAuthor);
		//sending response
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(arrayToJson);
	}

	private void searchAuthorByName(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Author> authors = new ArrayList<Author>();
		authors = serviceLib.searchAuthor(request.getParameter("searchAuthor"));
		request.setAttribute("authors", authors);
		try {
			getServletContext().getRequestDispatcher("/WEB-INF/views/author/Author.jsp").forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private void gotoAuthorsPage(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Author> authors = new ArrayList<Author>();
		authors = serviceLib.allAuthors();
		request.setAttribute("authors", authors);
		try {
			getServletContext().getRequestDispatcher("/WEB-INF/views/author/Author.jsp").forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
