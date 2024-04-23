package com.example.ms1.note.note;

import com.example.ms1.note.notebook.Notebook;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String title;
    private String content;
    @Setter(AccessLevel.NONE)
    private LocalDateTime createDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private Notebook notebook;

    @Builder
    public Note(String title, String content, Notebook notebook) {
        this.title = title;
        this.content = content;
        this.notebook = notebook;
        this.createDate = LocalDateTime.now();
    }
}