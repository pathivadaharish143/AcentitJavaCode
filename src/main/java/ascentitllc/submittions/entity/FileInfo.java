package ascentitllc.submittions.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class FileInfo {

	@Id
	private String id;
	private String filename;
	private String filetype;
	private byte[] file;

	public FileInfo(String filename, String filetype, byte[] file) {
		this.filename = filename;
		this.filetype = filetype;
		this.file = file;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] files) {
		this.file = files;
	}

	public FileInfo() {
	}

}
