/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mx.nibble.middleware.web.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class Upload extends HttpServlet
{ 
     Logger logger = LoggerFactory.getLogger(Upload.class);
    PrintWriter out;
    //private final String UPLOAD_DIR = System.getProperty("java.io.tmpdir");
    private final String UPLOAD_DIR = "/opt/cyssa/erp/import/obras";
    
    /**
     * Process incoming HTTP GET requests
     *
     * @param request Object that encapsulates the request to the servlet
     * @param response Object that encapsulates the response from the servlet
     */
    public void doGet(javax.servlet.http.HttpServletRequest request,
        javax.servlet.http.HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {
        logger.debug("Ejecutando doGET");
        performTask(request,response);
    }

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
        javax.servlet.http.HttpServletResponse response) throws IOException, ServletException{
      
      out = response.getWriter();
      logger.debug("archivo subido en "+System.getProperty("java.io.tmpdir"));
  File parentFolder = new File(UPLOAD_DIR);

  if (request.getMethod().equals("GET")) {
      logger.debug("getMethod().equals(GET)");
    response.setContentType("application/x-www-form-urlencoded");
    // probe
    String filename = cleanFilename(request.getParameter("name"));
    if (filename != null) {
      File file = new File(parentFolder, filename);
      try {
        if (file.exists()) {
          String metaname = filename + ".meta";
          File meta = new File(parentFolder, metaname);

          if (meta.exists()) { // Read and output contents from file if it exists
            byte[] buffer = new byte[(int) meta.length()];
            BufferedInputStream is = null;
            try {
                is = new BufferedInputStream(new FileInputStream(meta));
                is.read(buffer);
            } finally {
                if (is != null) {
                  try {
                    is.close();
                  } catch (IOException e) {}
                }
            }
            out.append(new String(buffer));

          } else {
            // meta file deleted
            out.append("status=finished");
          }
        } else {
          out.append("status=unknown");
        }
      } catch (Exception e) {
          logger.error(e.getMessage());
        try {
          out.append("status=unknown");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
      }
    } else {
      try {
          out.append("status=unknown");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

  } else if (request.getMethod().equals("POST")) {
logger.debug("getMethod().equals(POST)");
    response.setContentType("text/html;charset=UTF-8");
    // upload
    String filename = cleanFilename(request.getParameter("name"));
    byte[] md5chunk = request.getParameter("md5chunk").getBytes();
    String md5total = request.getParameter("md5total");
    int chunk = Integer.parseInt(request.getParameter("chunk"));
    int chunkSize = request.getContentLength();
    int chunks = Integer.parseInt(request.getParameter("chunks"));

    byte[] buffer = new byte[chunkSize];
    int nowRead = 0;
    int totalRead = 0;
    InputStream is = request.getInputStream();
    while (nowRead != -1 && chunkSize > totalRead) {
      nowRead = is.read(buffer, totalRead, chunkSize - totalRead);
      if (nowRead != -1) {
        totalRead += nowRead;
      }
    }

    try {
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      md5.update(buffer);
      if (MessageDigest.isEqual(md5.digest(), md5chunk)) {
        throw new ServletException("Checksum error");
      }
    } catch (NoSuchAlgorithmException e) {
      throw new ServletException("md5sum generator not found");
    }

    File file = new File(parentFolder, filename);
    OutputStream os = new FileOutputStream(file, true);
    os.write(buffer, 0, totalRead);
    os.close();

    File metafile = new File(parentFolder, filename + ".meta");
    writeMeta(metafile, md5total, chunk, chunks);

    out.append("uploaded");
  }
            
  }
  
  private void writeMeta(File file, String md5sum, int chunk, int chunks) {
    if (chunk < (chunks - 1)) {
      String upload_meta_data = "status=uploading&chunk=" + chunk + "&chunks=" + chunks + "&md5=" + md5sum;
      FileWriter writer = null;
      try {
        writer = new FileWriter(file);
        writer.write(upload_meta_data);
      } catch (IOException e) {
      } finally {
        if (writer != null) {
          try {
            writer.close();
          } catch (IOException e) {
          }
        }
      }
    } else {
      file.delete();
    }
  }

  public void streamCopy(InputStream is, OutputStream os) throws IOException {
    streamCopy(is, os, 8192);
  }

  public void streamCopy(InputStream is, OutputStream os, int chunkSize) throws IOException {
    byte[] buf = new byte[chunkSize];
    int bytesRead;
    while ((bytesRead = is.read(buf)) != -1) {
      os.write(buf, 0, bytesRead);
    }
  }

  private String cleanFilename(String filename) {
    if (filename != null) {
      int i = filename.lastIndexOf(".");
      if (i != -1) {
        filename = filename.substring(0, i) + filename.substring(i).toLowerCase();
      }
    }
    return filename;
  }
  
}