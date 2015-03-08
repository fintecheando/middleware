/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mx.nibble.middleware.web.util;

//import org.chorem.pollen.ui.interceptors.PollenFileUploadInterceptor;

import java.io.File;

/**
 * Contract to place on actions which needs some upload.
 * <p/>
 * This is linked with the {@link PollenFileUploadInterceptor} interceptor
 * which logic is not the same than the bacis struts2 upload interceptor.
 * <p/>
 * Created: 30/03/12
 *
 * @author fdesbois <desbois@codelutin.com>
 * @author tchemit <chemit@codelutin.com>
 * @since 1.3
 */
public interface FileUploadAware {

    void addFile(int index, File file);

    void addFileContentType(int index, String contentType);

    void addFileName(int index, String fileName);

}
