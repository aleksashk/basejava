import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
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
        int index = 0;
        for (int i = 0; i < storage.length; i++) {
            if (Objects.equals(storage[i].uuid, uuid)) {
                index = i;
                break;
            }
        }
        Resume[] tmpArrayBefore = Arrays.copyOfRange(storage, 0, index);
        Resume[] tmpArrayAfter = Arrays.copyOfRange(storage, index + 1, storage.length);
        System.arraycopy(tmpArrayBefore, 0, storage, 0, tmpArrayBefore.length);
        System.arraycopy(tmpArrayAfter, 0, storage, index, tmpArrayBefore.length);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int index;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                index = i;
                Resume[] result = new Resume[index];
                System.arraycopy(storage, 0, result, 0, result.length);
                return result;
            }
        }
        return new Resume[0];
    }

    int size() {
        return getAll().length;
    }
}
