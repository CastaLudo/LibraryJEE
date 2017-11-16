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
import fr.afpa.jee.libraryJEE.model.Book;
import fr.afpa.jee.libraryJEE.model.Subscriber;
import fr.afpa.jee.libraryJEE.service.IServiceLibrary;
import fr.afpa.jee.libraryJEE.service.ServiceLibrary;

public class SubscriberServlet extends HttpServlet{

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
				if (action.equals("/subscriber")) {
					gotoSubscribersPage(request, response);
				}
				if (action.equals("/searchSubscriber")) {
					subscribersWanted(request, response);
				}
				if (action.equals("/subscriberDetails")) { 
				innerSubscriberDetails(request, response);
				}
				if (action.equals("/borrowsInSubscriber")) {
					getBorrowedBooks(request, response);
				}
	}
	
	private void getBorrowedBooks(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, IOException {
		//recovery of the id
				int id = Integer.valueOf(request.getParameter("id"));
				ArrayList<Book> booksList = serviceLib.getBorrowedBooksBySubscriber(id);
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
				String arrayJson = objectMapper.writeValueAsString(booksList);
				System.out.println(arrayJson);
				//sending response
						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");
						response.getWriter().write(arrayJson);
			}

	private void innerSubscriberDetails(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		Subscriber selectedSubscriber = serviceLib.getSubscriber(id);
		System.out.println(selectedSubscriber.toString());
		ObjectMapper objectMapper = new ObjectMapper();
		// Set pretty printing of json
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		// 1. Convert List of Person objects to JSON
		String arrayToJson = objectMapper.writeValueAsString(selectedSubscriber);
		// System.out.println("1. Convert List of Book objects to JSON :");
		System.out.println(arrayToJson);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(arrayToJson);
	}

	private void subscribersWanted(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Subscriber> subscribers = new ArrayList<Subscriber>();
		subscribers = serviceLib.searchSubscriber(request.getParameter("searchSubscriber")); //on récupère la liste des abonnés avec le mot clé
		request.setAttribute("subscribers", subscribers); //on insère le résultat pour l'utiliser dans la JSP
		try {
			getServletContext().getRequestDispatcher("/WEB-INF/views/subscriber/Subscriber.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void gotoSubscribersPage(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Subscriber> subscribers = new ArrayList<Subscriber>();
		subscribers = serviceLib.allSubscribers(); //on récupère la liste des abonnés existants
		request.setAttribute("subscribers", subscribers); //on insère le résultat pour l'utiliser dans la JSP
		try {
			getServletContext().getRequestDispatcher("/WEB-INF/views/subscriber/Subscriber.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
