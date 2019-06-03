package com.controller;

import com.domain.Account;
import com.domain.Transfer;
import com.helper.AccountHelper;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import java.math.BigDecimal;

@Path("account")
public class AccountController {

    private AccountHelper accountHelper = new AccountHelper();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getAccountDetails(@PathParam("id") String id) {
        return accountHelper.getAccount(id);
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Account createAccount(Account account){
        return accountHelper.updateAccount(account);
    }
}