package com.example.ms1.note.note;

import com.example.ms1.note.notebook.Notebook;
import com.example.ms1.note.notebook.NotebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;
    private final NotebookService notebookService;

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    @RequestMapping("/")
    public String main(Model model) {
        List<Notebook> notebookList = notebookService.getList();
        if (notebookList.isEmpty())
            notebookList.add(notebookService.create());
        Notebook notebook = notebookList.getFirst();
        List<Note> noteList = notebook.getNoteList();
        if (noteList.isEmpty())
            noteList.add(noteService.create(notebook));
        model.addAttribute("notebookList", notebookList);
        model.addAttribute("targetNotebook", notebook);
        model.addAttribute("noteList", noteList);
        model.addAttribute("targetNote", noteList.getFirst());

        return "main";
    }

    @PostMapping("/write")
    public String writeNotebook() {
        Notebook notebook = notebookService.create();
        return "redirect:/detail/" + notebook.getId();
    }

    @PostMapping("/write/{notebook_id}")
    public String writeNote(@PathVariable Long notebook_id) {
        Notebook notebook = notebookService.getNotebook(notebook_id);
        Note note = noteService.create(notebook);
        return "redirect:/detail/" + notebook_id + "/" + note.getId();
    }
    @PostMapping("/write/{notebook_id}/sub")
    public String writeSubNotebook(@PathVariable Long notebook_id) {
        Notebook notebook = notebookService.getNotebook(notebook_id);
        Notebook subNotebook = notebookService.createSubNotebook(notebook);
        return "redirect:/detail/" + subNotebook.getId() ;
    }

    @GetMapping("/detail/{notebook_id}")
    public String detailNotebook(Model model, @PathVariable Long notebook_id) {
        Notebook notebook = notebookService.getNotebook(notebook_id);
        List<Note> noteList = notebook.getNoteList();
        if (noteList.isEmpty())
            noteList.add(noteService.create(notebook));
        model.addAttribute("targetNotebook", notebook);
        model.addAttribute("notebookList", notebookService.getList());
        model.addAttribute("targetNote", noteList.getFirst());
        model.addAttribute("noteList", noteList);
        return "main";
    }

    @GetMapping("/detail/{notebook_id}/{note_id}")
    public String detailNote(Model model, @PathVariable Long notebook_id, @PathVariable Long note_id) {
        Notebook notebook = notebookService.getNotebook(notebook_id);
        Note note = noteService.getNote(note_id);
        model.addAttribute("targetNotebook", notebook);
        model.addAttribute("notebookList", notebookService.getList());
        model.addAttribute("targetNote", note);
        model.addAttribute("noteList", notebook.getNoteList());

        return "main";
    }

    @PostMapping("/update")
    public String update(Long id, String title, String content) {
        Note note = noteService.getNote(id);
        noteService.update(note, title, content);
        return "redirect:/detail/" + note.getNotebook().getId() + "/" + id;
    }

    @PostMapping("/delete/{note_id}")
    public String delete(@PathVariable Long note_id) {
        Note note = noteService.getNote(note_id);
        Long notebook_id = note.getNotebook().getId();
        noteService.delete(note);
        return "redirect:/detail/" + notebook_id;
    }
}
