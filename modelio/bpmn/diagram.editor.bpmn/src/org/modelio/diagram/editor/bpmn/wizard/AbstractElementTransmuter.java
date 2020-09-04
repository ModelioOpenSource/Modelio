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

package org.modelio.diagram.editor.bpmn.wizard;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.diagram.editor.bpmn.elements.bpmnboundaryevent.GmBpmnBoundaryEvent;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.common.portcontainer.PortConstraint;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessCollaborationDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.mmextensions.standard.services.MModelServices;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Transmutes an element into another.
 * <p>
 * If the selected object is an {@link EditPart}, its {@link GmModel} is also replaced with the transmuted element's corresponding {@link GmModel}.
 * </p>
 */
@objid ("668fd303-33f7-11e2-95fe-001ec947c8cc")
public abstract class AbstractElementTransmuter implements IModelTransformer {
    @objid ("89d4937d-018b-48f9-90ff-4f98805d064f")
    protected final String targetMetaclass;

    /**
     * Transmute an element into another.
     */
    @objid ("3497729a-1481-4a27-95dc-ad28d935364c")
    protected abstract MObject transmuteElement(MObject elementToBeTransmuted, IMModelServices modelServices);

    /**
     * Transmute a {@link GmModel} into another, keeping:
     * <ul>
     * <li>layout data</li>
     * <li>starting links</li>
     * <li>ending links</li>
     * <li>boundary events</li>
     * </ul>
     */
    @objid ("90c8581e-a5b1-470b-bacf-12a05ce308c6")
    protected GmNodeModel transmuteGm(MObject transmutedElement, GmModel gmToBeTransmuted) {
        // Compute layout constraint for the new node
        GmModel gmPrimaryNodeToBeTransmuted = null;
        Rectangle rec = (Rectangle) gmToBeTransmuted.getLayoutData();
        if (gmToBeTransmuted instanceof GmPortContainer) {
            // For a port container, take the primary node's layout constraint
            gmPrimaryNodeToBeTransmuted = ((GmPortContainer) gmToBeTransmuted).getMainNode();
            rec.setWidth(((Rectangle) gmPrimaryNodeToBeTransmuted.getLayoutData()).width);
            rec.setHeight(((Rectangle) gmPrimaryNodeToBeTransmuted.getLayoutData()).height);
            rec.setX(rec.x + ((Rectangle) gmPrimaryNodeToBeTransmuted.getLayoutData()).x);
            rec.setY(rec.y + ((Rectangle) gmPrimaryNodeToBeTransmuted.getLayoutData()).y);
        } else {
            gmPrimaryNodeToBeTransmuted = gmToBeTransmuted;
        }
        
        // Unmask transmuted element
        GmNodeModel transmutedGm = gmToBeTransmuted.getDiagram().unmask((GmCompositeNode) gmToBeTransmuted.getParent(), transmutedElement, rec);
        
        // Replace the transmuted element with its primary node
        if (!transmutedGm.getRoleInComposition().equals("MainNode") && transmutedGm instanceof GmPortContainer) {
            transmutedGm = ((GmPortContainer) transmutedGm).getMainNode();
        }
        
        // Unmask links
        if (gmPrimaryNodeToBeTransmuted instanceof IGmLinkable) {
            for (IGmLink link : new ArrayList<>(((IGmLinkable) gmPrimaryNodeToBeTransmuted).getStartingLinks())) {
                IGmDiagram diagram = link.getDiagram();
                MObject mLink = link.getRelatedElement();
                GmPath path = new GmPath((IGmPath) link.getLayoutData());
                IGmLinkable to = link.getTo();
                link.delete();
                diagram.unmaskLink(mLink, transmutedGm, to, path);
            }
        
            for (IGmLink link : new ArrayList<>(((IGmLinkable) gmPrimaryNodeToBeTransmuted).getEndingLinks())) {
                IGmDiagram diagram = link.getDiagram();
                MObject mLink = link.getRelatedElement();
                GmPath path = new GmPath((IGmPath) link.getLayoutData());
                IGmLinkable from = link.getFrom();
                link.delete();
                diagram.unmaskLink(mLink, from, transmutedGm, path);
            }
        }
        
        // Unmask bounday events
        if (gmToBeTransmuted instanceof GmPortContainer) {
            for (GmNodeModel child : ((GmPortContainer) gmToBeTransmuted).getChildren()) {
                if (child instanceof GmBpmnBoundaryEvent) {
                    MObject boundaryEvent = child.getRelatedElement();
        
                    // Get layout data
                    GmModel boundaryEventPrimaryNode = ((GmPortContainer) child).getMainNode();
                    Rectangle layoutData = ((PortConstraint) child.getLayoutData()).getRequestedBounds();
                    layoutData.setWidth(((Rectangle) boundaryEventPrimaryNode.getLayoutData()).width);
                    layoutData.setHeight(((Rectangle) boundaryEventPrimaryNode.getLayoutData()).height);
                    layoutData.setX(layoutData.x + ((Rectangle) boundaryEventPrimaryNode.getLayoutData()).x);
                    layoutData.setY(layoutData.y + ((Rectangle) boundaryEventPrimaryNode.getLayoutData()).y);
        
                    child.delete();
        
                    transmutedGm.getDiagram().unmask((GmCompositeNode) transmutedGm.getParent(), boundaryEvent, layoutData);
                }
            }
        }
        return transmutedGm;
    }

    @objid ("a7f29d65-c74a-4ba9-9e28-21a2eab701b2")
    @Override
    public final List<MObject> transform(AbstractDiagram diagram, ISelection selection) {
        MObject elementToBeTransmuted = SelectionHelper.getFirst(selection, MObject.class);
        
        // First, replace the element itself
        MModelServices mmService = new MModelServices(CoreSession.getSession(elementToBeTransmuted));
        MObject transmutedElement = transmuteElement(elementToBeTransmuted, mmService);
        
        // If the selection is an edit part, replace the Gm shown in the diagram too
        EditPart selectedEditPart = SelectionHelper.getFirst(selection, EditPart.class);
        if (selectedEditPart != null) {
            transmuteGm(transmutedElement, (GmModel) selectedEditPart.getModel());
        }
        
        // Delete old element
        elementToBeTransmuted.delete();
        return null;
    }

    @objid ("58ecd897-1664-48d8-b3fb-730128135689")
    @Override
    public boolean isAvailable(AbstractDiagram diagram, ISelection selection) {
        // One selected element only
        if (SelectionHelper.size(selection) != 1) {
            return false;
        }
        
        // In a BPMN diagram
        if (!(diagram instanceof BpmnSubProcessDiagram) && !(diagram instanceof BpmnProcessCollaborationDiagram)) {
            return false;
        }
        
        MObject elt = SelectionHelper.getFirst(selection, MObject.class);
        return elt != null && !this.targetMetaclass.equals(elt.getMClass().getName());
    }

    @objid ("94c81b2a-d813-4ed8-9b5d-ea1e3a51471d")
    @Override
    public boolean canExecute(AbstractDiagram diagram, ISelection selection) {
        return true;
    }

    /**
     * Public constructor required by the {@link TransformerRegistry}.
     * @param targetMetaclass target metaclass of the transmutation
     */
    @objid ("2a06d727-fc3d-4d33-a47b-f591ee48bed8")
    public AbstractElementTransmuter(final String targetMetaclass) {
        this.targetMetaclass = targetMetaclass;
    }

}
