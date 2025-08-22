package com.fsse2506.fsse2506backend.controller.transaction;

import com.fsse2506.fsse2506backend.data.transaction.domainObject.TransactionResponseData;
import com.fsse2506.fsse2506backend.data.transaction.dto.TransactionResponseDto;
import com.fsse2506.fsse2506backend.data.user.domainObject.request.FirebaseUserData;
import com.fsse2506.fsse2506backend.data.user.entity.UserEntity;
import com.fsse2506.fsse2506backend.mapper.transaction.TransactionDtoMapper;
import com.fsse2506.fsse2506backend.mapper.user.UserDataMapper;
import com.fsse2506.fsse2506backend.mapper.user.UserEntityMapper;
import com.fsse2506.fsse2506backend.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@CrossOrigin("http://localhost:5173")
public class TransactionController {


    private final TransactionService transactionService;
    private final UserDataMapper userDataMapper;
    private final TransactionDtoMapper transactionDtoMapper;
    private final UserEntityMapper userEntityMapper;

    public TransactionController(TransactionService transactionService, UserDataMapper userDataMapper, TransactionDtoMapper transactionDtoMapper, UserEntityMapper userEntityMapper) {
        this.transactionService = transactionService;
        this.userDataMapper = userDataMapper;
        this.transactionDtoMapper = transactionDtoMapper;
        this.userEntityMapper = userEntityMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponseDto prepareTransaction (@AuthenticationPrincipal Jwt jwt){

        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);
        TransactionResponseData transactionResponseData = transactionService.prepareTransaction(firebaseUserData);

        return transactionDtoMapper.toTransactionResponseDto(transactionResponseData);

    }

    @GetMapping("/{tid}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponseDto getTransactionByTid (@PathVariable Integer tid,
                                                        @AuthenticationPrincipal Jwt jwt){
        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);

        TransactionResponseData transactionResponseData = transactionService.getTransactionByTid(firebaseUserData, tid);
        TransactionResponseDto transactionResponseDto = transactionDtoMapper.toTransactionResponseDto(transactionResponseData);
        return transactionResponseDto;

    }

    @PatchMapping("/{tid}")
    @ResponseStatus(HttpStatus.OK)
    public void updateTransactionStatus (@PathVariable Integer tid,
                                         @AuthenticationPrincipal Jwt jwt){
        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);
        transactionService.updateTransactionStatus(firebaseUserData, tid);
    }

    @PatchMapping ("/{tid}/success")
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponseDto successTransaction (@PathVariable Integer tid,
                                                      @AuthenticationPrincipal Jwt jwt){
        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);
        TransactionResponseData transactionResponseData = transactionService.successTransaction (firebaseUserData , tid);
        TransactionResponseDto transactionResponseDto = transactionDtoMapper.toTransactionResponseDto(transactionResponseData);
        return transactionResponseDto;
    }
}
