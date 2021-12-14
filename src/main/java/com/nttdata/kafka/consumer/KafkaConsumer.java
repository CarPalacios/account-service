package com.nttdata.kafka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.model.Client;
import com.nttdata.repository.ClientRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class KafkaConsumer {
  
  @Autowired
  private ClientRepository clientRepo;
  
  ObjectMapper objectMapper = new ObjectMapper();
  
  @KafkaListener(topics = "TOPIC-CLIENT", groupId = "group-account")
  public Disposable consume(String data) throws Exception {
      log.info("Consuming Message: {}", data);
      
      Client client = objectMapper.readValue(data, Client.class);
      
      return Mono.just(client)
          .log()
          .flatMap(clientRepo::save)
          .subscribe();
      
  }
  
//  @KafkaListener(topics = "TOPIC-PRODUCT", groupId = "group-account")
//  public Disposable consume2(String data) throws Exception {
//      log.info("Consuming Message: {}", data);
//      
//      Client client = objectMapper.readValue(data, Client.class);
//      
//      return Mono.just(client)
//          .log()
//          .flatMap(clientRepo::save)
//          .subscribe();
//      
//  }

}
