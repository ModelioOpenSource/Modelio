/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.editors.richnote.microsoft.plugin;

import java.lang.reflect.InvocationTargetException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.ole.win32.OLE;
import org.eclipse.swt.ole.win32.OleClientSite;
import org.eclipse.swt.ole.win32.OleFrame;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Editor provider for RTF and HTML formats made by Microsoft Office .
 * <p>
 * Looks for any version of MS office among known ones.
 */
@objid ("c5bcf37a-a686-4d2a-9289-af48cb17072d")
public class MicrosoftRtfHtmlEditorProvider extends MicrosoftEditorProvider {
    @objid ("0eec6867-3c27-436e-9751-5a7c31573feb")
    @Override
    protected boolean computeUsable() {
        try {
            Class.forName("org.eclipse.swt.ole.win32.OLE");
        } catch (ClassNotFoundException e1) {
            return false;
        }
        return testWord();
    }

    @objid ("9cd8433d-d1d4-48f7-9c76-6b50437668c0")
    private boolean testWord() {
        final boolean[] ret = new boolean[] { true };
        
        Display.getDefault().syncExec(new Runnable() {
        
            @Override
            public void run() {
                try {
                    ProgressMonitorDialog service = new ProgressMonitorDialog(null) {
                        @Override
                        protected void configureShell(Shell shell) {
                            super.configureShell(shell);
                            shell.setText("Looking for Microsoft Office version");
                        }
                    };
        
                    service.run(false, false, new IRunnableWithProgress() {
        
                        @Override
                        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                            ret[0] = MicrosoftRtfHtmlEditorProvider.createWordApplicationClientSite(monitor);
                        }
                    });
                } catch (InvocationTargetException e) {
                    MicrosoftEditors.LOG.warning(e);
                } catch (InterruptedException e) {
                    MicrosoftEditors.LOG.warning(e);
                }
            }
        });
        return ret[0];
    }

    /**
     * Create a Word.Application OLE client.
     * @param monitor a progress monitor.
     * @return true if Word found else false.
     */
    @objid ("a37e0561-ad6f-4f7c-87eb-d20e966050ae")
    static boolean createWordApplicationClientSite(IProgressMonitor monitor) {
        // Try all known MS Word versions starting with the latest
        final String[] names = new String[] { "Word.Application.14", "Word.Application.12", "Word.Application.8", "Word.Application.6", "Word.Application" };
        
        monitor.beginTask("Look for Microsoft Word", names.length);
        for (String name : names) {
            Shell parent = new Shell();
            try {
                monitor.subTask("Look for Microsoft Office '" + name + "' OLE class");
        
                OleClientSite newSite = new OleClientSite(new OleFrame(parent, 0), 0, name);
                MicrosoftEditors.LOG.info("'" + name + "' OLE class found.");
                newSite.dispose();
        
                return true;
            } catch (SWTException e) {
                if (e.code == OLE.ERROR_CANNOT_CREATE_OBJECT) {
                    MicrosoftEditors.LOG.info("Microsoft Office '" + name + "' OLE class does not work:" + e.getMessage());
                } else if (e.code == OLE.ERROR_INVALID_CLASSID) {
                    MicrosoftEditors.LOG.info("Microsoft Office '" + name + "' OLE class absent");
                } else {
                    MicrosoftEditors.LOG.info("Microsoft Office '" + name + "' OLE class does not work:" + e.getMessage());
                }
            } finally {
                parent.dispose();
            }
        
            monitor.worked(1);
        }
        
        monitor.done();
        return false;
    }

}
