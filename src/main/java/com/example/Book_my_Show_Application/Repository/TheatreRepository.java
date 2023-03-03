package com.example.Book_my_Show_Application.Repository;

import com.example.Book_my_Show_Application.Entities.TheatreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends JpaRepository<TheatreEntity,Integer>
{

}
