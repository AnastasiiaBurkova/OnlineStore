package fi.haagahelia.OnlineStore.domain;

public class Email {
	
	private String subject;
	private String content;
	
	public Email() {
		
	}
	
	public Email(String content, String subject) {
		this.content=content;
		this.subject=subject;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

}
