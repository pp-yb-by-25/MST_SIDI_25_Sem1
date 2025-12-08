package Project3.Gpt2;

import java.io.File;
import java.io.IOException;

public interface Document {
    public String getTitle();
    public String getAuthor();
    public int getYear();
    public void saveToDisk(File directory) throws IOException;
}





