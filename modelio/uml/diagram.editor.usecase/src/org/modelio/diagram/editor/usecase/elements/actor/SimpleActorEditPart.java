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

package org.modelio.diagram.editor.usecase.elements.actor;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.editor.statik.elements.policies.SmartGeneralizationEditPolicy;
import org.modelio.diagram.editor.usecase.plugin.DiagramEditorUseCase;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.ColorizableImageFigure;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.policies.DefaultElementDropEditPolicy;
import org.modelio.diagram.elements.core.policies.SimpleModeDeferringCreateNodePolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;

@objid ("5e428f12-55b7-11e2-877f-002564c97630")
public class SimpleActorEditPart extends AbstractNodeEditPart {
    @objid ("5e44157c-55b7-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        ColorizableImageFigure actorFigure = new ColorizableImageFigure(DiagramEditorUseCase.getImageRegistry()
                                                                                            .getImage(Actor.class.getName()));
        
        // Set default size
        actorFigure.setPreferredSize(64, 64);
        
        // Set style dependent properties
        refreshFromStyle(actorFigure, getModelStyle());
        return actorFigure;
    }

    @objid ("5e441582-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        // Allow drag & drop
        installEditPolicy(ModelElementDropRequest.TYPE, new DefaultElementDropEditPolicy());
        
        // Switch to structured mode when creating elements inside
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new SimpleModeDeferringCreateNodePolicy());
        
        // Allow links
        installEditPolicy(EditPolicy.NODE_ROLE, new SmartGeneralizationEditPolicy());
        
        // Allow notes and constraints
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                          new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
    }

    @objid ("5e441586-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (aFigure instanceof ColorizableImageFigure) {
            if (!switchRepresentationMode()) {
                ColorizableImageFigure cFigure = (ColorizableImageFigure) aFigure;
                final GmModel gmModel = getModel();
                cFigure.setColor(style.getColor(gmModel.getStyleKey(MetaKey.FILLCOLOR)));
        
                super.refreshFromStyle(aFigure, style);
            }
        }
    }

    @objid ("5e44158e-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        GmActorPrimaryNode model = (GmActorPrimaryNode) this.getModel();
        this.getFigure().getParent().setConstraint(this.getFigure(), model.getLayoutData());
    }

    @objid ("5e441592-55b7-11e2-877f-002564c97630")
    @Override
    protected List<Object> getModelChildren() {
        return Collections.emptyList();
    }

    @objid ("5e441599-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

}
