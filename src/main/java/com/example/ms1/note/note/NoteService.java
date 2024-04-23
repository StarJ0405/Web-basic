package com.example.ms1.note.note;

import com.example.ms1.note.notebook.Notebook;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public Note getNote(Long id) {
        return noteRepository.findById(id).get();
    }

    public List<Note> getList() {
        return noteRepository.findAll();
    }

    public Note create(Notebook notebook, String title, String content) {
        Note note = Note.builder().notebook(notebook).title(title).content(content).build();
        return noteRepository.save(note);
    }

    public Note create(Notebook notebook) {
        Note note = Note.builder().notebook(notebook).title("new title..").content("").build();
        return noteRepository.save(note);
    }

    public Note update(Note note, String title, String content) {
        if (title.isBlank())
            title = "제목 없음";
        note.setTitle(title);
        note.setContent(content);
        return noteRepository.save(note);
    }

    public void delete(Note note) {
        noteRepository.delete(note);
    }
}
