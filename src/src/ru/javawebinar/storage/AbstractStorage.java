package src.ru.javawebinar.storage;

import src.ru.javawebinar.exception.ExistStorageException;
import src.ru.javawebinar.exception.NotExistStorageException;
import src.ru.javawebinar.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean exist(Object searchKey);

    protected abstract void updateResume(Resume r, Object searchKey);

    public void update(Resume r) {
        Object searchKey = getExistedSearchKey(r.getUuid());
        updateResume(r, searchKey);
    }

    protected abstract void saveResume(Resume r, Object searchKey);

    public void save(Resume r) {
        Object searchKey = getNotExistedSearchKey(r.getUuid());
        saveResume(r, searchKey);
    }

    protected abstract void deleteResume(Object searchKey);

    public void delete(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        deleteResume(searchKey);
    }

    protected abstract Resume getResume(Object searchKey);

    public Resume get(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        return getResume(searchKey);
    }

    private Object getExistedSearchKey(String uuid) {
        Object searchObjectKey = getSearchKey(uuid);
        if (!exist(searchObjectKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchObjectKey;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object searchObjectKey = getSearchKey(uuid);
        if (exist(searchObjectKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchObjectKey;
    }
}
