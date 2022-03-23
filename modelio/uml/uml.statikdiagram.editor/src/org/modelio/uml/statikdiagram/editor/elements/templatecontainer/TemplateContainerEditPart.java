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
package org.modelio.uml.statikdiagram.editor.elements.templatecontainer;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.modelio.diagram.elements.common.portcontainer.PortContainerEditPart;
import org.modelio.diagram.elements.common.portcontainer.SatelliteChildrenSelectionPolicy;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.uml.statikdiagram.editor.elements.packaze.ContentAsSatelliteDragTrackerProvider;

/**
 * Edit policy for {@link GmTemplateContainer}.
 * <p>
 * Allows creation of template parameters.
 * <p>
 * Edit parts for classes deriving from {@link GmTemplateContainer} must derive from this class.
 * 
 * @author cmarin
 */
@objid ("36e3d2be-55b7-11e2-877f-002564c97630")
public class TemplateContainerEditPart extends PortContainerEditPart {
    @objid ("36e3d2c2-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy("Create template parameters", new CreateTemplateParameterEditPolicy());
        final GmTemplateContainer gmModel = (GmTemplateContainer) getModel();
        GmNodeModel mainNode = gmModel.getMainNode();
        if (mainNode != null) {
            // FIXME filtering simple mode isn't enough, Interfaces for example are always in simple mode and need the link between the main node and the label...
            // final RepresentationMode askedMode = mainNode.getRepresentationMode();
            // if (askedMode == RepresentationMode.SIMPLE) {
            //   // We don't want the dashed line appearing when selecting one of the children in simple mode, we already have the composition link for that!
            //   removeEditPolicy("satellite selection");
            // } else {
            installEditPolicy("satellite selection", new SatelliteChildrenSelectionPolicy());
            // }
        }
        
    }

    @objid ("36e3d2c5-55b7-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(final EditPart childEditPart, final int index) {
        super.addChildVisual(childEditPart, index);
        final GmNodeModel childModel = (GmNodeModel) childEditPart.getModel();
        if ("body content as satellite".equals(childModel.getRoleInComposition())) {
            ((AbstractNodeEditPart) childEditPart).setDragTrackerProvider(new ContentAsSatelliteDragTrackerProvider(childEditPart));
        }
        
    }

}
