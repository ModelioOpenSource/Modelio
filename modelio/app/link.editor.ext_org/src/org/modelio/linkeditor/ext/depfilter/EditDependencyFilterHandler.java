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

/**
 * 
 */
package org.modelio.linkeditor.ext.depfilter;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.modelio.linkeditor.ext.LinkEditorConfigurationParameters;
import org.modelio.linkeditor.ext.view.LinkEditorView;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;

/**
 * Handler for the EditDependencyFilter command.
 */
@objid ("1b6b2b8d-5e33-11e2-b81d-002564c97630")
public class EditDependencyFilterHandler {
    /**
     * C'tor.
     */
    @objid ("1b6b2b8f-5e33-11e2-b81d-002564c97630")
    public EditDependencyFilterHandler() {
        super();
    }

    @objid ("1b6b2b92-5e33-11e2-b81d-002564c97630")
    @Execute
    public Object execute(Shell shell, IMModelServices modelServices, MPart part) {
        if (part.getObject() instanceof LinkEditorView) {
            final LinkEditorView linkEditorView = (LinkEditorView) part.getObject();
        
            // Open a dialog where user can set/edit the filter
            DialogModel model = new DialogModel(LinkEditorConfigurationParameters.getInstance().getEnabledStereotypes(), modelServices);
            
            DialogView view = new DialogView(shell, model);
            
            view.setBlockOnOpen(true);
            
            // Only use result if user pressed OK
            if (view.open() == Window.OK) {
                // Update the LinkEditorConfigurationParameters instance
                LinkEditorConfigurationParameters.getInstance().setEnabledStereotypes(model.getFilter());
                linkEditorView.refreshFromCurrentSelection();
            }
        }
        return null;
    }

}
