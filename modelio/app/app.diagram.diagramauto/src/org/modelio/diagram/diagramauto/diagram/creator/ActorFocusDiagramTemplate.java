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
package org.modelio.diagram.diagramauto.diagram.creator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramLink.LinkRouterKind;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.diagramauto.plugin.DiagramAuto;
import org.modelio.diagram.diagramauto.tools.groups.DgNodeGroup;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Package;

@objid ("cb580394-83b9-4a66-8196-e2dbf8d16b49")
public class ActorFocusDiagramTemplate extends AbstractDiagramTemplate {
    /**
     * the DG representing the focused Actor
     */
    @objid ("f3d3a48d-dc36-4979-8c08-896903fa588a")
    private IDiagramNode _actorDG;

    /**
     * The Dgs representing the Actor-UseCase associations
     */
    @objid ("7812b4a5-a56c-46c1-afaa-ac30e0a883ba")
    public List<IDiagramLink> _linksDgs;

    /**
     * The DGs of the bounds use case, ie the use case the focused Actor is involved in.
     */
    @objid ("6c6784c2-28c7-460d-ad0c-cf8d4ced4edb")
    public List<IDiagramNode> _linkedUseCaseDgs;

    /**
     * Mandatory default c'tor needed by eclipse when loading the extension point.
     */
    @objid ("a596718a-fdaf-437c-8d2b-d04770c5d963")
    public  ActorFocusDiagramTemplate() {
        super();
        this._linksDgs = new ArrayList<>();
        this._linkedUseCaseDgs = new ArrayList<>();
        
    }

    @objid ("a9ea0071-c9b6-4fa4-8e3e-cf7ea8ed1de6")
    @Override
    public String getOldType() {
        return "actor-focus";
    }

    @objid ("ed103c6b-1a10-4cd0-8470-124fad5175d7")
    @Override
    public ModelElement resolveOrigin(final ModelElement main) {
        return main;
    }

    @objid ("48374ecb-a934-4f73-be7c-daa28a4358cd")
    @Override
    protected void generateContent(final IDiagramHandle dh, final ModelElement main) {
        if (main instanceof Actor) {
            initialUnmasking(dh, (Actor) main);
        }
        
    }

    @objid ("5a583caf-4ed0-4c84-9a86-019600a8e9d2")
    protected void initialUnmasking(final IDiagramHandle dh, final Actor actor) {
        // Mask old content
        for (IDiagramNode node : dh.getDiagramNode().getNodes()) {
            node.mask();
        }
        
        // The focused UseCase
        this._actorDG = (IDiagramNode) dh.unmask(actor, 400, 400).get(0);
        this._actorDG.fitToContent();
        
        // Unmask the "left" nodes : the usecase associated to the actor
        // Need to explore both incoming and outgoing associations because orientation is completely ignored in use case diagrams
        
        // First, links from an Actor to the UseCase
        for (AssociationEnd a : actor.getTargetingEnd()) {
            Classifier e = a.getSource();
            if (e != null && e instanceof UseCase) {
                // Unmask usecase
                List<IDiagramGraphic> nodes = dh.unmask(e, 0, 0);
                if ((nodes != null) && (nodes.size() > 0) && nodes.get(0) instanceof IDiagramNode) {
                    IDiagramNode node = (IDiagramNode) nodes.get(0);
                    this._linkedUseCaseDgs.add(node);
                }
        
                // Unmask the link
                List<IDiagramGraphic> linkDgs = dh.unmask(a.getAssociation(), 0, 0);
                if ((linkDgs != null) && (linkDgs.size() > 0)) {
                    IDiagramLink link = (IDiagramLink) linkDgs.get(0);
                    this._linksDgs.add(link);
                }
        
            }
        }
        
        // Then, links from the UseCase to an Actor
        for (AssociationEnd a : actor.getOwnedEnd()) {
            Classifier e = a.isNavigable() ? a.getTarget() : a.getOpposite().getSource();
            if (e != null && e instanceof UseCase) {
                // Unmask use case
                List<IDiagramGraphic> nodes = dh.unmask(e, 0, 0);
                if ((nodes != null) && (nodes.size() > 0) && nodes.get(0) instanceof IDiagramNode) {
                    IDiagramNode node = (IDiagramNode) nodes.get(0);
                    this._linkedUseCaseDgs.add(node);
                }
        
                // Unmask the link
                Association link = a.getAssociation();
                List<IDiagramGraphic> linkDgs = dh.unmask(link, 0, 0);
                if ((linkDgs != null) && (linkDgs.size() > 0)) {
                    IDiagramLink linkDg = (IDiagramLink) linkDgs.get(0);
                    this._linksDgs.add(linkDg);
                }
            }
        }
        
    }

