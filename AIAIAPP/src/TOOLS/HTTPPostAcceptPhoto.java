package TOOLS;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

public  class HTTPPostAcceptPhoto {
	
	Map<String,String> result = new HashMap<String,String>();
	
	public Map<String,String> analyze(HttpServletRequest request,
			HttpServletResponse response,String imageurl) {
		if (ServletFileUpload.isMultipartContent(request)) {// �ж��Ƿ����ļ�
			insertPIC(request,imageurl);
		} else {
			txthandle(request, response);
		}
		return result;
	}
	//д��ͼƬ
	private void insertPIC(HttpServletRequest request,String url) {

		ServletFileUpload upload = new ServletFileUpload();
		FileItemIterator iter;
		try {
			iter = upload.getItemIterator(request);
			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				String name = item.getFieldName();// filename
				InputStream stream = item.openStream();
				System.out.println("�ı�" + item.isFormField());
				if (item.isFormField()) {// ������ı�
					txt(result,name, Streams.asString(stream,"utf-8"));
				} else {
					String dir = request.getSession().getServletContext()
							.getRealPath(url);
					OutputStream fileos = null;
					byte[] buff = new byte[1];
					File image =new File(dir, item.getName());
					System.out.println(" item.getName():"+ item.getName());
					fileos = new BufferedOutputStream(new FileOutputStream(
							image));

					while ((stream.read(buff)) != -1) {
						fileos.write(buff);
					}
					fileos.flush();
					fileos.close();
					result.put("imagename", item.getName());//����ͼƬ����
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}
	//���ı�ʱ�Ĳ���
	private  void txt(Map<String,String> result,String name, String asString){
		result.put(name, asString);
	}
	//���ı�����
	public  void txthandle(HttpServletRequest request,
			HttpServletResponse response){
		Map<String,String[]> res = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : res.entrySet()) {  
			   String key = entry.getKey().toString();  
			   String value = entry.getValue()[0].toString();  
			   result.put(key, value);
			   //System.out.println("key=" + key + " value=" + value);  
			  }  
	}
	
}
