package com.nttdata.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.model.Account;
import com.nttdata.service.AccountService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account")
public class AccountController {
  
  @Autowired
  private AccountService service;
  
/** Muestra los registros de la tabla * @return registro de la tabla seleccionada. */
@GetMapping
public Mono<ResponseEntity<Flux<Account>>> findAll() {
  
  Flux<Account> account = service.findAll();
  
  return Mono.just(ResponseEntity
      .ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(account));
  
}

@GetMapping("/{id}")
public Mono<ResponseEntity<Account>> findById(@PathVariable("id") String id) {

  return service.findById(id)
      .map(c -> ResponseEntity
          .ok()
          .contentType(MediaType.APPLICATION_JSON)
          .body(c))
      .defaultIfEmpty(ResponseEntity
          .noContent()
          .build());    
}

@GetMapping("/client/{id}")
public Mono<ResponseEntity<Flux<Account>>> findByClientId(@PathVariable("id") String id) {

  Flux<Account> clientAccount = service.findByClientId(id);
  
  return Mono.just(ResponseEntity
      .ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(clientAccount));    
}

/** Crea los registros de la tabla.* @return crea registros de la tabla. */
@PostMapping
public Mono<ResponseEntity<Account>> 
    create(@RequestBody Account account, final ServerHttpRequest request) {
  
//  Flux<Account> clientAccount = service.findByClientId(account.getClient().getId());
      
  
//  if (account.getProduct().getId().equals(clientAccount) && account.getClient().getId().equals(clientAccount)) {
//    return Mono.just(ResponseEntity.badRequest().build());
//  }
  
  return service.create(account)
      .map(c -> ResponseEntity
          .created(URI.create(request.getURI().toString().concat("/").concat(c.getId())))
          .contentType(MediaType.APPLICATION_JSON)
          .body(c));
  
}

}
