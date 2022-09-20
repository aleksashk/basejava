package src.ru.javawebinar.storage;

import src.ru.javawebinar.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected int findIndex(String uuid) {
        for (int index = 0; index < size; index++) {
            if (storage[index].getUuid().equals(uuid)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    protected void correctArrayAfterAddElement(Resume r, int index) {
        storage[size] = r;
    }

    @Override
    protected void correctArrayAfterDeleteElement(int index) {
        storage[index] = storage[size - 1];
    }
}