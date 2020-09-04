/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.xmi.gui.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.xmi.gui.report.ReportModel.ElementMessage;
import org.modelio.xmi.plugin.Xmi;

/**
 * This class is the controller of the XMI report windows.
 * 
 * It provides all needed services and manages the relations between the XMI report model and the XMI report dialog.
 * @author ebrosse
 */
@objid ("5dcb4d19-672f-46d0-93f0-b90325262551")
public class ReportManager {
    @objid ("72dcf311-b26a-403f-ae2c-13aff2d8ef39")
    private static ReportDialog dialog;

    /**
     * This method opens the XMI report dialog
     * 
     * @param report : the report model exposed in report dialog
     */
    @objid ("d287868e-137b-44cd-9cc7-c5df08abf99a")
    public static void showGenerationReport(final Shell shell, final ReportModel report, final IModelioNavigationService navServices) {
        if (report == null || report.isEmpty ()) {
            if (ReportManager.dialog != null &&
                    !ReportManager.dialog.isDisposed ()) {
                ReportManager.dialog.close ();
            }
        } else {
        
            // Get the current display            
            if (ReportManager.dialog == null ||
                    ReportManager.dialog.isDisposed ()) {
                ReportManager.dialog = new ReportDialog (shell, navServices);
            }
        
            ReportManager.dialog.setModel(report);
        
            if (ReportManager.dialog.open () == SWT.OK) {            
                shell.dispose();
            }
        }
    }

    /**
     * This method creates a new Report Model
     * 
     * @return the created Report Model
     */
    @objid ("fd6e5f07-72e9-42c3-864c-26c2c3e9ebfa")
    public static ReportModel getNewReport() {
        return new ReportModel ();
    }

    @objid ("23c2f06f-2233-4291-bac9-695ba0edef37")
    private static File getLogFile(String logFilePath) {
        File logFile = new File(logFilePath);
        
        try {
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
        } catch (IOException e) {
            Xmi.LOG.error(e);       
        }
        return logFile;
    }

    @objid ("687d34cc-6697-47de-91fb-67c7d10d51f1")
    public static void writeReport(final ReportModel report, String logFilePath) {
        File logFile = getLogFile(logFilePath);
        
        DateFormat dateFormat = DateFormat.getDateTimeInstance(
                DateFormat.SHORT, DateFormat.MEDIUM, Locale.getDefault());
        
        try (FileWriter f = new FileWriter(logFile, true);
                BufferedWriter bf = new BufferedWriter(f);){
        
            for (ElementMessage error : report.getErrors()){
                bf.write("[" +  dateFormat.format(Calendar.getInstance().getTime()) + "]: Error : " + error.message);
                bf.newLine();
            }
            
            for (ElementMessage warning : report.getWarnings()){
                bf.write("[" +  dateFormat.format(Calendar.getInstance().getTime()) + "]: Warning : " + warning.message);
                bf.newLine();
            }
                     
            for (ElementMessage info : report.getInfos()){
                bf.write("[" +  dateFormat.format(Calendar.getInstance().getTime()) + "]: Info : " + info.message);
                bf.newLine();
            }
           
        } catch (IOException e) {
            Xmi.LOG.error(e);       
        }
    }

}
