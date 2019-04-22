package fi.haagahelia.OnlineStore.web;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.validation.Valid;
import javax.mail.Authenticator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fi.haagahelia.OnlineStore.domain.Email;
import fi.haagahelia.OnlineStore.domain.File;
import fi.haagahelia.OnlineStore.domain.FileRepository;
import fi.haagahelia.OnlineStore.domain.Item;
import fi.haagahelia.OnlineStore.domain.ItemRepository;

@Controller
public class ItemController {
	

	@Autowired
	ItemRepository itemrepository;
	
	@Autowired
	FileRepository filerepository;
	
	@Value("${upload.path}")
    private String uploadFolder;
	
	@RequestMapping(value="/login")
	    public String login() {	
	        return "login";
	    }
	
	  @RequestMapping(value = "/emailbox")
	    public String emailpage(){
	        return "emailbox";
	    }
	
	@RequestMapping(value = "/sendemail", method = RequestMethod.POST)
	public String sendEmail(@Valid @ModelAttribute("email") Email email,BindingResult bindingResult) throws AddressException, MessagingException, IOException {
		if (!bindingResult.hasErrors()) {
		Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   Session session = Session.getInstance(props, new Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("afronika15@gmail.com", "Bb24081998");
		      }
		   });
		   Message msg = new MimeMessage(session);
		   
		   //model.addAttribute("emailbox", new Email());
		   
		   msg.setFrom(new InternetAddress("afronika15@gmail.com", false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("afronika15@gmail.com"));
		   msg.setSubject(email.getSubject());
		   msg.setContent(email.getContent(), "text/html");
		   msg.setSentDate(new Date());

		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent(email.getContent(), "text/html");

		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(messageBodyPart);
		   /*MimeBodyPart attachPart = new MimeBodyPart();

		   attachPart.attachFile("/Users/aburkova/Downloads/hamster.jpeg");
		   multipart.addBodyPart(attachPart);*/
		   msg.setContent(multipart);
		   Transport.send(msg); 
		}
		else {
	    		return "itemlist";
		}
	   return "success";   
	} 
	
	@RequestMapping(value= "/itemList", method=RequestMethod.GET)
	public String itemList(Model model) {
	model.addAttribute("items", itemrepository.findAll());
		return "itemlist";
	}
	
	@RequestMapping(value="/add")
	public String addItem(Model model) {
		model.addAttribute("items", new Item());
		return "additem";	
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveItem(Item item) {
		itemrepository.save(item);
		return "redirect:itemList";	
	}
	
	@RequestMapping(value="/edit/{id}")
	public String editItem(@PathVariable("id") String id, Model model) {
		model.addAttribute("items", itemrepository.findById(id));
		return "edititem";	
	}
	@RequestMapping(value="/delete/{id}")
	public String deleteItem(@PathVariable("id") String id, Model model) {
		itemrepository.deleteById(id);
		return "redirect:../itemList";
	}
	
	@PostMapping("/itemList")
    public String fileUpload(@RequestParam("file") MultipartFile file, Model model, Item item) {
		  if (file.isEmpty()) {
	        	model.addAttribute("msg", "Upload failed");
	            return "uploadstatus";
	        }
        try {
            File fileModel = new File(file.getOriginalFilename(), file.getBytes());
            filerepository.save(fileModel);
            byte[] bytes = file.getBytes();
            System.out.println(filerepository.count()+"repo");
            int i=0;
            for (i=0; i<itemrepository.count(); i++) {
            	Path path_dir=Files.createDirectories(Paths.get(uploadFolder+itemrepository.findAll().get(i).getId()));
             
            	System.out.println("blabla"+itemrepository.findAll().get(i).getId());
            	System.out.println("first"+itemrepository.findAll().get(i));

            System.out.println("path_dir"+path_dir);

            Path path = Paths.get(path_dir+"/"+ file.getOriginalFilename());
            Files.write(path, bytes);
            } 
            return "redirect:/filelist";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "uploadedstatus";
    }
	
	@GetMapping("/filelist")
    public String fileList(Model model) {
    	model.addAttribute("filelist", filerepository.findAll());  	
    	return "filelist";
    }
    
	@GetMapping("/file/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable String id) {
		Optional<File> fileOptional = filerepository.findById(id);
		
		if(fileOptional.isPresent()) {
			File file = fileOptional.get();
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
					.body(file.getFile());	
		}
		
		return ResponseEntity.status(404).body(null);
	}
	@RequestMapping(value="/filedelete/{id}")
	public String deleteFile(@PathVariable("id") String id, Model model) {
		filerepository.deleteById(id);
		return "redirect:../filelist";
	}
	
	

}
