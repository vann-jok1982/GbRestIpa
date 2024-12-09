package van_jok.RestIpiHomeWork.servises;

import van_jok.RestIpiHomeWork.models.Notes;

import java.util.List;

public interface NotesService {
    List<Notes> findAll();
    Notes findOne(int id);
    void save(Notes notes);
    void update(int id, Notes updateNotes);
    void delete(int id);
}