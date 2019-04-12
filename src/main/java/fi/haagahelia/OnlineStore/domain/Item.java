package fi.haagahelia.OnlineStore.domain;




import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document (collection = "item_db")
public class Item {
	
	@Id
	private String id;
	
	private String name;
	private double price;
	private String description;
	private String comment;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Item() {
		
	}
	
	public Item(String name, double price, String description, String comment) {
	   
		this.name=name;
		this.price=price;
		this.description=description;
		this.comment=comment;	
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String toString() {
		return id + " "+ name+ " "+ price + " "+ description + " "+ comment;
	}
	

}
