package IGDB.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import IGDB.lib.CassandraHosts;
import IGDB.stores.GameStore;
import IGDB.stores.IGDBStore;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.sun.jersey.api.view.Viewable;

/**
 * Servlet implementation class ReviewServlet
 */
// @WebServlet(description = "Where the user posts a review")
@Path("Review")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Cluster cluster;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReviewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {

		return "<p>Java Web Services</p>";
	}

	@GET
	@Path("/{gameName}")
	public Response reviewSpecificGame(@PathParam("gameName") String gameName) {

	    String g=gameName;
	    System.out.print(g);
	    g=g.replaceAll("\\+", " ");
	    g=g.replaceAll("%20", " ");

	    System.out.print("---------After Replacement-----\n");
	    System.out.print(g);
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> l = new ArrayList<String>();

		LinkedList<GameStore> gamesList = new LinkedList<GameStore>();
		GameStore gs = new GameStore();

		cluster = CassandraHosts.getCluster();
		Session session = cluster.connect("IGDataBase");

		PreparedStatement statement = session
				.prepare("SELECT * from gameInfoTable where gamename = ?");
		BoundStatement boundStatement = new BoundStatement(statement);
		ResultSet rs = session.execute(boundStatement.bind(g));

		if (rs.isExhausted()) {
			System.out.println("No games returned");
		} else {
			for (Row row : rs) {
				// a = row.getString("user");
				l.add(row.getString("gamename"));
				gs.setGameName(row.getString("gamename"));
				gs.setGameUUID(row.getUUID("gameid"));
				gamesList.add(gs);
			}
		}
		session.close();

		map.put("items", l);

		return Response.ok(new Viewable("/reviewAGame", map)).build();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	@Path("aa")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Here we are");
		  PrintWriter out = response.getWriter();
		  out.print("BLAH");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
		@POST
		@Path("dojo")
		@Consumes(value="application/x-www-form-urlencoded")
		public Response doStuff(@FormParam("comments")String id)
		{
			String a  = id;
			return Response.status(200).entity(a).build();
		/*	System.out.println("Dogs smell nice");
			  PrintWriter out = new  PrintWriter(System.out);
			  out.println("BLAH");*/
		}
	
	
	  @POST
	  @Path("/Hey")
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		  System.out.println("Here we are");
		  PrintWriter out = response.getWriter();
		  out.println("BLAH");
	  }
	 
}
