import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;
    private int index;

    private int findIndex(String uuid) {
        for (int index = 0; index < size; index++) {
            if (storage[index].uuid.equals(uuid)) {
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
        } else if ((index = findIndex(r.uuid)) != -1) {
            System.out.println("Resume with a uuid '" + r.uuid + "' is already in the storage.");
        } else {
            storage[size++] = r;
        }
    }

    public void update(Resume r, int index) {
        if (index == -1 || index >= size) {
            System.out.println("Wrong index: " + index);
            return;
        }
        storage[index] = r;
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

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        return null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
