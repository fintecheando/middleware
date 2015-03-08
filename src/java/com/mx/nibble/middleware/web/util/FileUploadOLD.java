/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mx.nibble.middleware.web.util;


import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;
 
public class FileUploadOLD extends ActionSupport{
    
    private List<String> filemodificationdate0;
    private List<String> mimetype0;
    private List<String> pathinfo0;
    private List<String> relpathinfo0;
    private List<String> md5sum;
 
    private List<File> uploads = new ArrayList<File>();
    private List<File> items = new ArrayList<File>();
    private List<String> uploadFileNames = new ArrayList<String>();
    private List<String> uploadContentTypes = new ArrayList<String>();
    private ActionInvocation invocation;

    public List<File> getUpload() {
        return this.uploads;
    }
    public void setUpload(List<File> uploads) {
        this.uploads = uploads;
    }

    public List<String> getUploadFileName() {
        return this.uploadFileNames;
    }
    public void setUploadFileName(List<String> uploadFileNames) {
        this.uploadFileNames = uploadFileNames;
    }

    public List<String> getUploadContentType() {
        return this.uploadContentTypes;
    }
    public void setUploadContentType(List<String> contentTypes) {
        this.uploadContentTypes = contentTypes;
    }
    
   

	

    public String execute() throws Exception {
        
       
        
        //ActionContext ac = invocation.getInvocationContext();
        
        HttpServletResponse response = ServletActionContext.getResponse();
        
       // MultiPartRequestWrapper multipartRequest = ((MultiPartRequestWrapper)ServletActionContext.getRequest());
        HttpServletRequest multipartRequest = ServletActionContext.getRequest();
        
        List<FileItem> items2 = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(multipartRequest);
        System.out.println("TAMAÑO ITEMS "+items2.size());
        System.out.println("Check that we have a file upload request");
        boolean isMultipart = ServletFileUpload.isMultipartContent(multipartRequest);
        System.out.println("isMultipart: "+isMultipart);
        
        String ourTempDirectory = "/opt/erp/import/obras/";


	byte[] cr = {13}; 
	byte[] lf = {10}; 
	String CR = new String(cr);
	String LF = new String(lf);
	String CRLF = CR + LF;
	System.out.println("Before a LF=chr(10)" + LF 
		+ "Before a CR=chr(13)" + CR 
		+ "Before a CRLF" + CRLF); 


  //Initialization for chunk management.
  boolean bLastChunk = false;
  int numChunk = 0;
  
  //CAN BE OVERRIDEN BY THE postURL PARAMETER: if error=true is passed as a parameter on the URL
  boolean generateError = false;  
  boolean generateWarning = false;
  boolean sendRequest = false;  

  response.setContentType("text/plain");
  
  java.util.Enumeration<String> headers = multipartRequest.getHeaderNames();
  System.out.println("[parseRequest.jsp]  ------------------------------ ");
  System.out.println("[parseRequest.jsp]  Headers of the received request:");
  while (headers.hasMoreElements()) {
  	String header = headers.nextElement();
  	System.out.println("[parseRequest.jsp]  " + header + ": " + multipartRequest.getHeader(header));  	  	
  }
  System.out.println("[parseRequest.jsp]  ------------------------------ "); 
  
  try{
    System.out.println(" Get URL Parameters.");
    Enumeration paraNames = multipartRequest.getParameterNames();
    System.out.println("[parseRequest.jsp]  ------------------------------ ");
    System.out.println("[parseRequest.jsp]  Parameters: ");
    String pname;
    String pvalue;
    while (paraNames.hasMoreElements()) {
      pname = (String)paraNames.nextElement();
      pvalue = multipartRequest.getParameter(pname);
      System.out.println("[parseRequest.jsp] " + pname + " = " + pvalue);
      if (pname.equals("jufinal")) {
          System.out.println("pname.equals(\"jufinal\")");
      	bLastChunk = pvalue.equals("1");
      } else if (pname.equals("jupart")) {
          System.out.println("pname.equals(\"jupart\")");
      	numChunk = Integer.parseInt(pvalue);
      }

      //For debug convenience, putting error=true as a URL parameter, will generate an error
      //in this response.
      if (pname.equals("error") && pvalue.equals("true")) {
      	generateError = true;
      }

      //For debug convenience, putting warning=true as a URL parameter, will generate a warning
      //in this response.
      if (pname.equals("warning") && pvalue.equals("true")) {
      	generateWarning = true;
      }
       
      //For debug convenience, putting readRequest=true as a URL parameter, will send back the request content
      //into the response of this page.
      if (pname.equals("sendRequest") && pvalue.equals("true")) {
      	sendRequest = true;
      }
       
    }
    System.out.println("[parseRequest.jsp]  ------------------------------ ");

    int ourMaxMemorySize  = 10000000;
    int ourMaxRequestSize = 2000000000;

	///////////////////////////////////////////////////////////////////////////////////////////////////////
	//The code below is directly taken from the jakarta fileupload common classes
	//All informations, and download, available here : http://jakarta.apache.org/commons/fileupload/
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// Create a factory for disk-based file items
	DiskFileItemFactory factory = new DiskFileItemFactory();
	
	// Set factory constraints
	factory.setSizeThreshold(ourMaxMemorySize);
	factory.setRepository(new File(ourTempDirectory));
	
	// Create a new file upload handler
	ServletFileUpload upload = new ServletFileUpload(factory);
	
	// Set overall request size constraint
	upload.setSizeMax(ourMaxRequestSize);
	
	// Parse the request
	if (sendRequest) {
		//For debug only. Should be removed for production systems. 
		System.out.println("[parseRequest.jsp] ==========================================================================="); 
		System.out.println("[parseRequest.jsp] Sending the received request content: "); 
		InputStream is = multipartRequest.getInputStream();
		int c;
		while ( (c=is.read()) >= 0) {
			System.out.write(c);
		}//while
		is.close();
		System.out.println("[parseRequest.jsp] ==========================================================================="); 
	} else if (! multipartRequest.getContentType().startsWith("multipart/form-data")) {
		System.out.println("[parseRequest.jsp] No parsing of uploaded file: content type is " + multipartRequest.getContentType()); 
	} else { 
		List /* FileItem */ items = upload.parseRequest(multipartRequest);
		// Process the uploaded items
		Iterator iter = items.iterator();
		FileItem fileItem;
	    File fout;
	    System.out.println("[parseRequest.jsp]  Let's read the sent data   (" + items.size() + " items)");
		while (iter.hasNext()) {
		    fileItem = (FileItem) iter.next();
		
		    if (fileItem.isFormField()) {
		        System.out.println("[parseRequest.jsp] (form field) " + fileItem.getFieldName() + " = " + fileItem.getString());
		        
		        //If we receive the md5sum parameter, we've read finished to read the current file. It's not
		        //a very good (end of file) signal. Will be better in the future ... probably !
		        //Let's put a separator, to make output easier to read.
		        if (fileItem.getFieldName().equals("md5sum[]")) { 
					System.out.println("[parseRequest.jsp]  ------------------------------ ");
				}
		    } else {
		        //Ok, we've got a file. Let's process it.
		        //Again, for all informations of what is exactly a FileItem, please
		        //have a look to http://jakarta.apache.org/commons/fileupload/
		        //
		        System.out.println("[parseRequest.jsp] FieldName: " + fileItem.getFieldName());
		        System.out.println("[parseRequest.jsp] File Name: " + fileItem.getName());
		        System.out.println("[parseRequest.jsp] ContentType: " + fileItem.getContentType());
		        System.out.println("[parseRequest.jsp] Size (Bytes): " + fileItem.getSize());
		        //If we are in chunk mode, we add ".partN" at the end of the file, where N is the chunk number.
		        String uploadedFilename = fileItem.getName() + ( numChunk>0 ? ".part"+numChunk : "") ;
		        fout = new File(ourTempDirectory + (new File(uploadedFilename)).getName());
		        System.out.println("[parseRequest.jsp] File Out: " + fout.toString());
		        System.out.println(" write the file");
		        fileItem.write(fout);	        
		        
		        //////////////////////////////////////////////////////////////////////////////////////
		        System.out.println(" Chunk management: if it was the last chunk, let's recover the complete file");
		        System.out.println(" by concatenating all chunk parts.");
		        //
		        if (bLastChunk) {	        
			        System.out.println("[parseRequest.jsp]  Last chunk received: let's rebuild the complete file (" + fileItem.getName() + ")");
			        //First: construct the final filename.
			        FileInputStream fis;
			        FileOutputStream fos = new FileOutputStream(ourTempDirectory + fileItem.getName());
			        int nbBytes;
			        byte[] byteBuff = new byte[1024];
			        String filename;
			        for (int i=1; i<=numChunk; i+=1) {
			        	filename = fileItem.getName() + ".part" + i;
			        	System.out.println("[parseRequest.jsp] " + "  Concatenating " + filename);
			        	fis = new FileInputStream(ourTempDirectory + filename);
			        	while ( (nbBytes = fis.read(byteBuff)) >= 0) {
			        		//out.println("[parseRequest.jsp] " + "     Nb bytes read: " + nbBytes);
			        		fos.write(byteBuff, 0, nbBytes);
			        	}
			        	fis.close();
			        }
			        fos.close();
		        }
		        
		        
		        // End of chunk management
		        //////////////////////////////////////////////////////////////////////////////////////
		        
		        fileItem.delete();
		    }	    
		}//while
	}
	
    if (generateWarning) {
    	System.out.println("WARNING: just a warning message.\\nOn two lines!");
    }

  	System.out.println("[parseRequest.jsp] " + "Let's write a status, to finish the server response :");
    
    //Let's wait a little, to simulate the server time to manage the file.
    Thread.sleep(500);
    
    //Do you want to test a successful upload, or the way the applet reacts to an error ?
    if (generateError) { 
    	System.out.println("ERROR: this is a test error (forced in /wwwroot/pages/parseRequest.jsp).\\nHere is a second line!");
    } else {
    	System.out.println("SUCCESS");
    	//out.println("                        <span class=\"cpg_user_message\">Il y eu une erreur lors de l'exécution de la requête</span>");
    }

  	System.out.println("[parseRequest.jsp] " + "End of server treatment ");

  }catch(Exception e){
    System.out.println("");
    System.out.println("ERROR: Exception e = " + e.toString());
    System.out.println("");
  }
        return SUCCESS;
    }
    