    @objid ("33273501-f12d-4b2c-8254-3692e0873ae1")
    private boolean isInSamePackage(final ModelTree elt1, final ModelTree elt2) {
        ModelTree parent1 = getOwnerPackage(elt1);
        ModelTree parent2 = getOwnerPackage(elt2);
        return parent1 != null && parent2 != null && parent1.equals(parent2);
    }

    @objid ("aad0ea87-f760-403c-b531-c003868e1df8")
    private ModelTree getOwnerPackage(final ModelTree elt) {
        ModelTree parent = elt.getOwner();
        
        // Take parents for Inner elements
        while ((parent != null)) {
            if (parent instanceof Package) {
                return parent;
            } else {
                parent = parent.getOwner();
            }
        }
        return parent;
    }

    @objid ("c196d88a-4653-4bd1-9790-b6f55f407ab7")
    @Override
    protected void layout(final IDiagramHandle dh) {
        if (this._actorDG != null) {
            UseCaseFocusLayout layout = new UseCaseFocusLayout();
            try {
                layout.layout(this._actorDG, this._linkedUseCaseDgs, this._linksDgs);
            } catch (Exception e) {
                // Should never happen
                DiagramAuto.LOG.debug(e);
            }
        }
        
    }

    @objid ("2e68029e-e6b8-4e9b-a607-b82127d784a0")
    @Override
    public ModelElement getMainElement(AbstractDiagram autoDiagram) {
        return autoDiagram.getOrigin();
    }

    @objid ("9073a86b-cff0-4de9-a56e-70c3eb62cebe")
    @Override
    protected AbstractDiagram createDiagramElement() {
        IStandardModelFactory standardFactory = this.modelServices.getModelFactory().getFactory(IStandardModelFactory.class);
        return standardFactory.createUseCaseDiagram();
    }

    /**
     * Clear the internal state structures.
     */
    @objid ("4f6adecf-da24-4011-afd1-326c2b57da8c")
    @Override
    protected void reset() {
        this._actorDG = null;
        this._linksDgs.clear();
        this._linkedUseCaseDgs.clear();
        
    }

    @objid ("2bd596c0-5f7f-4050-ba17-730349b95e93")
    private static class UseCaseFocusLayout {
        @objid ("0122a67a-a534-4132-befb-687d81b9ccd2")
        private static final int BLOCK_H_SPACING = 150;

        @objid ("1e4f8cc5-cbc9-4376-8609-eff980bb698d")
        private static final int NODE_H_SPACING = 10;

        @objid ("fe592af8-72a6-48dd-9dce-e735438fad00")
        private static final int NODE_V_SPACING = 25;

        @objid ("2d8a6c19-41f1-4ba9-98bc-44a1ff168ab4")
        public void layout(IDiagramNode actorDg, List<IDiagramNode> linkedUseCaseDgs, List<IDiagramLink> linksDgs) {
            DgNodeGroup rightGroup = new DgNodeGroup(linkedUseCaseDgs);
            
            // Layout Actors : right DGs
            rightGroup.vLayout(0, 0, NODE_V_SPACING);
            
            Rectangle rightBlock = rightGroup.getBounds();
            
            // Position the focused Actor
            actorDg.fitToContent();
            double xUc = 0;
            double yUc = max(rightBlock.preciseHeight(), actorDg.getBounds().preciseHeight()) / 2;
            actorDg.setLocation((int) xUc, (int) yUc);
            
            // Position the right block
            double xRight = actorDg.getBounds().preciseX() + actorDg.getBounds().preciseWidth() +  BLOCK_H_SPACING;
            double yRight = actorDg.getBounds().preciseY() + actorDg.getBounds().preciseHeight() / 2 - rightBlock.preciseHeight() / 2;
            rightGroup.moveTo(xRight, yRight);
            
            // Route links
            for (IDiagramLink linkDg : linksDgs) {
                if (linkDg.getFrom() instanceof IDiagramNode && linkDg.getTo() instanceof IDiagramNode) {
                    linkDg.setRouterKind(LinkRouterKind.DIRECT);
                    IDiagramNode from = (IDiagramNode) linkDg.getFrom();
                    IDiagramNode to = (IDiagramNode) linkDg.getTo();
                    List<Point> points = Arrays.asList(new Point(from.getBounds().getCenter()), new Point(to.getBounds().getCenter()));
                    linkDg.setPath(points);
                }
            }
            
        }

        @objid ("652a4bb0-a30b-4c64-864b-e965b336b855")
        private double max(double a, double b, double c) {
            return Math.max(Math.max(a, b), c);
        }

        @objid ("7744c768-82cc-433e-bb9c-664b0fc0fe92")
        private double max(double a, double b) {
            return Math.max(a, b);
        }

    }

}
