package src.ru.javawebinar.storage;

import src.ru.javawebinar.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    private static final int STORAGE_LIMIT = 10000;

    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    private int index;

    private int findIndex(String uuid) {
        for (int index = 0; index < size; index++) {
            if (storage[index].getUuid().equals(uuid)) {
                return index;
            }
        }
        return -1;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
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

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index == -1) {
            System.out.println("Wrong index: " + index);
        } else {
            storage[index] = r;
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

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }
}
