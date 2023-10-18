package ascentitllc.submittions.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
@Entity

public class Profile {
	  @Id
	    private String id;
	    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
		private String name;
	    private String gender;
	    private String technology;
	    private int experience;
	    private String visa;
	    private String recruiter;
	    private long contact;
	    private String gmailId;
		private String note;
	    private String progress;
	    private String submissionDate;
	    private List<FileInfo> file;


	    
		public List<FileInfo> getFile() {
			return file;
		}
		public void setFile(List<FileInfo> file) {
			this.file = file;
		}
	
	
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getTechnology() {
			return technology;
		}
		public void setTechnology(String technology) {
			this.technology = technology;
		}
		public int getExperience() {
			return experience;
		}
		public void setExperience(int experience) {
			this.experience = experience;
		}
		public String getVisa() {
			return visa;
		}
		public void setVisa(String visa) {
			this.visa = visa;
		}
		public String getRecruiter() {
			return recruiter;
		}
		public void setRecruiter(String recruiter) {
			this.recruiter = recruiter;
		}
		public long getContact() {
			return contact;
		}
		public void setContact(long contact) {
			this.contact = contact;
		}
		public String getGmailId() {
			return gmailId;
		}
		public void setGmailId(String gmailId) {
			this.gmailId = gmailId;
		}
		
		public String getNote() {
			return note;
		}
		public void setNote(String note) {
			this.note = note;
		}
		public String getProgress() {
			return progress;
		}
		public void setProgress(String progress) {
			this.progress = progress;
		}
		public String getSubmissionDate() {
			return submissionDate;
		}
		public void setSubmissionDate(String submissionDate) {
			this.submissionDate = submissionDate;
		}
		
		
		public Profile() {
			super();
		}
		public Profile(String id,String name, String gender, String technology, int experience, String visa,
                String recruiter, long contact, String gmailId, String note, String progress,
                String submissionDate, List<FileInfo> file) {
     this.name = name;
     this.gender = gender;
     this.technology = technology;
     this.experience = experience;
     this.visa = visa;
     this.id=id;
     this.recruiter = recruiter;
     this.contact = contact;
     this.gmailId = gmailId;
     this.note = note;
     this.progress = progress;
     this.submissionDate = submissionDate;
     this.file = file;
 }
}
