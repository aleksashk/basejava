package src.ru.javawebinar.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import src.ru.javawebinar.exception.ExistStorageException;
import src.ru.javawebinar.exception.NotExistStorageException;
import src.ru.javawebinar.exception.StorageException;
import src.ru.javawebinar.model.Resume;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public abstract class AbstractArrayStorageTest {
    public static final String UUID_NOT_EXIST = "dummy";
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final Resume RESUME_4 = new Resume(UUID_4);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void get() throws Exception {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test
    public void getFail() throws Exception {
        assertNotEquals(RESUME_4, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_NOT_EXIST);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test(expected = NotExistStorageException.class)
    public void update() {
        storage.update(new Resume("UUID_4"));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("UUID_5"));
    }

    @Test
    public void getAll() throws Exception {
        assertArrayEquals(new Resume[]{RESUME_1, RESUME_2, RESUME_3}, storage.getAll());
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    @Test
    public void getSize() throws Exception {
        assertSize(3);
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME_4);
        assertArrayEquals(new Resume[]{RESUME_1, RESUME_2, RESUME_3, RESUME_4}, storage.getAll());
    }

    @Test(expected = ExistStorageException.class)
    public void saveWithExistStorageException() {
        storage.save(RESUME_3);
    }

    //сохраняем максимально-возможное количество Resume в storage и потом пробуем добавить еще одно Resume
    //логика реализации теста на переполнение массива (StorageException):
    //заполняем массив, но не вызываем у него переполнение
    //если при заполнении вылетит исключение, то тест должен провалиться (используйте Assert.fail())
    //в fail() выводите сообщение о том, что переполнение произошло раньше времени
    //тест считается успешно пройденным, когда переполнение происходит при попытке добавить в полностью заполненный массив еще одно резюме
    @Test(expected = StorageException.class)
    public void saveWithStorageException() throws Exception {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("overflow occurred ahead of time");
        }
        storage.save(new Resume());
    }

    @Test
    public void delete() throws Exception {
        storage.save(RESUME_4);
        storage.delete(UUID_4);
        assertArrayEquals(new Resume[]{RESUME_1, RESUME_2, RESUME_3}, storage.getAll());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNoExist() {
        storage.delete(UUID_4);
    }
}