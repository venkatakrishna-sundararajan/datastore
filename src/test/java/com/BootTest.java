package com;

import com.controller.AccountController;

import com.controller.TransferController;
import com.domain.Account;
import com.domain.Transfer;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class BootTest extends JerseyTest{

    List<Account> accountList = new ArrayList<Account>();
    List<Transfer> transferList = new ArrayList<Transfer>();
    @Override
    protected Application configure() {
        ResourceConfig config = new ResourceConfig();
        config.register(AccountController.class);
        config.register(TransferController.class);
        return config;
    }

    @Before
    public void testCreateTwoAccounts(){
        Account account = new Account();
        account.setBalance(new BigDecimal(100));
        account.setAccountName("Account 1");
        Response output = target("/account")
                .request()
                .post(Entity.entity(account, MediaType.APPLICATION_JSON));
        Assert.assertEquals(200, output.getStatus());
        Account responseEntity = output.readEntity(Account.class);
        Assert.assertNotNull(responseEntity);
        accountList.add(responseEntity);

        account.setAccountName("Account 2");
        output = target("/account")
                .request()
                .post(Entity.entity(account, MediaType.APPLICATION_JSON));
        Assert.assertEquals(200, output.getStatus());
        responseEntity = output.readEntity(Account.class);
        Assert.assertNotNull(responseEntity);
        accountList.add(responseEntity);
    }

    @Test
    public void testGetAccountDetails(){
        String id = accountList.get(0).getId();
        Response response = target("account/"+id).request().get();
        Assert.assertEquals(200, response.getStatus());
        Assert.assertNotNull(response.getEntity());
    }

    @Test
    public void testPostTransfer(){
        Transfer transfer = new Transfer();
        transfer.setFromAccountId(accountList.get(0).getId());
        transfer.setToAccountId(accountList.get(1).getId());
        transfer.setTransferAmount(BigDecimal.TEN);
        Response output = target("/transfer")
                .request()
                .post(Entity.entity(transfer, MediaType.APPLICATION_JSON));

        Assert.assertEquals(200, output.getStatus());
        Assert.assertNotNull(output.getEntity());
        transferList.add(output.readEntity(Transfer.class));
        testAccountBalance();
    }


    public void testAccountBalance(){
        String id = accountList.get(0).getId();
        Response response = target("account/"+id).request().get();
        Account account1 = response.readEntity(Account.class);
        Assert.assertEquals(new BigDecimal(90), account1.getBalance());

        id = accountList.get(1).getId();
        response = target("account/"+id).request().get();
        Account account2 = response.readEntity(Account.class);
        Assert.assertEquals(new BigDecimal(110), account2.getBalance());
    }


}
