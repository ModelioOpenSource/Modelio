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
package org.modelio.linkeditor.handlers.setorientation;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.modelio.linkeditor.panel.ILinkEditor;
import org.modelio.linkeditor.panel.ILinkEditorConfiguration.Orientation;
import org.modelio.linkeditor.panel.ILinkEditorConfigurator;
import org.modelio.linkeditor.view.ILinkEditorView;

/**
 * Flips the orientation of layout.
 */
@objid ("1b6d8ce1-5e33-11e2-b81d-002564c97630")
public class FlipOrientationHandler {
    @objid ("1b6d8ce3-5e33-11e2-b81d-002564c97630")
    @Execute
    public Object execute(MPart part) {
        if (part.getObject() instanceof ILinkEditorView) {
            final ILinkEditorView view = ((ILinkEditorView) part.getObject());
            final ILinkEditor editor = view.getLinkEditor();
            final ILinkEditorConfigurator config = editor.getConfigurator();
            config.setLayoutOrientation(config.getLayoutOrientation() == Orientation.Vertical ? Orientation.Horizontal : Orientation.Vertical);
        }
        return null;
    }

}
