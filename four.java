package com.compc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class Four 
{
		public static void main(String[] args)throws Exception
		{
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			int totalcount = 0;
			for(int i=1;i<=9;i++)
			{
				System.out.println(i);
				OutputStreamWriter file = new OutputStreamWriter(new FileOutputStream(new  File("C:/Users/admin1/Desktop/out1/"+i+".txt")));
		   		BufferedWriter bw = new BufferedWriter(file);
		   		
				File folder = new File("C:/Users/admin1/Desktop/out/"+i+"/");
				File[] files = folder.listFiles();
				
				for(int j=0;j<files.length;j++)
				{
					System.out.println(files[j].getPath());
					if(!files[j].getName().toString().contains(".db"))
					{
					Mat trn = Highgui.imread(files[j].getPath());
					//Imgproc.cvtColor(trn,trn,CvType.CV_32FC1);
					Imgproc.cvtColor(trn, trn, Imgproc.COLOR_BGR2GRAY);
					Imgproc.resize(trn,trn, new Size(56,56));
					//System.out.println(trn.channels()+"");
					int aabk = 0, aawt = 0 ,bbbk = 0, bbwt = 0,ccbk = 0, ccwt = 0,ddbk = 0,ddwt = 0;
					for(int ii=0;ii<28;ii++)
						for(int jj=0;jj<28;jj++)
						{
								double[] d = trn.get(ii, jj);
								if((d[0]) == 255)
									aawt++;
								else if((d[0]) == 0)
									aabk++;
						
						}
			for(int ii=28;ii<56;ii++)
						for(int jj=0;jj<28;jj++)
						{
								double[] d = trn.get(ii, jj);
								if((d[0]) == 255)
									bbwt++;
								else if((d[0]) == 0)
									bbbk++;
						
						}
						
						for(int ii=0;ii<28;ii++)
						for(int jj=28;jj<56;jj++)
						{
								double[] d = trn.get(ii, jj);
								if((d[0]) == 255)
									ccwt++;
								else if((d[0]) == 0)
									ccbk++;
						
						}
						
						for(int ii=28;ii<56;ii++)
						for(int jj=28;jj<56;jj++)
						{
								double[] d = trn.get(ii, jj);
								if((d[0]) == 255)
									ddwt++;
								else if((d[0]) == 0)
									ddbk++;
						
						}
					
			
					
					
					
							totalcount++;
					bw.write(aawt+" "+bbwt+" "+ccwt+" "+ddwt+" "+aabk+" "+bbbk+" "+ccbk+" "+ddbk);
					bw.newLine();
					bw.flush();
					}
				}
				bw.flush();
				bw.close();	
				System.out.println("Total count "+totalcount);
				
			}
			
	        
		}
		public static Mat toOneRow(Mat source)
		{
			Mat dest = new Mat(1,3136,CvType.CV_32FC1);
			//System.out.println(source.channels()+" "+dest.channels()+" -");
			int count = 0;
			
			for(int i=0;i<56;i++)
				for(int j=0;j<56;j++)
				{
						double[] dd = source.get(i, j);
						dest.put(0, count, dd);
					
					//System.out.println(count+" "+"count");
					count++;
					
				}
			
			return dest;
		

		}

}
