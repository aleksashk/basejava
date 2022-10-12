package src.ru.javawebinar.storage;

import src.ru.javawebinar.exception.ExistStorageException;
import src.ru.javawebinar.exception.NotExistStorageException;
import src.ru.javawebinar.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);

    protected abstract void updateResume(Resume r, Object searchKey);

    protected abstract void saveResume(Resume r, Object searchKey);

    protected abstract void deleteResume(Object searchKey);

    protected abstract Resume getResume(Object searchKey);

    public void update(Resume r) {
        Object searchKey = getExistedSearchKey(r.getUuid());
        updateResume(r, searchKey);
    }

    public void save(Resume r) {
        Object searchKey = getNotExistedSearchKey(r.getUuid());
        saveResume(r, searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        deleteResume(searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        return getResume(searchKey);
    }

    private Object getExistedSearchKey(String uuid) {
        Object searchObjectKey = getSearchKey(uuid);
        if (!isExist(searchObjectKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchObjectKey;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object searchObjectKey = getSearchKey(uuid);
        if (isExist(searchObjectKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchObjectKey;
    }
}
