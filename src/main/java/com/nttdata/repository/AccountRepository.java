package com.nttdata.repository;

import com.nttdata.model.Account;

import reactor.core.publisher.Flux;

public interface AccountRepository extends Repository<Account, String> {

  Flux<Account> findByClientId(String id);
  
}
