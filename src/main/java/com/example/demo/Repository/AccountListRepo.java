package com.example.demo.Repository;

import com.example.demo.Entity.AccountList;
import com.example.demo.Entity.RelatedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AccountListRepo extends JpaRepository<AccountList, Long> {
    @Query("select al from AccountList al where al.account.id = :accountId and al.typeList =:type")
    AccountList getAccountListByTypeAndAccountId(@Param("accountId") long accountId ,@Param("type") AccountList.Type type);
}
