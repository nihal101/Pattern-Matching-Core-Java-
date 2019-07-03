
package project;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Project1 {
    
    
    
  
    static String keyword;
    static int average=0;
    ArrayList  fileLength;
    ArrayList fileName;
    ArrayList resultPageName;
    ArrayList folderName;
    int totalFolder;

    Project1()
    {
        fileName=new ArrayList();
        resultPageName=new ArrayList();
        folderName=new ArrayList();
        fileLength=new ArrayList();
    }
    
   public void openFolder() throws IOException
   {
       String[] folder={"F://java","F://machine learning","F://blockchain","F://data science"};
       totalFolder=folder.length;
       for(int i=0;i<totalFolder;i++)
       {
           folderName.add(folder[i]);
           File subFolder=new File(folder[i]);  
           keyword=subFolder.getName().toLowerCase();
           resultPageName.add("F:\\resultPage"+keyword+".txt");
           openFile(subFolder);
       }
   }
   
   public void openFile(File file) throws IOException
   {
      
       File[] fileArray=file.listFiles();
       fileLength.add(fileArray.length);
       for(int i=0;i<fileArray.length;i++)
           readFile(fileArray[i]);
       writeAverage(average,fileArray.length,file.getName());
   }
   
   public void readFile(File file)
   {
       int i=0,j=0;
       char ch;
       int count1=0,count2=0;
       
      
     try
     {
           BufferedReader read=new BufferedReader(new FileReader(file));
           
           while((i=read.read())!=-1)
           {
               ch=convertLowerCase((char)i);
               
               if(ch==keyword.charAt(j))
               {
                   count1++;
                   j++;
               }else
               {
                   count1=0;
                   j=0;
               }
               
               
               
              if(count1==keyword.length())
               {
                   count1=0;
                   j=0;
                   count2++;
               }
               
           }
           average=average+count2;
         // System.out.println(count2); 
     }catch(FileNotFoundException e)
     {
         System.out.println("file not found");
     }catch(IOException e)
             {
                 System.out.println("IOException");
            }
     fileName.add(file.getName());
     createResultFile(count2,file.getName());
   }
   
  
   public void createResultFile(int count,String page)
   {
       String space="                  ";
       String lineChange="\r\n\r\n";
       
       try
       {
           
           FileOutputStream fin=new FileOutputStream("F:\\resultPage"+keyword+".txt",true);           
           byte[] pageByte=page.getBytes();
           fin.write(pageByte);
           pageByte=space.getBytes();
           fin.write(pageByte);
           fin.write(("-"+String.valueOf(count)+"-").getBytes());
           pageByte=lineChange.getBytes();
           fin.write(pageByte);
           fin.close();
         
           
       }catch(FileNotFoundException e)
       {
           System.out.println("file not found");
       }catch(IOException e)
       {
           
       }
       
   }
   
   public Character convertLowerCase(char c)
   {
       Character ch=c;
       String str=ch.toString().toLowerCase();
       return str.charAt(0);
   }

   public void writeAverage(int average, int fileLength, String folderName) throws FileNotFoundException, IOException
   {
       String space="\t\t\t";
       String lineChange="\r\n\r\n";
       FileOutputStream write=new FileOutputStream("F://averagePage.txt",true);
       
       write.write(folderName.getBytes());
       write.write(space.getBytes());
       write.write(("-"+String.valueOf(average/(float)fileLength)+"-").getBytes());
       write.write(lineChange.getBytes());
   }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        Project1 project=new Project1();
        project.openFolder();
      
        System.out.println("Enter choice");
        System.out.println("1.Graph For Each Keyword"+"\r\n"+"2.Averge Graph For All Keyword"+"\r\n"+"3.Exit"); 
        Scanner sc=new Scanner(System.in);
        
        
       while(true)
       {
           int ch=sc.nextInt();
            switch(ch)
        {
            case 1:
                 new showGraph(project.fileName,project.folderName,project.resultPageName,project.fileLength);
                 break;
                 
            case 2:
                new ShowGraphAverage(project.totalFolder,project.folderName);
                break;
                
            case 3:
                System.exit(0);
        }
            System.out.println("enter new choice or exit");
       }
        
    }
        
  
}
