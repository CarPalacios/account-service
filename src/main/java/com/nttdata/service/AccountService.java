package com.nttdata.service;

import com.nttdata.model.Account;

import reactor.core.publisher.Flux;

public interface AccountService extends CrudService<Account, String> {
  
  Flux<Account> findByClientId(String id);

}
