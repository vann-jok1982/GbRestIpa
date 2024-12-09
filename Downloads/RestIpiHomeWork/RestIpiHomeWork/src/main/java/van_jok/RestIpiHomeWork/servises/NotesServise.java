package van_jok.RestIpiHomeWork.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import van_jok.RestIpiHomeWork.models.Notes;
import van_jok.RestIpiHomeWork.repositories.NotesRepositrory;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class NotesServise implements NotesService {
    private final NotesRepositrory notesRepositrory;
    @Autowired
    public NotesServise(NotesRepositrory notesRepositrory) {
        this.notesRepositrory = notesRepositrory;
    }
    public List<Notes> findAll(){ // Считывание данных с БД
        return notesRepositrory.findAll();
    }
    public Notes findOne(int id){ // Поиск по ID
        Optional<Notes> foundSpisokZadach= notesRepositrory.findById(id);
        return foundSpisokZadach.orElse(null);
    }
    @Transactional
    public void save(Notes notes){ // Сщхранение в БД
        notesRepositrory.save(notes);
    }
    @Transactional
    public void update(int id, Notes updateNotes){ // Изменения в БД
        updateNotes.setId(id);
        notesRepositrory.save(updateNotes);
    }
    @Transactional
    public void delete(int id){ //удаление по ID
        notesRepositrory.deleteById(id);
    } //Удаление из БД
}
