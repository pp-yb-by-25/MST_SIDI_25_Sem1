package MODULE_1_JAVA.java_tps_object_oriented_programming.java_tp5_managing_files_folders.core;

import java.io.*;

public class MyFile extends FileItem {
    //Attributs
    private static int countFiles=0;
    private String type = "FILE";

    //Constructeur
    public MyFile(String path , String name) throws IOException
    {
        super(path,name);
        if(new File(path + "/" + name).createNewFile())
        {
                countFiles++;
        }
        
    }
    //Getters Setters 
    public static int getCountFiles()
    {
        return MyFile.countFiles;
    }
    public String getType()
    {
        return this.type;
    }

    //Methdoes
    public void writeToFile(String text) throws IOException
    {
        Writer fw = new FileWriter(this.path+"/"+this.name,true);
        fw.write(text);
        fw.close();
    }
    public String readFromFile()
    {
        StringBuilder text = new StringBuilder();
        try
        {
            String line;
            BufferedReader fr = new BufferedReader(new FileReader(this.path));
            while((line = fr.readLine()) != null)
            {
                text.append(line).append("\n");
            }
            fr.close();
            
        }
        catch(Exception e)
        {
            e.getMessage();
        }
        return text;
    }



    //Redefinition des methodes abstarites
    @Override
    public void showInfo()
    {
        String info = "File : " + this.name + " Path : "+ this.path + "Type : " + this.type;
        System.out.println(info);
    }


}
