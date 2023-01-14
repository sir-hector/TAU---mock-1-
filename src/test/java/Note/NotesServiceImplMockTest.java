package Note;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.easymock.EasyMock.createMock;
import static org.junit.jupiter.api.Assertions.*;

public class NotesServiceImplMockTest {
    private final NotesStorage storage = createMock(NotesStorage.class);
    private final NotesServiceImpl service = NotesServiceImpl.createWith(storage);

    @Test
    public void test_instance_car(){
        assertTrue(storage instanceof NotesStorage);
    }

    @Test
    public void testAdd() {
        Note note = new Note("John", 80);
        storage.add(note);
        EasyMock.expectLastCall();
        EasyMock.replay(storage);
        service.add(note);
        EasyMock.verify(storage);
    }

    @Test
    public void testAverageOf() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note("John", 80));
        notes.add(new Note("John", 90));
        EasyMock.expect(storage.getAllNotesOf("John")).andReturn(notes);
        EasyMock.replay(storage);
        assertEquals(85.0f, service.averageOf("John"), 0.1f);
        EasyMock.verify(storage);
    }

    @Test
    public void testClear() {
        storage.clear();
        EasyMock.expectLastCall();
        EasyMock.replay(storage);
        service.clear();
        EasyMock.verify(storage);
    }

    @Test
    public void testAverageOfNoNotes() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note("John", 0.0f));
        EasyMock.expect(storage.getAllNotesOf("John")).andReturn(notes);
        EasyMock.replay(storage);
        assertEquals(0.0f, service.averageOf("John"), 0.1f);
        EasyMock.verify(storage);
    }

    @Test
    public void test_exception(){
        EasyMock.expect(storage.getAllNotesOf("note")).andThrow(new RuntimeException());
        EasyMock.replay(storage);
        assertThrows(RuntimeException.class, () -> {
            storage.getAllNotesOf("note");
        });
    }
}
