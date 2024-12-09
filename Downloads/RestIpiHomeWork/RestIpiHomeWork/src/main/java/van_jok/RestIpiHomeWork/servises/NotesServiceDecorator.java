package van_jok.RestIpiHomeWork.servises;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import van_jok.RestIpiHomeWork.models.Notes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
public class NotesServiceDecorator implements NotesService {

    private static final Logger logger = LoggerFactory.getLogger(NotesServiceDecorator.class);

    private final NotesServise notesService;

    @Autowired
    public NotesServiceDecorator(NotesServise notesService) {
        this.notesService = notesService;
    }

    @Override
    public List<Notes> findAll() {
        return notesService.findAll();
    }

    @Override
    public Notes findOne(int id) {
        return notesService.findOne(id);
    }

    @Override
    @Transactional
    public void save(Notes notes) {
        notesService.save(notes);
        logger.info("Added note: {}", notes.getNote());
    }

    @Override
    @Transactional
    public void update(int id, Notes updateNotes) {
        notesService.update(id, updateNotes);
        logger.info("Updated note with ID: {}", id);
    }

    @Override
    @Transactional
    public void delete(int id) {
        notesService.delete(id);
        logger.info("Deleted note with ID: {}", id);
    }


    private void logAction(String message) {
        // Здесь можно добавить логику логирования, например, в файл или базу данных
        System.out.println("Log: " + message);
    }
}