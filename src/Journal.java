
public class Journal {
	private String issn;
	private String title;
	private String isoabbreviation;
	private String journalissue;
	public Journal(){
		
	}
	public void setIssn(String issn) {
		this.issn = issn;
	}
	public String getIssn() {
		return issn;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setIsoabbreviation(String isoabbreviation) {
		this.isoabbreviation = isoabbreviation;
	}
	public String getIsoabbreviation() {
		return isoabbreviation;
	}
	public void setJournalissue(String journalissue) {
		this.journalissue = journalissue;
	}
	public String getJournalissue() {
		return journalissue;
	}
}
