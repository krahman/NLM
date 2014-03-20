import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NLMQuery {

	public void insertArticle(String articletitle, String abstracttext,
			String author, String journal, String articledate) {
		String query = "";
		DatabaseClass db = new DatabaseClass();
		Connection conn = db.openConnection();
		conn = db.openConnection();
		try {
			query = "insert into article(articletitle, abstracttext, author, journal, articledate) values (\""
					+ articletitle
					+ "\", \""
					+ abstracttext
					+ "\", \""
					+ author
					+ "\", \""
					+ journal
					+ "\", \""
					+ articledate
					+ "\")";
			System.out.println(query);			
			PreparedStatement statement = conn.prepareStatement(query);
			statement.executeUpdate();
			db.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertAuthor(String lastname, String forename, String initials) {
		String query = "";
		DatabaseClass db = new DatabaseClass();
		Connection conn = db.openConnection();
		conn = db.openConnection();
		try {
			query = "insert into author(lastname, forename, initials) values (\""
					+ lastname
					+ "\", \""
					+ forename
					+ "\", \""
					+ initials
					+ "\")";
			System.out.println(query);
			
			PreparedStatement statement = conn.prepareStatement(query);
			statement.executeUpdate();
			db.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getNameid(String lastname, String forename) {
		String nameid = null;
		ResultSet rs = null;
		String query = "";
		DatabaseClass db = new DatabaseClass();
		Connection conn = db.openConnection();
		conn = db.openConnection();
		try {
			query = "select nameid from author where lastname='" + lastname
					+ "' and forename='" + forename + "'";
			System.out.println(query);
			if (!db.isConnected())
				conn = db.openConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			rs = statement.executeQuery();
			if (rs.next())
				nameid = rs.getString("nameid").toString();
			rs.close();
			db.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nameid;
	}

	public void insertJournal(String issn, String title,
			String isoabbreviation, String journalissue) {
		String query = "";
		DatabaseClass db = new DatabaseClass();
		Connection conn = db.openConnection();
		conn = db.openConnection();
		try {
			query = "insert into journal(issn, title, isoabbreviation, journalissue) values (\""
					+ issn
					+ "\", \""
					+ title
					+ "\", \""
					+ isoabbreviation
					+ "\", \"" + journalissue + "\")";
			System.out.println(query);
			
			PreparedStatement statement = conn.prepareStatement(query);
			statement.executeUpdate();
			db.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertIssue(String issue, String volume, String pubdate) {
		String query = "";
		DatabaseClass db = new DatabaseClass();
		Connection conn = db.openConnection();
		conn = db.openConnection();
		try {
			query = "insert into journalissue(issue, volume, pubdate) values (\""
					+ issue + "\", \"" + volume + "\", \"" + pubdate + "\")";
			System.out.println(query);
			
			PreparedStatement statement = conn.prepareStatement(query);
			statement.executeUpdate();
			db.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
