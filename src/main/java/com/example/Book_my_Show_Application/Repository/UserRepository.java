package com.example.Book_my_Show_Application.Repository;

import com.example.Book_my_Show_Application.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer>
{

}
