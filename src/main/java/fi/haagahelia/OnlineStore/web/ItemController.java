package fi.haagahelia.OnlineStore.web;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import fi.haagahelia.OnlineStore.domain.*;

/**
 * The item controller handles requests related to the main functionality, i.e.
 * itemList and filelist.
 * 
 * @author aburkova
 */
@Controller
public class ItemController {

	@Autowired
	ItemRepository itemrepository;

	@Autowired
	FileRepository filerepository;

	@Value("${upload.path}")
	private String uploadFolder;

	/**
	 * Redirects to login page.
	 * 
	 * @return "login"
	 */
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	/**
	 * Redirects to email page.
	 * 
	 * @return "emailbox"
	 */
	@RequestMapping(value = "/emailbox")
	public String emailpage() {
		return "emailbox";
	}

	/**
	 * Sends email according to entered parameters.
	 * 
	 * @param email
	 * @param bindingResult
	 * @return "itemlist" page if error occured or "success" page
	 * @throws AddressException   incorrect email address
	 * @throws MessagingException inappropriate message symbols
	 * @throws IOException        others
	 */
	@RequestMapping(value = "/sendemail", method = RequestMethod.POST)
	public String sendEmail(@Valid @ModelAttribute("email") Email email, BindingResult bindingResult)
			throws AddressException, MessagingException, IOException {
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

			msg.setFrom(new InternetAddress("afronika15@gmail.com", false));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("afronika15@gmail.com"));
			msg.setSubject(email.getSubject());
			msg.setContent(email.getContent(), "text/html");
			msg.setSentDate(new Date());

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(email.getContent(), "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			MimeBodyPart attachPart = new MimeBodyPart();
			attachPart.attachFile("/Users/aburkova/Downloads/" + email.getFile().getPath());
			multipart.addBodyPart(attachPart);
			msg.setContent(multipart);
			Transport.send(msg);
		} else {
			return "itemlist";
		}
		return "success";
	}

	/**
	 * Redirects to itemlist page and displays all items.
	 * 
	 * @param model
	 * @return "itemlist"
	 */
	@RequestMapping(value = "/itemList", method = RequestMethod.GET)
	public String itemList(Model model) {
		model.addAttribute("items", itemrepository.findAll());
		return "itemlist";
	}

	/**
	 * Redirects to "additem" page and adds new item to the list.
	 * 
	 * @param model
	 * @return "additem"
	 */
	@RequestMapping(value = "/add")
	public String addItem(Model model) {
		model.addAttribute("items", new Item());
		return "additem";
	}

	/**
	 * Saves new or modified item and redirects back to "itemlist" page.
	 * 
	 * @param item
	 * @return "redirect:itemList"
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveItem(Item item) {
		itemrepository.save(item);
		return "redirect:itemList";
	}

	/**
	 * Redirects to "edititem" page and allows to modify the selected item.
	 * 
	 * @param id
	 * @param model
	 * @return "edititem"
	 */
	@RequestMapping(value = "/edit/{id}")
	public String editItem(@PathVariable("id") String id, Model model) {
		model.addAttribute("items", itemrepository.findById(id));
		return "edititem";
	}

	/**
	 * Deletes the selected item.
	 * 
	 * @param id
	 * @param model
	 * @return "redirect:../itemList"
	 */
	@RequestMapping(value = "/delete/{id}")
	public String deleteItem(@PathVariable("id") String id, Model model) {
		itemrepository.deleteById(id);
		return "redirect:../itemList";
	}

	/**
	 * Implements upload file functionality.
	 * 
	 * @param file
	 * @param model
	 * @param item
	 * @return
	 */
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
			int i = 0;
			Path path_dir = null;
			for (i = 0; i < itemrepository.count(); i++) {
				path_dir = Files.createDirectories(Paths.get(uploadFolder + itemrepository.findAll().get(i).getId()));
			}
			Path path = Paths.get(path_dir + "/" + file.getOriginalFilename());
			Files.write(path, bytes);

			return "redirect:/filelist";
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "uploadedstatus";
	}

	/**
	 * Displays all the items on "filelist" page.
	 * 
	 * @param model
	 * @return "filelist"
	 */
	@GetMapping("/filelist")
	public String fileList(Model model) {
		model.addAttribute("filelist", filerepository.findAll());
		return "filelist";
	}

	/**
	 * Allows to download the selected item.
	 */
	@GetMapping("/file/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable String id) {
		Optional<File> fileOptional = filerepository.findById(id);

		if (fileOptional.isPresent()) {
			File file = fileOptional.get();
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
					.body(file.getFile());
		}

		return ResponseEntity.status(404).body(null);
	}

	/**
	 * Deletes the selected item.
	 * 
	 * @param id
	 * @param model
	 * @return "redirect:../filelist"
	 */
	@RequestMapping(value = "/filedelete/{id}")
	public String deleteFile(@PathVariable("id") String id, Model model) {
		filerepository.deleteById(id);
		return "redirect:../filelist";
	}

}
