import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        Arrays.fill(storage,0, size, null);
        size = 0;
    }

    void save(Resume r) {
        storage[size++] = r;
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
                size--;
                break;
            }
        }
        System.arraycopy(Arrays.copyOfRange(storage, 0, index), 0, storage, 0, Arrays.copyOfRange(storage, 0, index).length);
        System.arraycopy(Arrays.copyOfRange(storage, index + 1, storage.length), 0, storage, index, Arrays.copyOfRange(storage, index + 1, storage.length).length);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] result = new Resume[size];
        System.arraycopy(storage, 0, result, 0, size);
        return result;
    }

    int size() {
        return size;
    }
}
