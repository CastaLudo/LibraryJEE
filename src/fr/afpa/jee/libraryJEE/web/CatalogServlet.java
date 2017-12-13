package fr.afpa.jee.libraryJEE.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import fr.afpa.jee.libraryJEE.service.IServiceLibrary;
import fr.afpa.jee.libraryJEE.service.ServiceLibrary;
import fr.afpa.jee.libraryJEE.dao.DaoLibraryMySql;
import fr.afpa.jee.libraryJEE.model.Book;
import fr.afpa.jee.libraryJEE.model.Catalog;

/**
 * @author 34011-79-08
 *
 */
public class CatalogServlet extends HttpServlet {

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
		// On récupère l'action à effectuer
		String action = request.getServletPath();
		if (action.equals("/catalog")) {
			gotoCatalogsPage(request, response);
		}
		if (action.equals("/searchCatalogByName")) {
			catalogsWanted(request, response);
		}
		if (action.equals("/booksInCatalog")) {
			innerCatalogBooksDisplay(request, response);
		}
		if (action.equals("/addCatalog")) {
			addCatalog(request, response);
		}
		if (action.equals("/deleteCatalog")) {
			CatalogToDelete(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// On récupère l'action à effectuer
		String action = request.getServletPath();
		if (action.equals("/catalog")) {
			gotoCatalogsPage(request, response);
		}
		if (action.equals("/booksInCatalog")) {
			innerCatalogBooksDisplay(request, response);
		}
		if (action.equals("/add")) {
			addCatalog(request, response);
		}
		if (action.equals("/deleteCatalog")) {
			CatalogToDelete(request, response);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 */
	private void CatalogToDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		if (request.getParameter("catalogToDelete") != null) {
			int id = Integer.valueOf(request.getParameter("catalogToDelete"));
			if (serviceLib.getBooksFromCatalog(id).size() == 0) {
				request.getSession().removeAttribute("errorMessage");
				System.out.println("deleting");
				serviceLib.deleteCatalog(id);
			} else {
				System.out.println("error deleting");
				request.getSession().setAttribute("errorMessage",
						"Erreur : Impossible de supprimer ce catalogue /n " + "il contient encore des livres !");
			}
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addCatalog(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("catalogToAdd") != null) {
			String catalogName = request.getParameter("catalogToAdd");
			if (serviceLib.searchCatalog(catalogName).size() == 0)
				serviceLib.addCatalog(catalogName);
			response.sendRedirect("catalog");
		}

	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void innerCatalogBooksDisplay(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("errorMessage", "");
		int id = Integer.valueOf(request.getParameter("id"));
		ArrayList<Book> books = new ArrayList<Book>();
		books = serviceLib.getBooksFromCatalog(id);

		ObjectMapper objectMapper = new ObjectMapper();
		// Set pretty printing of json
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		// 1. Convert List of Person objects to JSON
		String arrayToJson = objectMapper.writeValueAsString(books);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(arrayToJson);
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void gotoCatalogsPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Catalog> catalogs = new ArrayList<Catalog>();
		catalogs = serviceLib.allCatalogs(); // on récupère la liste des catalogues existants
		request.setAttribute("catalogs", catalogs); // on insère le résultat pour l'utiliser dans la JSP
		getServletContext().getRequestDispatcher("/WEB-INF/views/catalog/Catalog.jsp").forward(request, response);
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void catalogsWanted(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Catalog> catalogs = new ArrayList<Catalog>();
		{
			catalogs = serviceLib.searchCatalog(request.getParameter("searchCatalog"));
			request.setAttribute("catalogs", catalogs);
			try {
				getServletContext().getRequestDispatcher("/WEB-INF/views/catalog/Catalog.jsp").forward(request,
						response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}
