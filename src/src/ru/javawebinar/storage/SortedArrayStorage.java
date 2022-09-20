package src.ru.javawebinar.storage;

import src.ru.javawebinar.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    public void save(Resume r) {
        index = findIndex(r.getUuid());
        if (size == STORAGE_LIMIT) {
            System.out.println("Storage is full");
        } else if (index >= 0) {
            System.out.println("Resume with a uuid '" + r.getUuid() + "' is already in the storage.");
        } else {
            index = (index + 1) * (-1);

            System.arraycopy(storage, index, storage, index + 1, size - index);

            storage[index] = r;
            size++;
        }
    }

    public void delete(String uuid) {
        index = findIndex(uuid);
        if (index < 0) {
            System.out.println("Wrong uuid: " + uuid);
            return;
        }

        index = findIndex(uuid);
        int deletePosition = size - index - 1;
        if (deletePosition > 0) {
            System.arraycopy(storage, index + 1, storage, index, deletePosition);
        }

        size--;
        storage[size] = null;
    }

    @Override
    protected void correctArrayAfterAddElement(Resume r) {
        index = (index + 1) * (-1);
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = r;
    }

    @Override
    protected void correctArrayAfterDeleteElement(int index) {
        int deletePosition = size - index - 1;
        if (deletePosition > 0) {
            System.arraycopy(storage, index + 1, storage, index, deletePosition);
        }
    }

    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
