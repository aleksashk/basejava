import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        if (size == storage.length) {
            System.out.println("Storage is full");
            return;
        }
        if (resumeChecker(r)) {
            storage[size++] = r;
        } else {
            System.out.println("Resume with a uuid '" + r.uuid + "' is already in the storage.");
        }
    }

    void update(Resume r) {
        if (resumeChecker(r)) {
            System.out.println("Resume with a uuid '" + r.uuid + "' isn't in the storage.");
            return;
        }
        for (int i = 0; i < size; i++) {
            if (Objects.equals(r.uuid, storage[i].uuid)) {
                storage[i] = r;
            }
        }
    }

    private boolean resumeChecker(Resume r) {
        return get(r.uuid) == null;
    }

    private boolean resumeChecker(String uuid) {
        return get(uuid) == null;
    }

    Resume get(String uuid) {
        for (Resume resume : storage) {
            if (null != resume && Objects.equals(uuid, resume.uuid)) {
                return resume;
            }
        }
        return null;
    }

    void delete(String uuid) {
        if (resumeChecker(uuid)) {
            System.out.println("Wrong uuid: " + uuid);
            return;
        }
        for (int i = 0; i < size; i++) {
            if (Objects.equals(storage[i].uuid, uuid)) {
                size--;
                storage[i] = storage[size];
                storage[size] = null;
                break;
            }
        }
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
