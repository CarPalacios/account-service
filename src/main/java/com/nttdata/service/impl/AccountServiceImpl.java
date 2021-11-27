package com.nttdata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.model.Account;
import com.nttdata.repository.AccountRepository;
import com.nttdata.repository.Repository;
import com.nttdata.service.AccountService;

import reactor.core.publisher.Flux;

@Service
public class AccountServiceImpl extends CrudServiceImpl<Account, String> implements AccountService {

@Autowired
private AccountRepository repository;

@Override
protected Repository<Account, String> getRepository() {
return repository;
}

@Override
public Flux<Account> findByClientId(String id) {
return repository.findByClientId(id);
}

}
