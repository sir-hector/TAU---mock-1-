package Note;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class NotesServiceImplTest {
    MockNotesStorage notesStorage;
    NotesServiceImpl notesService;

    @BeforeEach
    public void setUp() {
        notesStorage = new MockNotesStorage();
        notesService = NotesServiceImpl.createWith(notesStorage);
    }

    @Test
    public void testAverageOf() {

        Note testNote = new Note("John", 4);
        notesService.add(testNote);
        float average = notesService.averageOf("John");
        assertEquals(4.0, average);
    }

    @AfterEach
    public void tearDown() {
        notesStorage.clear();
    }

}