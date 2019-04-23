package fi.haagahelia.OnlineStore.domain;

import java.io.File;

public class Email {
	
	private String subject;
	private String content;
	private File file;
	
	public Email() {
		
	}
	
	public Email(String content, String subject, File file) {
		this.content=content;
		this.subject=subject;
		this.file=file;
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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}	

}
