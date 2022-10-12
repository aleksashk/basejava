package src.ru.javawebinar.storage;

import src.ru.javawebinar.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> map = new HashMap<>();

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return map.containsKey((String) searchKey);
    }

    @Override
    protected void updateResume(Resume r, Object searchKey) {
        map.put((String) searchKey, r);
    }

    @Override
    protected void saveResume(Resume r, Object searchKey) {
        map.put((String) searchKey, r);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        map.remove((String)searchKey);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return map.get((String)searchKey);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Resume[] getAll() {
        return map.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return map.size();
    }
}
