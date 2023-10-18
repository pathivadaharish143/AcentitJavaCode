package ascentitllc.submittions.controller;import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import ascentitllc.submittions.entity.FileInfo;
import ascentitllc.submittions.entity.Profile;
import ascentitllc.submittions.entity.User;
import ascentitllc.submittions.repo.Repo;
import ascentitllc.submittions.service.ServiceImple;
import ascentitllc.submittions.service.Serviceascent;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/ascentitllc")
@CrossOrigin(origins = "*")
public class ProfileController {

    @Autowired
    public Repo profileRepository;
    @Autowired
    public Serviceascent serviceImple;
    
    Logger logger=LoggerFactory.getLogger(getClass());


    @PostMapping("/submit")
    public ResponseEntity<Profile> saveProfile(@RequestParam("files") List<MultipartFile> files,
            @RequestParam String name,
            @RequestParam String gender,
            @RequestParam String technology,
            @RequestParam String experience,
            @RequestParam String visa,
            @RequestParam String recruiter,
            @RequestParam String contact,
            @RequestParam String gmailId,
            @RequestParam String note,
            @RequestParam String progress,
            @RequestParam String submissionDate) throws IOException{
    	
    	 Profile profile = new Profile();
    	 List<FileInfo> list = new  ArrayList<>();
    	 profile.setName(name);
    	 profile.setSubmissionDate(submissionDate);
 		profile.setGender(gender);
 		profile.setGmailId(gmailId);
 		profile.setExperience(Integer.parseInt(experience));
 		profile.setNote(note);
 		profile.setProgress(progress);
 		profile.setRecruiter(recruiter);
 		profile.setContact(Long.parseLong(contact));
 		profile.setTechnology(technology);
 		profile.setVisa(visa);
 		
 		
 		for(MultipartFile file:files) {
 			FileInfo fileInfo = new FileInfo();
 			fileInfo.setFilename(file.getOriginalFilename());
 			fileInfo.setFiletype(file.getContentType());
 			fileInfo.setFile(file.getBytes());
 			list.add(fileInfo);
 		}
 		profile.setFile(list);
 		profile=serviceImple.saveProfile(profile);
    	
    	
    	
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(profile);
    }
    
    @GetMapping("/AllProfiles")
	public ResponseEntity<List<Profile>> getAllProfiles(){
		List<Profile>ListOfprofiles=serviceImple.getallprofiles();
		if(ListOfprofiles.isEmpty()) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ListOfprofiles);
		}
		return ResponseEntity.ok(ListOfprofiles);
		
	}
  @GetMapping("/getbyid/{id}")
  public ResponseEntity<Profile> getbyid(@PathVariable("id") String id){
	Profile profile = serviceImple.getbyid(id);
	if(profile!=null) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(profile);
	}
	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	  
  }
