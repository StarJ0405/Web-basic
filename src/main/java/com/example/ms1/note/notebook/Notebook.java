package com.example.ms1.note.notebook;

import com.example.ms1.note.note.Note;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Notebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "notebook", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> noteList;
    @OneToMany(mappedBy = "notebook", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notebook> noteBookList;
    @ManyToOne(fetch = FetchType.LAZY)
    private Notebook notebook;

    @Builder
    public Notebook(String name, Notebook notebook) {
        this.name = name;
        this.notebook = notebook;
        this.noteList = new ArrayList<>();
        this.noteBookList = new ArrayList<>();
    }
}
