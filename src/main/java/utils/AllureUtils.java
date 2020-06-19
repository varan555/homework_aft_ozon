
package utils;

import io.qameta.allure.Attachment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

public class AllureUtils {
    public static File creatFile() {
        File file = new File("target/list");
        Set<String> list = VirtualCart.getPurchase().keySet();
        try {
            FileOutputStream f = new FileOutputStream(file);
            ObjectOutputStream s = new ObjectOutputStream(f);
            s.writeObject(list);
            s.flush();
            s.close();
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
    @Attachment(value = "listPurchase", type = "list/txt", fileExtension = ".txt")
        public static byte[] readBytes() throws IOException {
            return Files.readAllBytes(Path.of("target/list.txt"));

    }
    }

