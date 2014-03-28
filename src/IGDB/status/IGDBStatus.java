package IGDB.status;

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

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import IGDB.lib.CassandraHosts;
import IGDB.models.IGDBModel;
import IGDB.stores.IGDBStore;


@Path("statusupdate")
public class IGDBStatus {
	
	private static final String api_version = "00.01.00";
	private static final long serialVersionUID = 1L;
	private Cluster cluster;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IGDBStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		cluster = CassandraHosts.getCluster();
	}
	
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle()
	{
		return "<p>Java Web Services</p>";
	}
	
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion()
	{
		return "<p>This is the version</p>" + api_version;
	}
	
	@Path("/bird")
	@GET
	public void doGet(HttpServletRequest request,
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
	
}