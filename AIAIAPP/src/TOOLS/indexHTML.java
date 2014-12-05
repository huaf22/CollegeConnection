package TOOLS;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;  
import java.io.OutputStreamWriter;
import java.util.List;
  
import org.jsoup.*;  
import org.jsoup.helper.Validate;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;  
import org.jsoup.select.Elements; 

public class indexHTML {
	public void getIndexHTMLData(){
		BufferedWriter bw=null;
		try {//C:\Documents and Settings\Administrator\×ÀÃæ
			 bw=new BufferedWriter(new FileWriter(new File("./WebRoot/index.html")));//./WebRoot/index.html
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String text=null;
	 	String title=null;
	 	String href=null;
	    String url = "http://www.aiai.edu.cn";  
	    long start = System.currentTimeMillis();  
	    Document doc=null;  
	    try{  
	        doc = Jsoup.connect(url).timeout(10000).get();  
	    }  
	    catch(Exception e){  
	        e.printStackTrace();  
	    }  
	    try {
	    	//<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />

	    	//<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>
			bw.write("<!DOCTYPE HTML>" +
					"<html>" +
					"<head>" +"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\" />"+
					"<meta name=\"viewport\" content=\"width=device-width; initial-scale=1.0; maximum-scale=1.0;\"> " +
					"<title>index</title>" +
					"<link href=\"css/style.css\" rel=\"stylesheet\" type=\"text/css\"  />" +
					"</head>" +
					"<body>" +
					"<div class=\"content\">");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    Elements links = doc.select("a[href]");
	    for(int i=0;i<links.size();i++){
	    	 text=(String)links.get(i).text();
	    	 title=links.get(i).attr("title");
	    	 href=links.get(i).attr("href");
	    	if(!title.isEmpty()){
	    		if(href.indexOf("http")==(-1)){
	    		//System.out.println(" text: "+text);
		    	 System.out.println(title);  
		    	 System.out.println(url+href); 
		    	 StringBuilder res=new StringBuilder("<div class=\"blog\"> <div class=\"blog-content\"><p><a href=\"");
		    	 res.append(url+href);
		    	 res.append("\">");
		    	 res.append(title);
		    	 res.append(" </a></p> </div></div>");
		    	 try {
					bw.write(res.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	 } 	
	    	}
	    }
		try {
			bw.write("</div></body></html>");
			bw.flush();
			bw.close();
		   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	/*public void writeHTML(){
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File("./WebRoot/index.html"),true));
			
			bw.write("</div></body></html>");
			bw.flush();
			bw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	public static void main(String[] args){
		indexHTML ip=new indexHTML();
		ip.getIndexHTMLData();
		//ip.writeHTML();
		System.out.println("ok");
		
	}
	
}
/*
 *  <div class="blog">
           
            <div class="blog-content"><p><a href="blog-view.html">Yes you are right, its just a sample text which you shouldn't be reading.it just previews a view of text so that you may get an idea of how actual text is going to look. i think you are still reading. </a></p>
            </div>
        </div>*/
