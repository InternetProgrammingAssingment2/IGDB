package IGDB.models;

/*
* Expects a cassandra columnfamily defined as
* use keyspace2;
CREATE TABLE Tweets (
user varchar,
interaction_time timeuuid,
tweet varchar,
PRIMARY KEY (user,interaction_time)
) WITH CLUSTERING ORDER BY (interaction_time DESC);
* To manually generate a UUID use:
* http://www.famkruithof.net/uuid/uuidgen
*/

import java.util.LinkedList;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import IGDB.lib.CassandraHosts;
import IGDB.stores.IGDBStore;

public class IGDBModel {

	Cluster cluster;

	public IGDBModel() {

	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	public LinkedList<IGDBStore> getTweets() {
		LinkedList<IGDBStore> tweetList = new LinkedList<IGDBStore>();
		Session session = cluster.connect("keyspace2");

		PreparedStatement statement = session.prepare("SELECT * from tweets");
		BoundStatement boundStatement = new BoundStatement(statement);
		ResultSet rs = session.execute(boundStatement);
		if (rs.isExhausted()) {
			System.out.println("No Tweets returned");
		} else {
			for (Row row : rs) {
				IGDBStore ts = new IGDBStore();
				ts.setTweet(row.getString("tweet"));
				ts.setUser(row.getString("user"));
				tweetList.add(ts);
			}
		}
		session.close();
		return tweetList;
	}
	
	public LinkedList<IGDBStore> getUserNames(String user){
		LinkedList<IGDBStore> userList = new LinkedList<IGDBStore>();
		//cluster = CassandraHosts.getCluster();

		Session session = cluster.connect("IGDataBase");

		String a = user;

		PreparedStatement statement = session.prepare("SELECT * from userInfoTable");

		BoundStatement boundStatement = new BoundStatement(statement);
		ResultSet rs = session.execute(boundStatement);
				
		if (rs.isExhausted()) {
			System.out.println("No users returned");
		} else {
			for (Row row : rs) {
				IGDBStore ts = new IGDBStore();
				ts.setUser(row.getString("username"));
				userList.add(ts);
			}
		}
		session.close();
		return userList;
	}

	
}
