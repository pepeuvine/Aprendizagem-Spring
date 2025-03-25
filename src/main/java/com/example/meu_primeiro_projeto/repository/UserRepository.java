package com.example.meu_primeiro_projeto.repository;

import com.example.meu_primeiro_projeto.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
