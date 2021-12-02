package com.nttdata.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.nttdata.model.Account;
import com.nttdata.model.Client;
import com.nttdata.model.Product;
import com.nttdata.repository.AccountRepository;
import com.nttdata.service.impl.AccountServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Import(AccountServiceImpl.class)
@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = AccountController.class, excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})
class AccountControllerTest {
  
  @Autowired
  private WebTestClient account;
  
  @MockBean
  private AccountRepository repository;

  @Test
  void testFindAll() {
    
    Account accounts = new Account();
    
    List<String> type = new ArrayList<>();
    type.add("1");
    
    accounts.setId("1");
    accounts.setCardNumber("12345678998752");
    accounts.setAccountDate(LocalDateTime.now());
    
    Client client = new Client();
    client.setId("1");
    Product product = new Product();
    product.setId("2");
    accounts.setClient(client);
    accounts.setProduct(product);
    
    List<Account> list = new ArrayList<>();
    list.add(accounts);
    Flux<Account> fluxaccount = Flux.fromIterable(list); 
    
    Mockito.when(repository.findAll()).thenReturn(fluxaccount);
    account.get().uri("/account").accept(MediaType.APPLICATION_JSON)
    .exchange().expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON)
    .expectBodyList(Account.class).hasSize(1);
         
  }

  @Test
  void testFindById() {
    
    Account accounts = new Account();
    
    List<String> type = new ArrayList<>();
    type.add("1");
    
    accounts.setId("1");
    accounts.setCardNumber("12345678998752");
    accounts.setAccountDate(LocalDateTime.now());
    
    Client client = new Client();
    client.setId("1");
    Product product = new Product();
    product.setId("2");
    accounts.setClient(client);
    accounts.setProduct(product);
    
    Mockito.when(repository.findById("1")).thenReturn(Mono.just(accounts));
    account.get().uri("/account/1")
    .accept(MediaType.APPLICATION_JSON).exchange()
    .expectStatus().isOk()
    .expectHeader().contentType(MediaType.APPLICATION_JSON)
    .expectBody()
    .jsonPath("$.id").isNotEmpty()
    .jsonPath("$.cardNumber").isEqualTo("12345678998752");
    
  }

  @Test
  void testCreate() {
    
    Account accounts = new Account();
    
    List<String> type = new ArrayList<>();
    type.add("2");
    
    accounts.setId("2");
    accounts.setCardNumber("12345678998584");
    accounts.setAccountDate(LocalDateTime.now());
    
    Client client = new Client();
    client.setId("2");
    Product product = new Product();
    product.setId("3");
    accounts.setClient(client);
    accounts.setProduct(product);
    
    Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(Mono.just(accounts));
    account.post().uri("/account")
    .contentType(MediaType.APPLICATION_JSON)
    .accept(MediaType.APPLICATION_JSON)
    .body(Mono.just(accounts), Account.class)
    .exchange()
    .expectStatus().isCreated()
    .expectHeader().contentType(MediaType.APPLICATION_JSON)
    .expectBody()
    .jsonPath("$.id").isNotEmpty()
    .jsonPath("$.cardNumber").isEqualTo("12345678998584");
    
  }

}
