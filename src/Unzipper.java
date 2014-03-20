import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Unzipper extends DefaultHandler {
	private static final String INPUT = "c:\\xml.zip";
	private static final String OUTPUT = "c:\\xml.xml";

	public Unzipper() {
		super();
	}

	public static void main(String args[]) {
		try {
			BufferedOutputStream out = null;
			ZipInputStream in = new ZipInputStream(new BufferedInputStream(
					new FileInputStream(INPUT)));
			ZipEntry entry;
			while ((entry = in.getNextEntry()) != null) {
				int count;
				byte data[] = new byte[1000];
				out = new BufferedOutputStream(new FileOutputStream(OUTPUT),
						1000);
				while ((count = in.read(data, 0, 1000)) != -1) {
					out.write(data, 0, count);
				}
				out.flush();
				out.close();
			}

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {
				boolean isarticle = false;
				boolean isjournal = false;
				boolean isauthor = false;
				boolean isjournalissue = false;
				String qname;
				Article article;
				Author author;
				Journal journal;
				Issue issue;
				NLMQuery query = new NLMQuery();

				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {
					this.qname = qName;

					if (qName.equalsIgnoreCase("article")) {
						article = new Article();
						isarticle = true;
					}
					if (qName.equalsIgnoreCase("journal")) {
						journal = new Journal();
						isjournal = true;
					}
					if (qName.equalsIgnoreCase("author")) {
						author = new Author();
						isauthor = true;
					}
					if (qName.equalsIgnoreCase("journalissue")) {
						issue = new Issue();
						isjournalissue = true;
					}
				}

				public void endElement(String uri, String localName,
						String qName) throws SAXException {

					if (qName.equalsIgnoreCase("article")) {
						isarticle = false;
						query.insertArticle(article.getArticletitle(), article
								.getAbstracttext(), article.getAuthor(),
								article.getJournal(), article.getDate());
					}
					if (qName.equalsIgnoreCase("journal")) {
						isjournal = false;
						query.insertJournal(journal.getIssn(), journal
								.getTitle(), journal.getIsoabbreviation(),
								journal.getJournalissue());
					}
					if (qName.equalsIgnoreCase("author")) {
						isauthor = false;
						query.insertAuthor(author.getLastname(), author
								.getForename(), author.getInitials());
					}
					if (qName.equalsIgnoreCase("journalissue")) {
						isjournalissue = false;
						query.insertIssue(issue.getIssue(), issue.getVolume(),
								issue.getPubdate());
					}
				}

				public void characters(char ch[], int start, int length)
						throws SAXException {

					if (isarticle) {
						System.out.println(this.qname + " : "
								+ new String(ch, start, length));
						if (this.qname.equalsIgnoreCase("articletitle")) {
							article.setArticletitle(new String(ch, start,
									length));
						} else if (this.qname.equalsIgnoreCase("issn")) {
							article.setJournal(new String(ch, start, length));
						} else if (this.qname.equalsIgnoreCase("forename")) {
							article.setAuthor(new String(ch, start, length));
						} else if (this.qname.equalsIgnoreCase("year")) {
							article.setDate(new String(ch, start, length));
						} else if (this.qname.equalsIgnoreCase("abstracttext")) {
							article.setAbstracttext(new String(ch, start,
									length));
						}
						//return;
					}

					if (isjournal) {
						System.out.println(this.qname + " : "
								+ new String(ch, start, length));
						if (this.qname.equalsIgnoreCase("issn")){
							journal.setIssn(new String(ch, start, length));
						} else if (this.qname.equalsIgnoreCase("title")){
							journal.setTitle(new String(ch, start, length));
						} else if (this.qname.equalsIgnoreCase("isoabbreviation")){
							journal.setIsoabbreviation(new String(ch, start, length));
						} else if (this.qname.equalsIgnoreCase("journalissue")){
							journal.setJournalissue(new String(ch, start, length));
						}
						//return;
					}

					if (isjournalissue) {
						System.out.println(this.qname + " : "
								+ new String(ch, start, length));
						if (this.qname.equalsIgnoreCase("year")){
							issue.setPubdate(new String(ch, start, length));
						} else if (this.qname.equalsIgnoreCase("volume")){
							issue.setVolume(new String(ch, start, length));
						} else if (this.qname.equalsIgnoreCase("issue")){
							issue.setIssue(new String(ch, start, length));
						}
						//return;
					}

					if (isauthor) {
						System.out.println(this.qname + " : "
								+ new String(ch, start, length));
						if (this.qname.equalsIgnoreCase("lastname")) {
							author.setLastname(new String(ch, start, length));
						} else if (this.qname.equalsIgnoreCase("forename")) {
							author.setForename(new String(ch, start, length));
						} else if (this.qname.equalsIgnoreCase("initials")) {
							author.setInitials(new String(ch, start, length));
						}
						//return;
					}

				}

			};

			saxParser.parse(OUTPUT, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
