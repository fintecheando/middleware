/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mx.nibble.middleware.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class FileUpload extends HttpServlet
{ 
    Logger logger = LoggerFactory.getLogger(FileUpload.class);
    private static final String UPLOAD_DIRECTORY = "/opt/erp/import/obras";
    PrintWriter out;
    /**
     * Process incoming HTTP GET requests
     *
     * @param request Object that encapsulates the request to the servlet
     * @param response Object that encapsulates the response from the servlet
     */
   /* public void doGet(javax.servlet.http.HttpServletRequest request,
        javax.servlet.http.HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {
        logger.debug("Ejecutando doGET");
        performTask(request, response);
    }*/

    /**
     * Process incoming HTTP POST requests
     *
     * @param request Object that encapsulates the request to the servlet
     * @param response Object that encapsulates the response from the servlet
     */
    public void doPost(javax.servlet.http.HttpServletRequest request,
        javax.servlet.http.HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {
        logger.debug("Ejecutando doPOST");
        performTask(request, response);
    }
    
  public void performTask(javax.servlet.http.HttpServletRequest request,
        javax.servlet.http.HttpServletResponse response) {
      
            
    /**
 * This pages gives a sample of java upload management. It needs the commons FileUpload library, which can be found on apache.org.
 *
 * Note:
 * - putting error=true as a parameter on the URL will generate an error. Allows test of upload error management in the applet. 
 * 
 * 
 * 
 * 
 * 
 * 
 */

    logger.debug(" Directory to store all the uploaded files");
      
    String ourTempDirectory = "/opt/erp/import/obras/";
    try{
    out = response.getWriter();
    } catch (Exception e)
    {
        e.printStackTrace();
    }
    
	byte[] cr = {13}; 
	byte[] lf = {10}; 
	String CR = new String(cr);
	String LF = new String(lf);
	String CRLF = CR + LF;
	out.println("Before a LF=chr(10)" + LF 
		+ "Before a CR=chr(13)" + CR 
		+ "Before a CRLF" + CRLF); 


  logger.debug("Initialization for chunk management.");
  boolean bLastChunk = false;
  int numChunk = 0;
  
  logger.debug("CAN BE OVERRIDEN BY THE postURL PARAMETER: if error=true is passed as a parameter on the URL");
  boolean generateError = false;  
  boolean generateWarning = false;
  boolean sendRequest = false;  

  response.setContentType("text/plain");
  
  java.util.Enumeration<String> headers = request.getHeaderNames();
  out.println("[parseRequest.jsp]  ------------------------------ ");
  out.println("[parseRequest.jsp]  Headers of the received request:");
  logger.debug("[parseRequest.jsp]  Headers of the received request:");
  while (headers.hasMoreElements()) {
  	String header = headers.nextElement();
  	out.println("[parseRequest.jsp]  " + header + ": " + request.getHeader(header));  	  	
        logger.debug("[parseRequest.jsp]  " + header + ": " + request.getHeader(header));  	  	
  }
  out.println("[parseRequest.jsp]  ------------------------------ "); 
  
  try{
    logger.debug(" Get URL Parameters.");
    Enumeration paraNames = request.getParameterNames();
    out.println("[parseRequest.jsp]  ------------------------------ ");
    out.println("[parseRequest.jsp]  Parameters: ");
    logger.debug("[parseRequest.jsp]  Parameters: ");
    String pname;
    String pvalue;
    while (paraNames.hasMoreElements()) {
      pname = (String)paraNames.nextElement();
      pvalue = request.getParameter(pname);
      out.println("[parseRequest.jsp] " + pname + " = " + pvalue);
      logger.debug("[parseRequest.jsp] " + pname + " = " + pvalue);
      if (pname.equals("jufinal")) {
      	bLastChunk = pvalue.equals("1");
      } else if (pname.equals("jupart")) {
      	numChunk = Integer.parseInt(pvalue);
      }

      
      if (pname.equals("error") && pvalue.equals("true")) {
      	generateError = true;
        logger.debug("For debug convenience, putting error=true as a URL parameter, will generate an error in this response.");
      }

      
      if (pname.equals("warning") && pvalue.equals("true")) {
      	generateWarning = true;
        logger.debug("For debug convenience, putting warning=true as a URL parameter, will generate a warning in this response.");
      }
       
      
      if (pname.equals("sendRequest") && pvalue.equals("true")) {
      	sendRequest = true;
        logger.debug("For debug convenience, putting readRequest=true as a URL parameter, will send back the request content into the response of this page.");
      }
       
    }
    out.println("[parseRequest.jsp]  ------------------------------ ");

    int ourMaxMemorySize  = 10000000;
    int ourMaxRequestSize = 2000000000;

	///////////////////////////////////////////////////////////////////////////////////////////////////////
	//The code below is directly taken from the jakarta fileupload common classes
	//All informations, and download, available here : http://jakarta.apache.org/commons/fileupload/
	///////////////////////////////////////////////////////////////////////////////////////////////////////
    
	logger.debug(" Create a factory for disk-based file items");
	DiskFileItemFactory factory = new DiskFileItemFactory();
	
	logger.debug(" Set factory constraints");
	factory.setSizeThreshold(ourMaxMemorySize);
        logger.debug("ourTempDirectory" +ourTempDirectory);
	factory.setRepository(new File(ourTempDirectory));
	
	logger.debug(" Create a new file upload handler");
	ServletFileUpload upload = new ServletFileUpload(factory);
	
	logger.debug(" Set overall request size constraint");
	upload.setSizeMax(ourMaxRequestSize);
	
	logger.debug(" Parse the request");
	if (sendRequest) {
		logger.debug("For debug only. Should be removed for production systems. ");
		out.println("[parseRequest.jsp] ==========================================================================="); 
		out.println("[parseRequest.jsp] Sending the received request content: "); 
                logger.debug("[parseRequest.jsp] Sending the received request content: "); 
		InputStream is = request.getInputStream();
		int c;
		while ( (c=is.read()) >= 0) {
			out.write(c);
		}logger.debug("while");
		is.close();
		out.println("[parseRequest.jsp] ==========================================================================="); 
	} else if (! request.getContentType().startsWith("multipart/form-data")) {
		out.println("[parseRequest.jsp] No parsing of uploaded file: content type is " + request.getContentType()); 
	} else { 
            
            
		List /* FileItem */ items = upload.parseRequest(request);
                
		logger.debug(" Process the uploaded items"+items.size());
                
		Iterator iter = items.iterator();
		FileItem fileItem;
                File fout;
                out.println("[parseRequest.jsp]  Let's read the sent data   (" + items.size() + " items)");
		while (iter.hasNext()) {
		    fileItem = (FileItem) iter.next();
		
		    if (fileItem.isFormField()) {
		        out.println("[parseRequest.jsp] (form field) " + fileItem.getFieldName() + " = " + fileItem.getString());
		        
		        logger.debug("If we receive the md5sum parameter, we've read finished to read the current file. It's not");
		        logger.debug("a very good (end of file) signal. Will be better in the future ... probably !");
		        logger.debug("Let's put a separator, to make output easier to read.");
		        if (fileItem.getFieldName().equals("md5sum[]")) { 
					out.println("[parseRequest.jsp]  ------------------------------ ");
				}
		    } else {
		        logger.debug("Ok, we've got a file. Let's process it.");
		        logger.debug("Again, for all informations of what is exactly a FileItem, please");
		        logger.debug("have a look to http://jakarta.apache.org/commons/fileupload/");
		        
		        out.println("[parseRequest.jsp] FieldName: " + fileItem.getFieldName());
		        out.println("[parseRequest.jsp] File Name: " + fileItem.getName());
		        out.println("[parseRequest.jsp] ContentType: " + fileItem.getContentType());
		        out.println("[parseRequest.jsp] Size (Bytes): " + fileItem.getSize());
		        logger.debug("If we are in chunk mode, we add .partN at the end of the file, where N is the chunk number.");
		        String uploadedFilename = fileItem.getName() + ( numChunk>0 ? ".part"+numChunk : "") ;
		        fout = new File(ourTempDirectory + (new File(uploadedFilename)).getName());
		        out.println("[parseRequest.jsp] File Out: " + fout.toString());
		        logger.debug(" write the file");
		        fileItem.write(fout);	        
		        
		       //
		        logger.debug("Chunk management: if it was the last chunk, let's recover the complete file");
		        logger.debug("by concatenating all chunk parts.");
		        logger.debug("");
		        if (bLastChunk) {	        
			        out.println("[parseRequest.jsp]  Last chunk received: let's rebuild the complete file (" + fileItem.getName() + ")");
			        logger.debug("First: construct the final filename.");
			        FileInputStream fis;
			        FileOutputStream fos = new FileOutputStream(ourTempDirectory + fileItem.getName());
			        int nbBytes;
			        byte[] byteBuff = new byte[1024];
			        String filename;
			        for (int i=1; i<=numChunk; i+=1) {
			        	filename = fileItem.getName() + ".part" + i;
			        	out.println("[parseRequest.jsp] " + "  Concatenating " + filename);
			        	fis = new FileInputStream(ourTempDirectory + filename);
			        	while ( (nbBytes = fis.read(byteBuff)) >= 0) {
			        		//out.println("[parseRequest.jsp] " + "     Nb bytes read: " + nbBytes);
			        		fos.write(byteBuff, 0, nbBytes);
			        	}
			        	fis.close();
			        }
			        fos.close();
		        }
		        
		        
		        logger.debug(" End of chunk management");
		        //
		        
		        fileItem.delete();
		    }	    
		}logger.debug("while");
	}
	
    if (generateWarning) {
    	out.println("WARNING: just a warning message.\\nOn two lines!");
    }

  	out.println("[parseRequest.jsp] " + "Let's write a status, to finish the server response :");
    
    logger.debug("Let's wait a little, to simulate the server time to manage the file.");
    Thread.sleep(500);
    
    logger.debug("Do you want to test a successful upload, or the way the applet reacts to an error ?");
    if (generateError) { 
    	out.println("ERROR: this is a test error (forced in /wwwroot/pages/parseRequest.jsp).\\nHere is a second line!");
    } else {
    	out.println("SUCCESS");
    	logger.debug("");
    }

  	out.println("[parseRequest.jsp] " + "End of server treatment ");

  }catch(Exception e){
    out.println("");
    out.println("ERROR: Exception e = " + e.toString());
    out.println("");
  }
  }
  
  
}