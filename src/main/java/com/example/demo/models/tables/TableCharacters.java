package com.example.demo.models.tables;

import com.example.demo.models.entities.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TableCharacters extends JpaRepository<Character, Integer>{
    @Query(value = "select * from characters where name like 'С%'", nativeQuery = true)
    List<Character> findWhereNameStartsFromC();
}
