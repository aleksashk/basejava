package src.ru.javawebinar.storage;

import src.ru.javawebinar.exception.ExistStorageException;
import src.ru.javawebinar.exception.NotExistStorageException;
import src.ru.javawebinar.exception.StorageException;
import src.ru.javawebinar.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int index;

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
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
            throw new NotExistStorageException(r.getUuid());
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

    public final void save(Resume r) {
        index = findIndex(r.getUuid());
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else if (index > 0) {
            throw new ExistStorageException(r.getUuid());

        } else {
            insertResume(r, index);
            size++;
        }
    }

    public final void delete(String uuid) {
        index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }

        deleteResume(index);
        size--;
        storage[size] = null;
    }

    protected abstract void insertResume(Resume r, int index);

    protected abstract void deleteResume(int index);

    protected abstract int findIndex(String uuid);
}