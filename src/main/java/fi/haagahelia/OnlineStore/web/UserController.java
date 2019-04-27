package fi.haagahelia.OnlineStore.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.OnlineStore.domain.SignupForm;
import fi.haagahelia.OnlineStore.domain.User;
import fi.haagahelia.OnlineStore.domain.UserRepository;

/**
 * The user controller handles requests related to users sign up and saving
 * credentials.
 * 
 * @author aburkova
 */
@Controller
public class UserController {
	@Autowired
	private UserRepository userrepository;

	/**
	 * Redirects to "signup" page.
	 * 
	 * @param model
	 * @return "signup"
	 */
	@RequestMapping(value = "signup")
	public String addSignUp(Model model) {
		model.addAttribute("signupform", new SignupForm());
		return "signup";
	}

	/**
	 * Saves new user.
	 * 
	 * @param signupForm
	 * @param bindingResult
	 * @return "signup" if error occured or "redirect:/login"
	 */
	@RequestMapping(value = "saveuser", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) {
				String pwd = signupForm.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPwd = bc.encode(pwd);

				User newUser = new User();
				newUser.setPasswordHash(hashPwd);
				newUser.setUsername(signupForm.getUsername());
				newUser.setRole("USER");
				if (userrepository.findByUsername(signupForm.getUsername()) == null) {
					userrepository.save(newUser);
				} else {
					bindingResult.rejectValue("username", "err.username", "Username already exists");
					return "signup";
				}
			} else {
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
				return "signup";
			}
		} else {
			return "signup";
		}
		return "redirect:/login";
	}
}
