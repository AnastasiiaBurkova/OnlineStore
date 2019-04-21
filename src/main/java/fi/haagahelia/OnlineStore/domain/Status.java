package fi.haagahelia.OnlineStore.domain;

import java.util.List;


import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import fi.haagahelia.OnlineStore.domain.Item;

@Document
public class Status {
	@Id
	private String id;
	

}
