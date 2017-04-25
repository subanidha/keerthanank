import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.lang.Object;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;


public class CompSegmentation 
{
	public static void main(String[] args)throws Exception
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Highgui.imread("C:/Users/admin1/Desktop/images/1.jpg");
		Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY);
    	
        Mat dest = new Mat();
        dest = src.clone();
       
        
       Imgproc.threshold(src, dest,100, 255, Imgproc.THRESH_BINARY_INV);
        
        Highgui.imwrite("C:/Users/admin1/Desktop/images/sub/destv.jpg", dest);
        
        List<MatOfPoint> contours = new ArrayList<>();
        
        Imgproc.findContours(dest, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        Mat destCont = new Mat();
        destCont = src.clone();
        for(int i=0;i<destCont.rows();i++)
        	for(int j=0;j<destCont.cols();j++)
        	{
        		double[] d = {0};
        		destCont.put(i, j, d);
        	}
        Imgproc.drawContours(destCont, contours, -1, new Scalar(255,255,0));
      
        		
    
       System.out.println(contours.size()+"");
       Mat smallIm = new Mat(src.width(), src.height(), src.type());
       	OutputStreamWriter file = new OutputStreamWriter(new FileOutputStream(new  File("C:/Users/admin1/Desktop/images/pointsnew.txt")));
   		BufferedWriter bw = new BufferedWriter(file);
   		
   		OutputStreamWriter file1 = new OutputStreamWriter(new FileOutputStream(new  File("C:/Users/admin1/Desktop/images/areasnew.txt")));
   		BufferedWriter bw1 = new BufferedWriter(file1);
   		
   		int fileNum = 0;
        for(int i=0;i<contours.size();i++)
        {
        	MatOfPoint m = contours.get(i);
     
        	
        	if(m.size().height>10||m.size().width>10)
        	{
        	Rect r = Imgproc.boundingRect(m);
        	Mat subM =	src.submat(r);
        	System.out.println("c "+m.size());
        	System.out.println("r "+r.size());
        	
        	bw.write(r.x+" "+r.y);
        	bw.newLine();
        	
        	String[] areaSplitDim = r.size().toString().split("x");
        	bw1.write(r.size()+" "+(Integer.parseInt(areaSplitDim[0])*Integer.parseInt(areaSplitDim[1])));
        	bw1.newLine();
        	
        	Highgui.imwrite("C:/Users/admin1/Desktop/images/sub/"+fileNum+".jpg", subM);
        	fileNum++;
        	String[] dim = m.size().toString().split("x");
        	
        	if(Integer.parseInt(dim[1])<50)
        	{
        		Point[] point = m.toArray();
        		org.opencv.core.Core.putText(destCont, "("+(int)point[0].x+","+(int)point[0].y+")", new Point((int)point[0].x,(int)point[0].y), Core.FONT_HERSHEY_SIMPLEX, new Double(1),new  Scalar(255));
           		smallIm.put((int)point[0].x,(int)point[0].y, 1);
        		System.out.println();
        	}
        	
        	}
        		
        }
        bw1.close();
        bw.flush();
        bw.close();
        Highgui.imwrite("C:/Users/admin1/Desktop/images/sub/desCont.jpg", destCont);
        Highgui.imwrite("C:/Users/admin1/Desktop/images/5.jpg", dest);
			}

}

