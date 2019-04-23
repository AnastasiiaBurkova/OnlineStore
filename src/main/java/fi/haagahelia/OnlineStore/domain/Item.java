package fi.haagahelia.OnlineStore.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * An object for Item.
 * 
 * @author aburkova
 */
@Document(collection = "item_db")
public class Item {

	@Id
	private String id;
	private String name;
	private double price;
	private String description;

	/**
	 * Sets empty Item object.
	 */
	public Item() {
	}

	/**
	 * Sets parameters for Item object.
	 * 
	 * @param name
	 * @param price
	 * @param description
	 */
	public Item(String name, double price, String description) {

		this.name = name;
		this.price = price;
		this.description = description;
	}

	/**
	 * Gives item id.
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets item id.
	 * 
	 * @param id id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gives item name.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets item name.
	 * 
	 * @param name name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gives item price.
	 * 
	 * @return price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets item price.
	 * 
	 * @param price price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Gives item description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets item description.
	 * 
	 * @param description description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gives item in string.
	 * 
	 * @return item object in string
	 */
	public String toString() {
		return id + " " + name + " " + price + " " + description;
	}
}
