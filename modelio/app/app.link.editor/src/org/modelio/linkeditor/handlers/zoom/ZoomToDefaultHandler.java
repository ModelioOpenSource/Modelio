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
package org.modelio.linkeditor.handlers.zoom;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.modelio.linkeditor.view.ILinkEditorView;

/**
 * Handler that will reset zoom level to 1.0 in the link editor.
 */
@objid ("1bc0dcf5-5e33-11e2-b81d-002564c97630")
public class ZoomToDefaultHandler {
    @objid ("1bc0dcf7-5e33-11e2-b81d-002564c97630")
    @Execute
    public Object execute(@Named(IServiceConstants.ACTIVE_PART) final MPart part) {
        if (!(part.getObject() instanceof ILinkEditorView)) {
            return null;
        } else {
            final ILinkEditorView linkEditorView = (ILinkEditorView) part.getObject();
            linkEditorView.getLinkEditor().setZoomLevel(1.0);
            return null;
        }
        
    }

}
