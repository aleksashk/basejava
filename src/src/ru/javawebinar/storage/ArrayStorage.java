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

    public void save(Resume r) {
        if (size == STORAGE_LIMIT) {
            System.out.println("Storage is full");
        } else if ((index = findIndex(r.getUuid())) != -1) {
            System.out.println("Resume with a uuid '" + r.getUuid() + "' is already in the storage.");
        } else {
            storage[size++] = r;
        }
    }

    public void delete(String uuid) {
        index = findIndex(uuid);
        if (index == -1) {
            System.out.println("Wrong uuid: " + uuid);
            return;
        }

        size--;
        storage[index] = storage[size];
        storage[size] = null;
    }
}