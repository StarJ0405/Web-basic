package com.example.ms1.note.notebook;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotebookService {
    private final NotebookRepository notebookRepository;

    public List<Notebook> getList() {
        return notebookRepository.findAll();
    }

    public Notebook getNotebook(Long id) {
        return notebookRepository.findById(id).get();
    }

    public Notebook create() {
        Notebook notebook = Notebook.builder().name("새노트").build();
        return notebookRepository.save(notebook);
    }

    public Notebook createSubNotebook(Notebook notebook) {
        while (notebook.getNotebook() != null)
            notebook = notebook.getNotebook();
        Notebook subNotebook = Notebook.builder().name("새하위노트").notebook(notebook).build();
        return notebookRepository.save(subNotebook);
    }
}
