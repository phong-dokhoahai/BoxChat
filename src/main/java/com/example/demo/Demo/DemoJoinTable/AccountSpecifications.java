package com.example.demo.Demo.DemoJoinTable;

import com.example.demo.Entity.Account;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpecifications {
    public static Specification<Account> hasFirstNameLike(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.<String>get("firstName"), "%" + name + "%");
    }
    public static Specification<Account> hasLastName(String name) {
        return (root, query, cb) ->
                cb.equal(root.<String>get("lastName"), name);
    }
}
