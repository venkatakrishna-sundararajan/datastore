package com.controller;

import com.domain.Account;
import com.domain.Transfer;
import com.helper.TransferHelper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;

@Path("/transfer")
public class TransferController {

    private TransferHelper transferHelper = new TransferHelper();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Transfer getTransferDetails(@PathParam("id") String transfer_Id) {
        return transferHelper.getTransfer(transfer_Id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Transfer transferAmount(Transfer transfer_details){
        return transferHelper.postTransfer(transfer_details);
    }

}
