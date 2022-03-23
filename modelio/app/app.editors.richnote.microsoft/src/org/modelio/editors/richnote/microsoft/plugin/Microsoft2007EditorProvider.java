/* 
 * Copyright 2013-2020 Modeliosoft
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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.ole.win32.OLE;
import org.eclipse.swt.ole.win32.OleClientSite;
import org.eclipse.swt.ole.win32.OleFrame;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Editor provider for Microsoft Office.
 */
@objid ("130503f1-058e-4370-ba95-919278b03684")
public class Microsoft2007EditorProvider extends MicrosoftEditorProvider {
    @objid ("abbe7c57-365a-4dec-8919-131edd649f97")
    @Override
    protected boolean computeUsable() {
        try {
            Class.forName("org.eclipse.swt.ole.win32.OLE");
        } catch (ClassNotFoundException e1) {
            return false;
        }
        
        // It seems only 'pptx' is registered
        //String wordProgId = OLE.findProgramID("docx");
        return testWord12();
    }

    @objid ("bc5eee94-dfec-4b55-9520-bbad44a84ff7")
    private static boolean testWord12() {
        final Display d = Display.getDefault();
        final boolean[] ret = new boolean[]{true};
        final SWTException[] err = new SWTException[]{null};
        
        d.syncExec(new Runnable() {
            
            @Override
            public void run() {
                Shell sh = new Shell(d);
                OleFrame f = new OleFrame(sh, 0);
                try {
                    OleClientSite oleSite = new OleClientSite(f, 0, "Word.Document.12");
                    oleSite.dispose();
                    f.dispose();
                } catch (SWTException e) {
                    if (e.code == OLE.ERROR_INVALID_CLASSID ) {
                        MicrosoftEditors.LOG.info("Microsoft Office 2007-2010 not found:"+e.getLocalizedMessage());
                        ret[0] = false;
                    } else if (e.code == OLE.ERROR_CANNOT_CREATE_OBJECT ) {
                        MicrosoftEditors.LOG.info("Microsoft Office 2007-2010 does not work:"+e.getLocalizedMessage());
                        ret[0] = false;
                    } else {
                        err[0] = e;
                    }
                }
                
            }
        });
        
        if (err[0] != null)
            throw err[0];
        return ret[0];
    }

}
