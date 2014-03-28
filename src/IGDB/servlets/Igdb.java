package IGDB.servlets;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Cluster;

import IGDB.lib.CassandraHosts;
import IGDB.models.IGDBModel;
import IGDB.stores.IGDBStore;


/**
 * Servlet implementation class Tweet
 */
@Path("tweet")
public class Igdb extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Cluster cluster;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Igdb() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		cluster = CassandraHosts.getCluster();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String args[] = Convertors.SplitRequestPath(request);
		IGDBModel gm = new IGDBModel();
		gm.setCluster(cluster);
		LinkedList<IGDBStore> tweetList = gm.getTweets();
		request.setAttribute("Tweets", tweetList); // Set a bean with the list
													// in it
		RequestDispatcher rd = request
				.getRequestDispatcher("/RenderIGDB.jsp");

		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/*protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}*/

}
