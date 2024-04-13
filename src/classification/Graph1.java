/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classification;
import java.awt.Color;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.ChartFrame;
/**
 *
 * @author admin
 */
public class Graph1 
{
     public void display(String alg,String val,String tit)
    {
        try
        {
            String al[]=alg.split("#");
            String v1[]=val.split("#");
            
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
            for(int i=0;i<al.length;i++)
                dataset.setValue(Double.parseDouble(v1[i]),al[i],"");
            
            JFreeChart chart = ChartFactory.createBarChart  
                ("Performance - "+tit,"Algorithm", tit, dataset, 
                 PlotOrientation.VERTICAL, true,true, false);
  
            chart.getTitle().setPaint(Color.blue); 
  
            CategoryPlot p = chart.getCategoryPlot(); 
  
            p.setRangeGridlinePaint(Color.red); 
         
  
            CategoryItemRenderer renderer = p.getRenderer();

            //renderer.setSeriesPaint(0, Color.red);

            
            renderer.setSeriesPaint(0, Color.RED);
            renderer.setSeriesPaint(1, Color.BLUE);
            renderer.setSeriesPaint(2, Color.GREEN);
            renderer.setSeriesPaint(3, Color.YELLOW);
            renderer.setSeriesPaint(4, Color.MAGENTA);
	  
 
            
            ChartFrame frame1=new ChartFrame(tit,chart);
  
            frame1.setSize(450,400);
  
            frame1.setVisible(true);
  
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void display2(String alg,String val,String tit)
    {
        try
        {
            String al[]=alg.split("#");
            String v1[]=val.split("@");
            
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
            for(int i=0;i<al.length;i++)
            {
                //dataset.setValue(Double.parseDouble(v1[i]),al[i],"");
                 String g1[]=v1[i].split("#");
                dataset.setValue(Double.parseDouble(g1[0]),al[i],"Precision");
                dataset.setValue(Double.parseDouble(g1[1]),al[i],"Recall");     
                dataset.setValue(Double.parseDouble(g1[2]),al[i],"F-measure");     
            }
            
            JFreeChart chart = ChartFactory.createBarChart  
                ("Performance - "+tit,"Algorithm", tit, dataset, 
                 PlotOrientation.VERTICAL, true,true, false);
  
            chart.getTitle().setPaint(Color.blue); 
  
            CategoryPlot p = chart.getCategoryPlot(); 
  
            p.setRangeGridlinePaint(Color.red); 
         
  
            CategoryItemRenderer renderer = p.getRenderer();

            //renderer.setSeriesPaint(0, Color.red);

            
            renderer.setSeriesPaint(0, Color.RED);
            renderer.setSeriesPaint(1, Color.BLUE);
            renderer.setSeriesPaint(2, Color.GREEN);
            renderer.setSeriesPaint(3, Color.YELLOW);
            renderer.setSeriesPaint(4, Color.MAGENTA);
	  
 
            
            ChartFrame frame1=new ChartFrame(tit,chart);
  
            frame1.setSize(450,400);
  
            frame1.setVisible(true);
  
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
