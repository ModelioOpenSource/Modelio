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

package org.modelio.diagram.editor.bpmn.elements.bpmncallactivity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.header.ModelElementHeaderEditPart;
import org.modelio.diagram.elements.common.header.WrappedHeaderFigure;

/**
 * Edit part of classifier header.
 */
@objid ("6095c343-55b6-11e2-877f-002564c97630")
public class BpmnCallActivityHeaderEditPart extends ModelElementHeaderEditPart {
    @objid ("6095c347-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        WrappedHeaderFigure fig = (WrappedHeaderFigure) getFigure();
        final GmBpmnCallActivityHeader noteModel = (GmBpmnCallActivityHeader) getModel();
        fig.setLeftIcons(noteModel.getRepresentedIcon());
    }

}
