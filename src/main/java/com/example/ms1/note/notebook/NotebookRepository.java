package com.example.ms1.note.notebook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotebookRepository extends JpaRepository<Notebook,Long> {
    @Override
    @Query("select nr " +
            "from Notebook nr " +
            "where nr.notebook is null")
    List<Notebook> findAll();
}
