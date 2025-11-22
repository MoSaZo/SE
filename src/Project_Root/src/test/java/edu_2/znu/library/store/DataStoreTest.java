package edu_2.znu.library.store;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DataStoreTest {

    @Test
    void saveAndLoadList() throws Exception {
        String tmp = "test_datastore.tmp";
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");

        DataStore.saveList(tmp, list);
        List<String> loaded = DataStore.loadList(tmp);
        assertEquals(2, loaded.size());
        assertTrue(loaded.contains("a"));
        // cleanup
        new File(tmp).delete();
    }
}