//  @GetMapping("/downloadFile/{id}")
//  public ResponseEntity<ByteArrayResource> getfile(@PathVariable("id") String id){
//	  Profile profile= serviceImple.getbyid(id);
//	  
//	return ResponseEntity.ok().contentType(MediaType.parseMediaType(profile.getFile()))
//			.header(HttpHeaders.CONTENT_DISPOSITION,"profile;filename=\""+fileInfo.getFilename(),"\"")
//			.body(null);
//	  
//  }
  @GetMapping("/downloadFilesAsZip/{id}")
  public ResponseEntity<byte[]> downloadFilesAsZip(@PathVariable("id") String id) throws IOException {
      Profile profile = serviceImple.getbyid(id);

      if (profile == null) {
          return ResponseEntity.notFound().build();
      }

      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);

      // Iterate through each FileInfo and add them to the ZIP archive with unique entry names
      int fileCounter = 1;
      for (FileInfo fileInfo : profile.getFile()) {
    	  
          String originalFilename = fileInfo.getFilename();
          String uniqueEntryName = "file" + fileCounter + "_" + originalFilename;
          zipOutputStream.putNextEntry(new ZipEntry(uniqueEntryName));
          zipOutputStream.write(fileInfo.getFile());
          zipOutputStream.closeEntry();
          fileCounter++;
      }

      zipOutputStream.close();

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
      headers.setContentDispositionFormData("attachment", "files.zip");

      return new ResponseEntity<>(byteArrayOutputStream.toByteArray(), headers, HttpStatus.OK);
  }

  @PostMapping("/user")
  public ResponseEntity<User> saveuser(@RequestBody User user){
	  return new ResponseEntity<User>(serviceImple.saveUser(user),(HttpStatus.ACCEPTED));  
  }
  @GetMapping("/login/{gmail}/{password}")
  public ResponseEntity<User> validate(@PathVariable String gmail, @PathVariable String password){
	  User user = serviceImple.login(gmail, password);
	  if(user!=null) {
		  return ResponseEntity.ok(user);
	  }
	  else {
	  return ResponseEntity.notFound().build();
  }}
  @PutMapping("/update/{id}")
  public ResponseEntity<Profile> Update(@RequestParam("files") List<MultipartFile> files ,
		  @RequestParam String name,
          @RequestParam String gender,
          @RequestParam String technology,
          @RequestParam int experience,
          @RequestParam String visa,
          @RequestParam long contact,
          @RequestParam String gmailId,
          @RequestParam String note,
          @RequestParam String progress,
          @PathVariable String id) throws IOException{
	   Profile Existingprofile = serviceImple.getbyid(id);
	   
	   logger.info("caa");
	   System.out.print("hii");
	  
	   if(Existingprofile!=null) {
		   Existingprofile.setName(name);
		   Existingprofile.setExperience(experience);
		   Existingprofile.setGender(gender);
		   Existingprofile.setGmailId(gmailId);
		   Existingprofile.setProgress(progress);
		   Existingprofile.setNote(note);
		   Existingprofile.setTechnology(technology);
		   Existingprofile.setVisa(visa);
          Existingprofile.setContact(contact);		
          
          List<FileInfo> list=new ArrayList<>();
		   for(MultipartFile file :files) {
				FileInfo fileInfo = new FileInfo();
				fileInfo.setFilename(file.getOriginalFilename());
				fileInfo.setFiletype(file.getContentType());
				fileInfo.setFile(file.getBytes());
				list.add(fileInfo);
		   }
		   Existingprofile.setFile(list);
		   Profile updatedprofile= serviceImple.edit(Existingprofile, id);
		   return ResponseEntity.ok().body(updatedprofile);
		   }
   else {
      return ResponseEntity.notFound().build();
  }
}
  @PatchMapping("/edit/{id}")
  public ResponseEntity<Profile> partialUpdateProfile(
      @RequestParam(value = "files", required = false) List<MultipartFile> files,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "gender", required = false) String gender,
      @RequestParam(value = "technology", required = false) String technology,
      @RequestParam(value = "experience", required = false) Integer experience,
      @RequestParam(value = "visa", required = false) String visa,
      @RequestParam(value = "contact", required = false) Long contact,
      @RequestParam(value = "gmailId", required = false) String gmailId,
      @RequestParam(value = "note", required = false) String note,
      @RequestParam(value = "progress", required = false) String progress,
      @PathVariable String id) throws IOException {
      Profile existingProfile = serviceImple.getbyid(id);
      


      if (existingProfile != null) {
          // Update fields if provided
          if (name != null) {
              existingProfile.setName(name);
          }
          if (experience != null) {
              existingProfile.setExperience(experience);
          }
          if (gender != null) {
              existingProfile.setGender(gender);
          }
          if (gmailId != null) {
              existingProfile.setGmailId(gmailId);
          }
          if (progress != null) {
              existingProfile.setProgress(progress);
          }
          if (note != null) {
              existingProfile.setNote(note);
          }
          if (technology != null) {
              existingProfile.setTechnology(technology);
          }
          if (visa != null) {
              existingProfile.setVisa(visa);
          }
          if (contact != null) {
              existingProfile.setContact(contact);
          }

          // Update files if provided
          if (files != null) {
              List<FileInfo> fileList = new ArrayList<>();
              for (MultipartFile file : files) {
                  FileInfo fileInfo = new FileInfo();
                  fileInfo.setFilename(file.getOriginalFilename());
                  fileInfo.setFiletype(file.getContentType());
                  fileInfo.setFile(file.getBytes());
                  fileList.add(fileInfo);
              }
              existingProfile.setFile(fileList);
          }

          Profile updatedProfile = serviceImple.edit(existingProfile, id);
          return ResponseEntity.ok().body(updatedProfile);
      } else {
          return ResponseEntity.notFound().build();
      }
  }

}
  
