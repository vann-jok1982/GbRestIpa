package van_jok.RestIpiHomeWork;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import van_jok.RestIpiHomeWork.controllers.NotesController;
import van_jok.RestIpiHomeWork.models.Notes;
import van_jok.RestIpiHomeWork.repositories.NotesRepositrory;
import van_jok.RestIpiHomeWork.servises.NotesServise;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class RestIpiHomeWorkApplicationTests {
	@Mock
	public NotesServise notesServise;

	@InjectMocks
	public NotesController notesController;
	@Test
	void printAllTest() {
		List<Notes> notesList = new ArrayList<>();
		notesList.add(new Notes(1, "Note 1", "Data 1"));
		notesList.add(new Notes(2, "Note 2", "Data 2"));
		when(notesServise.findAll()).thenReturn(notesList);

		List<Notes> result = notesController.printAll();

		assertEquals(2, result.size());
		assertEquals("Note 1", result.get(0).getNote());
		assertEquals("Data 1", result.get(0).getData());
		assertEquals("Note 2", result.get(1).getNote());
		assertEquals("Data 2", result.get(1).getData());
	}

}
