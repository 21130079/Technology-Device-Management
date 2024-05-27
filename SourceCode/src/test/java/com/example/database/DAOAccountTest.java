package com.example.database;

import com.example.technologydevicemanagement.model.Account;
import database.DAOAccount;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DAOAccountTest {

    DAOAccount daoAccount = new DAOAccount();

    @Test
    void getAll() {
        ArrayList<Account> accounts = daoAccount.getAll();
        // kiểm tra có dữ liệu không
        assertNotNull(accounts);
        assertFalse(accounts.isEmpty());
    }

    @Test
    void checkExits() {
        // kiểm tra với dữ liệu có trong db
        assertTrue(daoAccount.checkExits("ledat1","22"));// expected true
        // Kiểm tra với dữ liệu không có trong db
        assertFalse(daoAccount.checkExits("ledat1","2233")); // expected false
    }

    @Test
    void checkExitsUsername() {
        // kiểm tra với dữ liệu có trong db
        assertTrue(daoAccount.checkExitsUsername("ledat1"));
        // Kiểm tra với dữ liệu không có trong db
        assertFalse(daoAccount.checkExitsUsername("ledat0"));
    }

    @Test
    void insert() {
        // tạo 1 đối tượng Account mới
        ArrayList<String> roles = new ArrayList<String>();
        roles.add("Manager");
        Account newAccount = new Account("newUser", "newPassword",roles);
        int result = daoAccount.insert(newAccount);
        assertEquals(1, result);
        System.out.println(daoAccount.getByUsername("newUser"));
    }

    @Test
    void update() {
        System.out.println("Tài khoản chưa update: " + daoAccount.getByUsername("newUser"));
        ArrayList<String> roles = new ArrayList<String>();
        roles.add("Sale staff");
        Account existingAccount = new Account("newUser", "newPassword2233",roles);
        int result = daoAccount.update(existingAccount);
        assertEquals(1, result);
        System.out.println("Tài khoản đã update: " + daoAccount.getByUsername("newUser"));
    }

    @Test
    void getByUsername() {
        Account account = daoAccount.getByUsername("ledat1");
        assertNotNull(account);
        assertEquals("ledat1", account.getUsername());
        assertEquals("22", account.getPasswd());
    }

    @Test
    void delete() {
        int result = daoAccount.delete("ledat1");
        assertEquals(1, result);
        assertFalse(daoAccount.checkExitsUsername("ledat1"));
    }
}