package br.com.beertech.fusion.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

import br.com.beertech.fusion.controller.dto.AccountInfoDTO;
import br.com.beertech.fusion.controller.dto.OperationDTO;
import br.com.beertech.fusion.domain.Balance;
import br.com.beertech.fusion.domain.Operation;
import br.com.beertech.fusion.util.ApiInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.com.beertech.fusion.domain.CurrentAccount;
import br.com.beertech.fusion.service.CurrentAccountService;

import javax.validation.Valid;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/beercoin")
public class CurrentAccountController {

	@Autowired
	private CurrentAccountService currentAccountService;

    @ApiOperation(value =  ApiInfo.getaccountsTitle,  notes = ApiInfo.getaccountsNotes)
	@GetMapping("/getaccounts")
    public List<CurrentAccount> listAccounts() {
        return currentAccountService.listAccounts();
    }

    @GetMapping("/getaccountHash")
    @ApiOperation(value =  ApiInfo.getaccountHashTitle,  notes = ApiInfo.getaccountHashNotes)
    public CompletableFuture<ResponseEntity> listAccountByHash(@Valid @RequestBody AccountInfoDTO accountinfo) throws ExecutionException, InterruptedException {

        CompletableFuture<ResponseEntity> future = new CompletableFuture<>();
        try
        {
            future = CompletableFuture.supplyAsync(new Supplier<ResponseEntity>() {
                @Override
                public ResponseEntity get()
                {
                    CurrentAccount Account = currentAccountService.findAccountByHash(accountinfo.getHashAccount());
                    return new ResponseEntity<>(Account, OK);
                }
            });
        }
        catch (Exception e)
        { e.printStackTrace(); }
        return CompletableFuture.completedFuture(future.get());
    }


    @GetMapping("/getaccountId")
    @ApiOperation(value =  ApiInfo.getaccountIdTitle,  notes = ApiInfo.getaccountIdNotes)
    public CompletableFuture<ResponseEntity> listAccountById(@Valid @RequestBody AccountInfoDTO accountinfo) throws ExecutionException, InterruptedException {

        CompletableFuture<ResponseEntity> future = new CompletableFuture<>();
        try
        {
            future = CompletableFuture.supplyAsync(new Supplier<ResponseEntity>() {
                @Override
                public ResponseEntity get()
                {
                    CurrentAccount Account = currentAccountService.findAccountById(accountinfo.getIdAccount());
                    return new ResponseEntity<>(Account, OK);
                }
            });
        }
        catch (Exception e)
        { e.printStackTrace(); }
        return CompletableFuture.completedFuture(future.get());
    }

}
