
package project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class ShowGraphAverage extends JFrame {
    
    ArrayList al;

    
    ShowGraphAverage(int folderLength,ArrayList folderName) throws IOException
    {
        al=new ArrayList();   
         CategoryDataset dataset = createDataset(folderLength,folderName);  
      
    //Create chart  
    JFreeChart chart=ChartFactory.createBarChart(  
        "Report", //Chart Title  
        "Keyword", // Category axis  
        "Frequency", // Value axis  
        dataset,  
        PlotOrientation.VERTICAL,  
        true,true,false  
       );  
  
    ChartPanel panel=new ChartPanel(chart);  
    setContentPane(panel);
    
    setSize(800, 400);  
    setLocationRelativeTo(null);  
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
    setVisible(true);
    }
    
    
    private void findValue() throws FileNotFoundException, IOException
    {
        FileInputStream read=new FileInputStream("F://averagePage.txt");
        int i=0;
        String str ="";
        while((i=read.read())!=-1)
        {
            if((char)i=='-')
            {
                i=read.read();
                while((char)i!='-')
                {
                   str=str+(char)i; 
                    //System.out.println((char)i);
                    i=read.read();
                }
                al.add(str);
                str="";
            }   
        }
        read.close();
    }      

    private CategoryDataset createDataset(int folderLength,ArrayList folderName) throws IOException {
        findValue();
        DefaultCategoryDataset dcd=new DefaultCategoryDataset();
        
        for(int i=0;i<folderLength;i++)
        {
           
            dcd.addValue(Float.parseFloat((String)al.get(i)),(String)folderName.get(i),"Average Value");
        }
        return dcd;
    }
         
    
}
