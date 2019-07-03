
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




public class showGraph extends JFrame{

    ArrayList al;
    ArrayList fileName;
    ArrayList folderName;
    ArrayList resultPageName;
    ArrayList fileLength;

showGraph(ArrayList fileName,ArrayList folderName,ArrayList resultPageName,ArrayList fileLength) throws IOException
{
    super("Graph");  
    this.fileName=fileName;
    this.folderName=folderName;
    this.resultPageName=resultPageName;
    this.fileLength=fileLength;
    // Create Dataset  
    CategoryDataset dataset = createDataset();  
      
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

    private CategoryDataset createDataset() throws IOException {
        
        int index=0;
        
        
       al=new ArrayList();
       findValue();
       DefaultCategoryDataset dcd=new DefaultCategoryDataset();
 
        for(int j=0;j<fileLength.size();j++)
        {
             for(int i=0;i<(int)fileLength.get(j);i++)
            {
                dcd.addValue(Integer.parseInt((String) al.get(index)), (String) fileName.get(i), (Comparable) folderName.get(j));
                index++;
            }
        }
        
       return dcd;
    }

    private void findValue() throws FileNotFoundException, IOException {
       
        
        for(int j=0;j<folderName.size();j++)
        {
             FileInputStream read=new FileInputStream((String) resultPageName.get(j));
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
    }
    
   
}
