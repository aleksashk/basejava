package src.ru.javawebinar.storage;

import org.junit.Assert;
import org.junit.Test;
import src.ru.javawebinar.exception.StorageException;
import src.ru.javawebinar.model.Resume;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest{

    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    //Очистили хранилище и сохраняем максимально-возможное количество Resume в storage, потом пробуем
    //добавить еще одно Resume. Логика реализации теста на переполнение массива (StorageException):
    //заполняем массив, но не вызываем у него переполнение
    //если при заполнении вылетит исключение, то тест должен провалиться (используйте Assert.fail())
    //в fail() выводите сообщение о том, что переполнение произошло раньше времени
    //тест считается успешно пройденным, когда переполнение происходит при попытке добавить в
    // полностью заполненный массив еще одно резюме
    @Test(expected = StorageException.class)
    public void saveWithStorageException() throws Exception {
        clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume());
    }
}