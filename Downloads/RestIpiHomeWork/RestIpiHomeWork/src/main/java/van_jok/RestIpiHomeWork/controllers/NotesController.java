package van_jok.RestIpiHomeWork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import van_jok.RestIpiHomeWork.models.Notes;
import van_jok.RestIpiHomeWork.repositories.NotesRepositrory;
import van_jok.RestIpiHomeWork.servises.NotesServise;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NotesController {
    @Autowired
    NotesServise notesServise;
    @GetMapping
    public List<Notes> printAll(){
        return notesServise.findAll();
    }
    @PostMapping("/save")
    public ResponseEntity<HttpStatus> addNotes(@RequestBody Notes notes) {
        notesServise.save(notes);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public Notes notesId(@PathVariable("id") int id){// заметки  по id
        return notesServise.findOne(id);
    }
    @GetMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        notesServise.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
