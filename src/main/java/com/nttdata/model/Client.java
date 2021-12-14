package com.nttdata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;

/**Declarando las variables de la clase Client.*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "client")
public class Client {

  @Id
  private String id;
  
  private String name;
  
  private String type;
  
  @Field(name = "identity_number")
  private String identityNumber;
  
}
