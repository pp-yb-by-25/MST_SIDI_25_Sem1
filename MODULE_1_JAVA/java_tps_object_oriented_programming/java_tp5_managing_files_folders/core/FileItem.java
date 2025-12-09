package MODULE_1_JAVA.java_tps_object_oriented_programming.java_tp5_managing_files_folders.core;

public abstract class FileItem {
    //Attributs
    protected String path;
    public final String name;

    //Constructeur
    public FileItem(String path, String name)
    {
        this.path = path;
        this.name = name;
    }
    //Getters Setters
    public String getPath()
    {
        return this.path;
    }
    public void setPath(String path)
    {
        this.path = path;
    }
    //Methodes



    // Methodes abstraites
    public abstract void showInfo();
}
