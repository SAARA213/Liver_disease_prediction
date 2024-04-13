/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classification;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author admin
 */
public class KMeans
{
    private double[][] data;        
    private int numClusters;    
    private double[][] clusterCenters;  
    private int dataSize;               
    private int dataDim;                
    private ArrayList[] clusters;     
    private ArrayList[] record;     
    private double[] clusterVars;     
 
    private double epsilon;
 
    public KMeans(double[][] data, int numClusters, double[][] clusterCenters)
    {
        dataSize = data.length;
        dataDim = data[0].length;
		
 
        this.data = data;
 
        this.numClusters = numClusters;
 
        this.clusterCenters = clusterCenters;
 
        clusters = new ArrayList[numClusters];
        record=new ArrayList[numClusters];
        
         for(int i=0;i<numClusters;i++)
        {
            clusters[i] = new ArrayList();
            record[i]=new ArrayList();
            
        }
        clusterVars = new double[numClusters];
 
        epsilon = 0.01;
    }
 
    public KMeans(double[][] data, int numClusters)
    {
        this(data, numClusters, true);
    }
 
    public KMeans(double[][] data, int numClusters, boolean randomizeCenters)
    {
        dataSize = data.length;
        dataDim = data[0].length;
 
        this.data = data;
 
        this.numClusters = numClusters;
 
        this.clusterCenters =  new double[numClusters][dataDim];
 
        clusters = new ArrayList[numClusters];
        record=new ArrayList[numClusters];
        
        for(int i=0;i<numClusters;i++)
        {
            clusters[i] = new ArrayList();
            record[i]=new ArrayList();
        }
        clusterVars = new double[numClusters];
 
        epsilon = 0.01;
 
        if(randomizeCenters)
        {
            randomizeCenters(numClusters, data);
        }
    }
 
    private void randomizeCenters(int numClusters, double[][] data) {
        Random r = new Random();
        int[] check = new int[numClusters];
        for (int i = 0; i < numClusters; i++) {
            int rand = r.nextInt(dataSize);
            if (check[i] == 0) {
                this.clusterCenters[i] = data[rand].clone();
                check[i] = 1;
            } else {
                i--;
            }
        }
    }
 
    private void calculateClusterCenters()
    {
        for(int i=0;i<numClusters;i++)
        {
            int clustSize = clusters[i].size();
 
            for(int k= 0; k < dataDim; k++)
            {
 
                double sum = 0d;
                for(int j =0; j < clustSize; j ++)
                {
                    double[] elem = (double[]) clusters[i].get(j);
                    sum += elem[k];
                }
				//System.out.println(i+" : "+k);
                clusterCenters[i][k] = sum / clustSize;
            }
        }
    }
 
     private void calculateClusterVars()
    {
        for(int i=0;i<numClusters;i++)
        {
            int clustSize = clusters[i].size();
            Double sum = 0d;
 
                for(int j =0; j < clustSize; j ++)
                {
 
                    double[] elem = (double[])clusters[i].get(j);
 
                    for(int k= 0; k < dataDim; k++)
                    {
                        sum += Math.pow( (Double)elem[k] - getClusterCenters()[i][k], 2);
                    }
                }
 
            clusterVars[i] = sum / clustSize;
        }
    }
 
     public double getTotalVar()
    {
         double total = 0d;
         for(int i=0;i< numClusters;i++)
         {
             total += clusterVars[i];
         }
 
         return total;
     }
 
     public double[] getClusterVars()
    {
        return  clusterVars;
    }
 
     public ArrayList[] getClusters()
    {
         return clusters;
    }
 
     public ArrayList[] getRecords()
     {
         return record;
     }
     private void assignData()
    {
        for(int k=0;k<numClusters;k++)
        {
            clusters[k].clear();
            record[k].clear();
        }
 
        for(int i=0; i<dataSize; i++)
        {
 
            int clust = 0;
            double dist = Double.MAX_VALUE;
            double newdist = 0;
 
            for(int j=0; j<numClusters; j++)
            {
                newdist = distToCenter( data[i], j );
                if( newdist <= dist )
                {
                    clust = j;
                    dist = newdist;
                }
            }
 
            clusters[clust].add(data[i]);
            record[clust].add(i);
        }
 
    }
 
     private double distToCenter( double[] datum, int j )
    {
         double sum = 0d;
         for(int i=0;i < dataDim; i++)
         {
			//System.out.println("ddd "+datum[i]);
             sum += Math.pow(( datum[i] - getClusterCenters()[j][i] ), 2);
         }
 
         return Math.sqrt(sum);
    }
 
      public void calculateClusters()
    {
 
         double var1 = Double.MAX_VALUE;
         double var2;
         double delta;
 
        do
        {
              calculateClusterCenters();
              assignData();
              calculateClusterVars();
              var2 = getTotalVar();
              if (Double.isNaN(var2))    // if this happens, there must be some empty clusters
             {
                 delta = Double.MAX_VALUE;
                 randomizeCenters(numClusters, data);
                 assignData();
                 calculateClusterCenters();
                 calculateClusterVars();
             }
             else
             {
                 delta = Math.abs(var1 - var2);
                 var1 = var2;
             }
 
        }while(delta > epsilon);
    }
 
    public void setEpsilon(double epsilon)
    {
        if(epsilon > 0)
        {
            this.epsilon = epsilon;
        }
    }
    
    public double[][] getClusterCenters() 
    {
        return clusterCenters;
    }
}
