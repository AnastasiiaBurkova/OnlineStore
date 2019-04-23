package fi.haagahelia.OnlineStore.domain;

import java.io.File;

/**
 * An object for Email.
 * 
 * @author aburkova
 */
public class Email {

	private String subject;
	private String content;
	private File file;

	/**
	 * Sets empty Email object.
	 */
	public Email() {

	}

	/**
	 * Sets parameters for Email object.
	 * 
	 * @param content
	 * @param subject
	 * @param file
	 */
	public Email(String content, String subject, File file) {
		this.content = content;
		this.subject = subject;
		this.file = file;
	}

	/**
	 * Gives email subject.
	 * 
	 * @return subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets email subject.
	 * 
	 * @param subject subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Gives email content.
	 * 
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets email content.
	 * 
	 * @param content content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Gives File object.
	 * 
	 * @return file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Sets File object.
	 * 
	 * @param file file
	 */
	public void setFile(File file) {
		this.file = file;
	}

}
