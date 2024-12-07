package van_jok.RestIpiHomeWork;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import van_jok.RestIpiHomeWork.models.Notes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// Мы используем mockMvc, чтобы отправить POST-запрос на URL /notes/save
// и проверить статус ответа ОК (200), что означает, что наша заметка успешно сохранена в базе данных.
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransferTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddNotes() throws Exception {
        Notes newNote = new Notes();
        newNote.setNote("New note");
        newNote.setData("New data");

        this.mockMvc.perform(post("/notes/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newNote)))
                .andExpect(status().isOk());
    }
}