package fi.haagahelia.OnlineStore.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * An object for File.
 * 
 * @author aburkova
 */
@Document(collection = "file_db")
public class File {
	@Id
	private String id;
	private String fileName, base64str;
	private byte[] file;

	/**
	 * Sets empty File object.
	 */
	public File() {
	}

	/**
	 * Sets parameters for File object.
	 * 
	 * @param fileName
	 * @param file
	 */
	public File(String fileName, byte[] file) {
		this.file = file;
		this.fileName = fileName;
	}

	/**
	 * Gives file id.
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets file id.
	 * 
	 * @param id id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gives filename.
	 * 
	 * @return fileName
	 */
	public String getFilename() {
		return fileName;
	}

	/**
	 * Sets filename.
	 * 
	 * @param fileName filename
	 */
	public void setFilename(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gives file byte content.
	 * 
	 * @return file
	 */
	public byte[] getFile() {
		return file;
	}

	/**
	 * Sets file byte content.
	 * 
	 * @param file file
	 */
	public void setFile(byte[] file) {
		this.file = file;
	}

}
