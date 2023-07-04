package com.example.demo.Repository;

import com.example.demo.Dto.DayDto;
import com.example.demo.Dto.EntityDto.AccountDto;
import com.example.demo.Dto.EntityDto.AccountFullNameDto;
import com.example.demo.Entity.Account;
import com.example.demo.Entity.Content;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {
    Account findByUsername(String username);

    @Query(value = "SELECT * FROM account WHERE account.username= :name", nativeQuery = true)
    Account findByAccountName(@Param("name") String name);

    @Query("SELECT e FROM Account e WHERE id =:id")
    Account findAccountById(@Param("id") long id);

    @Query("SELECT new com.example.demo.Dto.EntityDto.AccountFullNameDto(" +
            "concat(e.firstName,' ', e.lastName),e.email) FROM Account e WHERE e.dateOfBirth= :dateOfBirth")
    List<AccountFullNameDto> findAccountFullNameByDateOfBirth(@Param("dateOfBirth") Date dateOfBirth);

    @Query("SELECT e FROM Account e " +
            "WHERE EXTRACT(DAY FROM e.dateOfBirth) = :day " +
            "AND EXTRACT(MONTH FROM e.dateOfBirth) = :month")
    List<Account> findAccountByDayOfBirth(@Param("day") int day, @Param("month") int month);

    @Query("SELECT new com.example.demo.Dto.EntityDto.AccountDto(e.id,e.email, e.firstName, e.lastName) FROM Account e " +
            "WHERE EXTRACT(DAY FROM e.dateOfBirth) = :day " +
            "AND EXTRACT(MONTH FROM e.dateOfBirth) = :month")
    List<AccountDto> findAccountDtoByDayOfBirth(@Param("day") int day, @Param("month") int month);

    List<Account> findAccountByAccountNote(String note);

    @Query("SELECT e FROM Account e WHERE e.role = ?1")
    List<Account> findAccountByRole(Account.Role role);
    @Query("select e.id from Account e where e.username =:username")
    long findIdbyuserName(@Param("username") String username);

}
