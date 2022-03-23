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
package org.modelio.uml.sequencediagram.editor.elements.lifeline.header;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.modelio.diagram.elements.common.header.ModelElementHeaderEditPart;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.RectangularFigure;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;

/**
 * Specialisation of the default edit part for a header wrapping the header figure inside a {@link RectangularFigure} and adding the handling of Image representation mode.
 * 
 * @author fpoyer
 */
@objid ("d9483333-55b6-11e2-877f-002564c97630")
public class LifelineHeaderEditPart extends ModelElementHeaderEditPart {
    @objid ("d9483337-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        LifelineHeaderFigure lifelineHeaderFigure = new LifelineHeaderFigure();
        
        // Set style independent properties.
        lifelineHeaderFigure.setOpaque(true);
        
        // Set style dependenty properties.
        refreshFromStyle(lifelineHeaderFigure, ((GmAbstractObject) getModel()).getDisplayedStyle());
        return lifelineHeaderFigure;
    }

    @objid ("d948333c-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
        
    }

    @objid ("d948333f-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        final LifelineHeaderFigure headerFigure = (LifelineHeaderFigure) getFigure();
        final GmLifelineHeader headerModel = (GmLifelineHeader) getModel();
        
        // Main label
        refreshMetaclassKeyword(headerFigure, headerModel);
        
    }

    @objid ("d9483342-55b6-11e2-877f-002564c97630")
    protected void refreshMetaclassKeyword(final LifelineHeaderFigure headerFigure, final GmLifelineHeader headerModel) {
        if (headerModel.isShowMetaclassKeyword()) {
            headerFigure.setKeywordLabel("<<" + headerModel.getRelatedElement().getMClass().getName() + ">>");
        } else {
            headerFigure.setKeywordLabel(headerModel.computeSecondaryLabel());
        }
        
    }

}
