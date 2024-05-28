package com.example.technologydevicemanagement.service;

import com.example.technologydevicemanagement.model.Account;
import com.example.technologydevicemanagement.dao.DAOAccount;

import java.util.List;

public class AccountService implements IService<Account>{
    DAOAccount daoAccount = new DAOAccount();
    @Override
    public void saveData(Account model) {
        daoAccount.insert(model);
    }
    public boolean isExistUsername(String username) {
        return daoAccount.checkExitsUsername(username);
    }
    public boolean isExistAccount(String account,String password) {
        return daoAccount.checkExits(account,password);
    }
    @Override
    public Account getDataById(String id) {
        return daoAccount.getByUsername(id);
    }

    @Override
    public List<Account> getAllData(){
        return daoAccount.getAll();
    }
    @Override
    public void updateData(Account model) {
        daoAccount.update(model);
    }

    @Override
    public void deleteData(String id) {
        daoAccount.delete(id);
    }
}
