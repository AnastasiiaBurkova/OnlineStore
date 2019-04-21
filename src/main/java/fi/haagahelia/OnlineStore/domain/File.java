package fi.haagahelia.OnlineStore.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "file_db")
public class File {
	@Id
	private String id;
	
	private String fileName, base64str;
	
	private byte[] file;
	
	public File() {}
	
	public File(String fileName, byte[] file) {
	this.file=file;
	this.fileName=fileName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFilename() {
		return fileName;
	}

	public void setFilename(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}
	

}
