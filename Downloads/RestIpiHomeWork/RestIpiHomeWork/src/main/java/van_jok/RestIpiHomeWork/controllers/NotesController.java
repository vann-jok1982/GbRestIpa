package van_jok.RestIpiHomeWork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import van_jok.RestIpiHomeWork.models.Notes;
import van_jok.RestIpiHomeWork.servises.NotesService;
import van_jok.RestIpiHomeWork.servises.NotesServiceDecorator;
import van_jok.RestIpiHomeWork.servises.NotesServise;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NotesController {

    @Autowired
    private NotesServiceDecorator notesServiseDecorator;

    @GetMapping
    public List<Notes> printAll() {
        return notesServiseDecorator.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<HttpStatus> addNotes(@RequestBody Notes notes) {
        notesServiseDecorator.save(notes);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Notes notesId(@PathVariable("id") int id) {
        return notesServiseDecorator.findOne(id);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        notesServiseDecorator.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
