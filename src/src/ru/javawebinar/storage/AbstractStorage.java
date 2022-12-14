package src.ru.javawebinar.storage;

import src.ru.javawebinar.exception.ExistStorageException;
import src.ru.javawebinar.exception.NotExistStorageException;
import src.ru.javawebinar.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    private static final Comparator<Resume> COMPARATOR_FULL_NAME_THEN_UUID = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    public void update(Resume r) {
        Object searchKey = getExistedSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    public void save(Resume r) {
        Object searchKey = getNotExistedSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    private Object getExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = getResumeList();
        list.sort(COMPARATOR_FULL_NAME_THEN_UUID);
        return list;
    }

    protected abstract List<Resume> getResumeList();
}