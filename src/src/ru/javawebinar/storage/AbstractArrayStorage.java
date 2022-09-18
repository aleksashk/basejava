package src.ru.javawebinar.storage;

import src.ru.javawebinar.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int index;

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
        }
        return storage[index];
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index == -1) {
            System.out.println("Wrong index: " + index);
        } else {
            storage[index] = r;
        }
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract int findIndex(String uuid);

    public abstract void save(Resume r);

    public abstract void delete(String uuid);

}
