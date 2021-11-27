package com.nttdata.model;

import java.time.LocalDateTime;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "account")
@Data
public class Account {
  
  @Id
  private String id;
  
  @Field(name = "cardNumber")
  private String cardNumber;

  @Field(name = "client")
  private Client client;

  @Field(name = "product")
  private Product product;

  @Field(name = "accountDate")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime accountDate = LocalDateTime.now();
  
}
  