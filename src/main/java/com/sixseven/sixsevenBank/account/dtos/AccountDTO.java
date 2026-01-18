package com.sixseven.sixsevenBank.account.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sixseven.sixsevenBank.auth_users.dtos.UserDTO;
import com.sixseven.sixsevenBank.enums.AccountStatus;
import com.sixseven.sixsevenBank.enums.AccountType;
import com.sixseven.sixsevenBank.enums.Currency;
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
@JsonIgnoreProperties(ignoreUnknown = true)

public class AccountDTO {

    private Long id;

    private String accountNumber;

    private BigDecimal balance;

    private AccountType accountType;

    @JsonBackReference// this will not be added to the account dto. It will be ignored because it is a back reference
    private UserDTO user;

    private Currency currency;

    private AccountStatus status;

    @JsonManagedReference// it helps avoid recursion loop by ignoring the AccountDTO withing the TransactionDTO
    private List<TransactionDTO> transactions;

    private LocalDateTime closedAt;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
