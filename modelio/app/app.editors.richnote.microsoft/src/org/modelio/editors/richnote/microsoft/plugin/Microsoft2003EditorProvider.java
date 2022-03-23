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
 * Editor provider for Microsoft Office 97 - 2003.
 * <p>
 * Disabled on Windows 64 because it does not work.
 */
@objid ("b10af90b-fa67-4e1a-8af0-0632c64e1030")
public class Microsoft2003EditorProvider extends MicrosoftEditorProvider {
    @objid ("7c7f16b6-ba54-4bbf-a0d3-9babe96fd193")
    @Override
    protected boolean computeUsable() {
        try {
            Class.forName("org.eclipse.swt.ole.win32.OLE");
        } catch (ClassNotFoundException e1) {
            return false;
        }
        return testWord8();
    }

    @objid ("74e21bf8-7f50-439d-8798-da018778180b")
    private static boolean testWord8() {
        final Display d = Display.getDefault();
        final boolean[] ret = new boolean[]{true};
        final SWTException[] err = new SWTException[]{null};
        
        d.syncExec(new Runnable() {
            
            @Override
            public void run() {
                Shell sh = new Shell(d);
                OleFrame f = new OleFrame(sh, 0);
                try {
                    OleClientSite oleSite = new OleClientSite(f, 0, "Word.Document.8");
                    oleSite.dispose();
                    f.dispose();
                } catch (SWTException e) {
                    if (e.code == OLE.ERROR_INVALID_CLASSID ) {
                        MicrosoftEditors.LOG.info("Microsoft Office 97-2003 not found:"+e.getLocalizedMessage());
                        ret[0] = false;
                    } else if (e.code == OLE.ERROR_CANNOT_CREATE_OBJECT ) {
                        MicrosoftEditors.LOG.info("Microsoft Office 97-2003 does not work:"+e.getLocalizedMessage());
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
