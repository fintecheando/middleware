/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mx.nibble.middleware.web.util;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;




public class FileImportAction extends  ActionSupport{
 
	private File fileUpload;
	private String fileUploadContentType;
	private String fileUploadFileName;
        private String jufinal;
        private String jupart;
        private String error;
        private String warning;
        private String sendRequest;
 
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
 
	public String execute() throws Exception{
 
		return SUCCESS;
 
	}
 
	public String display() {
		return NONE;
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
 
}