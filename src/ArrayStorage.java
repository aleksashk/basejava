import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    protected final Resume[] storage = new Resume[10000];
    private int size = 0;

    public int findIndex(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        if (size == storage.length) {
            System.out.println("Storage is full");
            return;
        }
        if (findIndex(r.uuid) != -1) {
            System.out.println("Resume with a uuid '" + r.uuid + "' is already in the storage.");
            return;
        }
        storage[size++] = r;
    }

    void update(Resume r) {
        int index = findIndex(r.uuid);
        if (index == -1) {
            System.out.println("Resume with a uuid '" + r.uuid + "' isn't in the storage.");
            return;
        }
        storage[index] = r;
    }

    void delete(String uuid) {
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
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
