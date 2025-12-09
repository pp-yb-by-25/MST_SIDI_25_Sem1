package MODULE_1_JAVA.java_tps_object_oriented_programming.java_tp2_inheritance_digital_documents;

import java.io.File;
import java.io.IOException;

public interface Document {
    public String getTitle();
    public String getAuthor();
    public int getYear();
    public void saveToDisk(File directory) throws IOException;
}





