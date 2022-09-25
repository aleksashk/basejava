package src.ru.javawebinar.storage;

import src.ru.javawebinar.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertResume(Resume r, int index) {
        int indexNewPosition = -index - 1;
        System.arraycopy(storage, indexNewPosition, storage, indexNewPosition + 1, size - indexNewPosition);
        storage[indexNewPosition] = r;
    }

    @Override
    protected void deleteResume(int index) {
        int deletePosition = size - index - 1;
        if (deletePosition > 0) {
            System.arraycopy(storage, index + 1, storage, index, deletePosition);
        }
    }

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
