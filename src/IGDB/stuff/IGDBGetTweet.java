package IGDB.stuff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.sun.jersey.api.view.Viewable;

import IGDB.lib.CassandraHosts;
import IGDB.models.IGDBModel;
import IGDB.stores.IGDBStore;

@Path("gettweet")
public class IGDBGetTweet {

	private static final String api_version = "00.01.00";
	Cluster cluster;

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Java Web Services</p>";
	}

	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>This is the version</p>" + api_version;
	}

	@SuppressWarnings("null")
	@Path("/database")
	@GET
	public String getTweets() {

		LinkedList<IGDBStore> tweetList = new LinkedList<IGDBStore>();
		IGDBStore ts = new IGDBStore();

		String a = null;
		String b = null;

		cluster = CassandraHosts.getCluster();
		Session session = cluster.connect("keyspace2");

		PreparedStatement statement = session.prepare("SELECT user from blah");
		BoundStatement boundStatement = new BoundStatement(statement);
		ResultSet rs = session.execute(boundStatement);

		if (rs.isExhausted()) {
			System.out.println("No Blahs returned");
		} else {
			for (Row row : rs) {
				// a = row.getString("user");
				ts.setUser(row.getString("user"));
				tweetList.add(ts);
			}
		}
		session.close();

		b = ts.getUser();
		return b;
	}

	@Path("foo")
	@GET
	public Viewable get() {
		return new Viewable("/index");
	}
	
	
	 @Path("donkey")
	 @GET
	 @Produces("text/html")
	 public Response index() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", "usul");
        List<String> l = new ArrayList<String>();
        l.add("light saber");
        l.add("fremen clothes");
        map.put("items", l);
        return Response.ok(new Viewable("/cart", map)).build();
    }
	 
	@Path("/{username}")
	@GET
	public Response returnSpecificBrandItem(@PathParam("username") String userName){
		
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> l = new ArrayList<String>();

		cluster = CassandraHosts.getCluster();
		Session session = cluster.connect("IGDataBase");

		PreparedStatement statement = session.prepare("SELECT * from userInfoTable Where username = ?");
		BoundStatement boundStatement = new BoundStatement(statement);
		ResultSet rs = session.execute(boundStatement.bind(userName));

		if (rs.isExhausted()) {
			System.out.println("No users returned");
		} else {
			for (Row row : rs) {
		        l.add(row.getString("username"));
		        l.add(row.getString("emailaddress"));
		        l.add(row.getString("firstname"));
		        l.add(row.getString("lastname"));
			}
		}
		session.close();
        map.put("user", userName);
        map.put("items", l);

        return Response.ok(new Viewable("/userProfile", map)).build();
	}

}