    public String display() {
		return NONE;
	}

    /**
     * @return the filemodificationdate0
     */
    public List<String> getFilemodificationdate0() {
        return filemodificationdate0;
    }

    /**
     * @param filemodificationdate0 the filemodificationdate0 to set
     */
    public void setFilemodificationdate0(List<String> filemodificationdate0) {
        this.filemodificationdate0 = filemodificationdate0;
    }

    /**
     * @return the mimetype0
     */
    public List<String> getMimetype0() {
        return mimetype0;
    }

    /**
     * @param mimetype0 the mimetype0 to set
     */
    public void setMimetype0(List<String> mimetype0) {
        this.mimetype0 = mimetype0;
    }

    /**
     * @return the pathinfo0
     */
    public List<String> getPathinfo0() {
        return pathinfo0;
    }

    /**
     * @param pathinfo0 the pathinfo0 to set
     */
    public void setPathinfo0(List<String> pathinfo0) {
        this.pathinfo0 = pathinfo0;
    }

    /**
     * @return the relpathinfo0
     */
    public List<String> getRelpathinfo0() {
        return relpathinfo0;
    }

    /**
     * @param relpathinfo0 the relpathinfo0 to set
     */
    public void setRelpathinfo0(List<String> relpathinfo0) {
        this.relpathinfo0 = relpathinfo0;
    }

    /**
     * @return the md5sum
     */
    public List<String> getMd5sum() {
        return md5sum;
    }

    /**
     * @param md5sum the md5sum to set
     */
    public void setMd5sum(List<String> md5sum) {
        this.md5sum = md5sum;
    }

   

    
    
}