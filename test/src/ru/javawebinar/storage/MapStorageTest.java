package src.ru.javawebinar.storage;

import org.junit.Test;
import src.ru.javawebinar.model.Resume;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class MapStorageTest extends AbstractStorageTest {

    private static final Resume RESUME_1 = new Resume("uuid_1");
    private static final Resume RESUME_2 = new Resume("uuid_2");

    public MapStorageTest() {
        super(new MapStorage());
    }

    @Test
    public void save() throws Exception {
        clear();
        storage.save(RESUME_1);
        assertArrayEquals(new Resume[]{RESUME_1}, storage.getAll());
    }

    @Test
    public void getAll() throws Exception {
        clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        Resume[] resumes = storage.getAll();
        Arrays.sort(resumes);
        assertArrayEquals(new Resume[]{RESUME_1, RESUME_2}, resumes);
    }
}