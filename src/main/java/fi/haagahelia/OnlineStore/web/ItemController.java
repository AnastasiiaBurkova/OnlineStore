package fi.haagahelia.OnlineStore.web;



import org.springframework.http.MediaType;


import java.util.List;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fi.haagahelia.OnlineStore.domain.Item;
import fi.haagahelia.OnlineStore.domain.ItemRepository;

@Controller
public class ItemController {
	
	//private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	ItemRepository itemrepository;
	
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
	
	
	
	

}
