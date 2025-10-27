package edu_2.znu.library.store;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * کلاس DataStore برای ذخیره و بارگذاری داده‌ها در فایل
 */
public class DataStore {

    public static <T> void saveList(String filename, List<T> list) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(new ArrayList<>(list));
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> loadList(String filename) throws IOException, ClassNotFoundException {
        File f = new File(filename);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<T>) ois.readObject();
        }
    }
}
