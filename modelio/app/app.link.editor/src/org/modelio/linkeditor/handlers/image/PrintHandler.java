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

/**
 * 
 */
package org.modelio.linkeditor.handlers.image;

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.widgets.Control;
import org.modelio.linkeditor.handlers.print.PrintPreviewDialog;
import org.modelio.linkeditor.panel.ILinkEditor;
import org.modelio.linkeditor.view.ILinkEditorView;

/**
 * Handler that print the current content of the Link Editor.
 */
@objid ("1b47772b-5e33-11e2-b81d-002564c97630")
public class PrintHandler {
    @objid ("1b47772d-5e33-11e2-b81d-002564c97630")
    @Execute
    public Object execute(@Named(IServiceConstants.ACTIVE_PART) final MPart part) {
        if (!(part.getObject() instanceof ILinkEditorView)) {
            return null;
        } else {            
        
            ILinkEditor editor = ((ILinkEditorView) part.getObject()).getLinkEditor();        
           
            /*PrintDialog dialog = new PrintDialog(((Control)editor.getPanel()).getShell(), SWT.NULL);
            PrinterData data = dialog.open();
            
            if (data != null) {
                editor.print(data);
                
             
             }*/
            PrintPreviewDialog dialog = new PrintPreviewDialog(((Control)editor.getPanel()).getShell(), editor);
            dialog.open();
            return null;
        }
    }

}
