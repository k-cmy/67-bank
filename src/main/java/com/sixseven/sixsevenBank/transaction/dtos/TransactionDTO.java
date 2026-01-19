package com.sixseven.sixsevenBank.transaction.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sixseven.sixsevenBank.account.dtos.AccountDTO;
import com.sixseven.sixsevenBank.enums.TransactionStatus;
import com.sixseven.sixsevenBank.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//control how Java Obj is converted into the text (JSON) that React can read
@JsonInclude(JsonInclude.Include.NON_NULL) // if a field is empty , it is removed from the JSON entirely, makes the data package smaller & faster to send.
@JsonIgnoreProperties(ignoreUnknown = true) // if React sends back more data than we asked for , Spring Boot will just ignore the extra stuff instead of crashing

public class TransactionDTO {
    private Long id;

    private BigDecimal amount;

    private TransactionType transactionType;

    private LocalDateTime transactionDate;

    private String description;

    private TransactionStatus status;

    @JsonBackReference
    private AccountDTO account;

    //for transfer
    private String sourceAccount;
    private String destinationAccount;
}
