package MODULE_1_JAVA.java_tps_object_oriented_programming.java_tp5_managing_files_folders.core;

import java.io.File;

public class MyDirectory extends FileItem {
    //Attributs 
    private static int counterDir=0;
    private String type = "Directory";
    //Constructeur

    public MyDirectory(String path , String name)
    {
        super(path,name);
        counterDir++;
    }
    //Getters and Setters
    public String getType()
    {
        return this.type;
    }
    public static int getCounterDir()
    {
        return counterDir;
    }
    //Methodes
    public void createDir()
    {
        new File(path).mkdir();
    }
    public void listFiles()
    {
        String info = "";
        File[] lsf = new File(path).listFiles();
        for(File f : lsf)
        {
            info = "file "+f.getName()+" Path :"+f.getPath()+ " size : "+ f.length() + "/n";
        }
        System.out.println(info);
    }

    //Methodes abstraites
    @Override
    public void showInfo()
    {
        String info = "File : " + this.name + " Path : "+ this.path + "Type : " + this.type;
        System.out.println(info);
    }
}
