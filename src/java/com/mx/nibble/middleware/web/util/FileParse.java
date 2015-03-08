/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mx.nibble.middleware.web.util;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import static java.lang.System.out;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.PrintWriter;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
/**
 *
 * @author victor
 */
public class FileParse extends ActionSupport{
    
        private static final Log log = LogFactory.getLog(FileParse.class);
	private File fileUpload;
	private String fileUploadContentType;
	private String fileUploadFileName;
        
        private String jufinal;
        private String jupart;
        private String error;
        private String warning;
        private String sendRequest;
        
        /*private HttpServletResponse response;    
        private HttpServletRequest request;
 */
	public String getFileUploadContentType() {
		return fileUploadContentType;
	}
 
	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}
 
	public String getFileUploadFileName() {
		return fileUploadFileName;
	}
 
	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}
 
	public File getFileUpload() {
		return fileUpload;
	}
 
	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}
        
        Map parameters = null;

public final Map getParameters() {
        parameters=ActionContext.getContext().getParameters();
        return parameters;
	}

public String getParameterValue(String param){
		Object paramObj = getParameters().get(param);
		if(paramObj == null) return null;
		return ((String[])paramObj)[0];
	}
 
	public String execute(ActionInvocation invocation) throws Exception{
            
            Iterator iterator = parameters.entrySet().iterator();
            while (iterator.hasNext()) {
                    Map.Entry mapEntry = (Map.Entry) iterator.next();
                    System.out.println("The key is: " + mapEntry.getKey()
                            + ",value is :" + mapEntry.getValue());
            }
 
            

            
            ActionContext ac = invocation.getInvocationContext();

        HttpServletRequest request = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);
        
        HttpServletResponse response = (HttpServletResponse) ac.get(ServletActionContext.HTTP_RESPONSE);
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

                   // Directory to store all the uploaded files
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
                 
                 
                 java.util.Enumeration<String> headers = request.getHeaderNames();
                 System.out.println("[parseRequest.jsp]  ------------------------------ ");
                 System.out.println("[parseRequest.jsp]  Headers of the received request:");
                 while (headers.hasMoreElements()) {
                       String header = headers.nextElement();
                       System.out.println("[parseRequest.jsp]  " + header + ": " + request.getHeader(header));  	  	
                 }
                 System.out.println("[parseRequest.jsp]  ------------------------------ "); 

                 try{
                   // Get URL Parameters.
                   Enumeration paraNames = request.getParameterNames();
                   System.out.println("[parseRequest.jsp]  ------------------------------ ");
                   System.out.println("[parseRequest.jsp]  Parameters: ");
                   String pname;
                   String pvalue;
                   while (paraNames.hasMoreElements()) {
                     pname = (String)paraNames.nextElement();
                     pvalue = request.getParameter(pname);
                     System.out.println("[parseRequest.jsp] " + pname + " = " + pvalue);
                     if (pname.equals("jufinal")) {
                       bLastChunk = pvalue.equals("1");
                     } else if (pname.equals("jupart")) {
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
                               InputStream is = request.getInputStream();
                               int c;
                               while ( (c=is.read()) >= 0) {
                                       out.write(c);
                               }//while
                               is.close();
                               System.out.println("[parseRequest.jsp] ==========================================================================="); 
                       } else if (! request.getContentType().startsWith("multipart/form-data")) {
                               System.out.println("[parseRequest.jsp] No parsing of uploaded file: content type is " +  request.getContentType()); 
                       } else { 
                               List /* FileItem */ items = upload.parseRequest(request);
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
                                       // write the file
                                       fileItem.write(fout);	        

                                       //////////////////////////////////////////////////////////////////////////////////////
                                       //Chunk management: if it was the last chunk, let's recover the complete file
                                       //by concatenating all chunk parts.
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
                                                               //System.out.println("[parseRequest.jsp] " + "     Nb bytes read: " + nbBytes);
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
                       PrintWriter out = ServletActionContext.getResponse().getWriter();
                        out.write("SUCCESS");
                       //System.out.println("                        <span class=\"cpg_user_message\">Il y eu une erreur lors de l'exécution de la requête</span>");
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
		return INPUT;
	}

    /**
     * @return the jufinal
     */
    public String getJufinal() {
        return jufinal;
    }

    /**
     * @param jufinal the jufinal to set
     */
    public void setJufinal(String jufinal) {
        this.jufinal = jufinal;
    }

    /**
     * @return the jupart
     */
    public String getJupart() {
        return jupart;
    }

    /**
     * @param jupart the jupart to set
     */
    public void setJupart(String jupart) {
        this.jupart = jupart;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return the warning
     */
    public String getWarning() {
        return warning;
    }

    /**
     * @param warning the warning to set
     */
    public void setWarning(String warning) {
        this.warning = warning;
    }

    /**
     * @return the sendRequest
     */
    public String getSendRequest() {
        return sendRequest;
    }

    /**
     * @param sendRequest the sendRequest to set
     */
    public void setSendRequest(String sendRequest) {
        this.sendRequest = sendRequest;
    }

    /**
     * @return the response
     
    public HttpServletResponse getResponse() {
        return response;
    }

    /**
     * @param response the response to set
    
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    /**
     * @return the request
     
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * @param request the request to set
     
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }*/
 
}