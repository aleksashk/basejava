import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    private int findIndex(String uuid) {
        for (int i = 0; i < STORAGE_LIMIT; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
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
            return;
        }
        if (findIndex(r.uuid) != -1) {
            System.out.println("Resume with a uuid '" + r.uuid + "' is already in the storage.");
            return;
        }
        storage[size++] = r;
    }

    public void update(Resume r) {
        int index = findIndex(r.uuid);
        if (index == -1) {
            System.out.println("Resume with a uuid '" + r.uuid + "' isn't in the storage.");
            return;
        }
        storage[index] = r;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index == -1) {
            System.out.println("Wrong uuid: " + uuid);
            return;
        }

        size--;
        storage[index] = storage[size];
        storage[size] = null;
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
