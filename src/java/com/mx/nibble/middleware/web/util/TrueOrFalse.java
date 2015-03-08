/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.middleware.web.util;

/**
 *
 * @author 43507296
 */

public class TrueOrFalse{
 
	private Boolean trueOrFalseCode;
	private String trueOrFalseDisplay;
 
	//getter and setter methods
 
	public TrueOrFalse(Boolean trueOrFalseCode, String trueOrFalseDisplay) {
		this.trueOrFalseCode = trueOrFalseCode;
		this.trueOrFalseDisplay = trueOrFalseDisplay;
	}

    /**
     * @return the trueOrFalseCode
     */
    public Boolean getTrueOrFalseCode() {
        return trueOrFalseCode;
    }

    /**
     * @param trueOrFalseCode the trueOrFalseCode to set
     */
    public void setTrueOrFalseCode(Boolean trueOrFalseCode) {
        this.trueOrFalseCode = trueOrFalseCode;
    }

    /**
     * @return the trueOrFalseDisplay
     */
    public String getTrueOrFalseDisplay() {
        return trueOrFalseDisplay;
    }

    /**
     * @param trueOrFalseDisplay the trueOrFalseDisplay to set
     */
    public void setTrueOrFalseDisplay(String trueOrFalseDisplay) {
        this.trueOrFalseDisplay = trueOrFalseDisplay;
    }
}