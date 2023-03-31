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
import org.modelio.diagram.diagramauto.tools.layout.NodeRollingUnmasker;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Package;

@objid ("27b26f94-4c5c-46e5-a220-21c62258f5ce")
public class UseCaseFocusDiagramTemplate extends AbstractDiagramTemplate {
    @objid ("56f60fa8-127e-41e4-8c54-2e288b3700e3")
    private IDiagramNode _ucDG;

    /**
     * The DGs representing the actors associated with the focused UseCase
     */
    @objid ("2d9a6d80-a83c-497d-894a-8a3cf0a8411d")
    public List<IDiagramNode> _actorsDgs;

    /**
     * The Dgs representing the Actor-UseCase associations
     */
    @objid ("9615fa9e-9f9a-495e-94f6-db02d427d92f")
    public List<IDiagramLink> _linksDgs;

    /**
     * The DGs of the bounds use case, ie : extended, and includes use cases. The focused UseCase itself is NOT in this this list.
     */
    @objid ("09091676-0898-4c3a-9b50-79c47009b69f")
    public List<IDiagramNode> _linkedUseCaseDgs;

    /**
     * The DGs of the parent use case
     */
    @objid ("51eb0003-e616-4ad0-8c9f-a26d1f321c55")
    public List<IDiagramNode> _parentUseCaseDgs;

    /**
     * A NodeRollingUnmasker that avoids unmasking nodes on each other which could results in erroneous re-parenting
     */
    @objid ("c52f7ccf-b197-4cc8-8774-395c03f87248")
    protected NodeRollingUnmasker _unmasker;

    /**
     * Mandatory default c'tor needed by eclipse when loading the extension point.
     */
    @objid ("6e4174f9-90bb-47d6-b944-e7e60dc502cf")
    public  UseCaseFocusDiagramTemplate() {
        super();
        
        this._actorsDgs = new ArrayList<>();
        this._linksDgs = new ArrayList<>();
        this._parentUseCaseDgs = new ArrayList<>();
        this._linkedUseCaseDgs = new ArrayList<>();
        this._unmasker = new NodeRollingUnmasker();
        
    }

    @objid ("9f7e7dc8-a3c9-42bd-a042-026136719b2c")
    @Override
    public String getOldType() {
        return "use-case-focus";
    }

    @objid ("33578f7d-05c5-4825-8b24-5cabbd4a43a7")
    @Override
    public ModelElement resolveOrigin(final ModelElement main) {
        return main;
    }

    @objid ("fc50fd84-771c-4ed4-97a2-56c1f9c77e6d")
    @Override
    protected void generateNodesContent(final IDiagramHandle dh, final ModelElement main) {
        // Get rid of dumb case
        if (!(main instanceof UseCase)) {
            return;
        }
        
        UseCase uc = (UseCase) main;
        
        
        
        // The focused UseCase
        this._ucDG = this._unmasker.unmask(dh, uc, 100, 100);
        
        // Unmask the "left" nodes : the actors associated to the use case
        // Need to explore both incoming and outgoing associations because orientation is completely ignored in use case diagrams
        // First, links from an Actor to the UseCase
        for (AssociationEnd a : uc.getTargetingEnd()) {
            Classifier e = a.getSource();
            if (e != null && e instanceof Actor) {
                // Unmask actor
                IDiagramNode node = this._unmasker.unmask(dh, e);
                if (node != null)
                    this._actorsDgs.add(node);
            }
        
            // Unmask the link
            // List<IDiagramGraphic> linkDgs = dh.unmask(a.getAssociation(), 0, 0);
            // if ((linkDgs != null) && (linkDgs.size() > 0)) {
            // IDiagramLink link = (IDiagramLink) linkDgs.get(0);
            // this._linksDgs.add(link);
            // }
        
        }
        
        // Then, links from the UseCase to an Actor
        for (AssociationEnd a : uc.getOwnedEnd()) {
            Classifier e = a.isNavigable() ? a.getTarget() : a.getOpposite().getSource();
            if (e != null && e instanceof Actor) {
                // Unmask actor
                IDiagramNode node = this._unmasker.unmask(dh, e);
                if (node != null)
                    this._actorsDgs.add(node);
            }
        
            // Unmask the link
            // Association link = a.getAssociation();
            // List<IDiagramGraphic> linkDgs = dh.unmask(link, 0, 0);
            // if ((linkDgs != null) && (linkDgs.size() > 0)) {
            // IDiagramLink linkDg = (IDiagramLink) linkDgs.get(0);
            // this._linksDgs.add(linkDg);
            // }
        
        }
        
        // Unmask the 'top' nodes which are the inherited UseCases
        for (Generalization g : uc.getParent()) {
            // Unmask the node
            IDiagramNode dg = this._unmasker.unmask(dh, g.getSuperType());
            if (dg != null)
                this._parentUseCaseDgs.add(dg);
            // unmask the link
            // List<IDiagramGraphic> links = dh.unmask(g, 0, 0);
            // if ((links != null) && (links.size() > 0)) {
            // IDiagramLink link = (IDiagramLink) links.get(0);
            // this._linksDgs.add(link);
            // }
        }
        
        // Unmask the 'left' nodes which are the included and extended UseCases
        for (UseCaseDependency d : uc.getUsed()) {
            // Unmask the node
            IDiagramNode dg = this._unmasker.unmask(dh, d.getTarget());
            if (dg != null)
                this._linkedUseCaseDgs.add(dg);
            // unmask the link
            // List<IDiagramGraphic> links = dh.unmask(d, 0, 0);
            // if ((links != null) && (links.size() > 0)) {
            // IDiagramLink link = (IDiagramLink) links.get(0);
            // this._linksDgs.add(link);
            // }
        }
        
    }

    @objid ("1dae599f-a875-407a-a4ee-c6c1b3dcd3d7")
    @Override
    protected void generateLinksContent(final IDiagramHandle dh, final ModelElement main) {
        // Get rid of dumb case
        if (!(main instanceof UseCase)) {
            return;
        }
        
        UseCase uc = (UseCase) main;
        
        // The focused UseCase
        this._ucDG = this._unmasker.unmask(dh, uc, 100, 100);
        
        // Unmask the "left" nodes : the actors associated to the use case
        // Need to explore both incoming and outgoing associations because orientation is completely ignored in use case diagrams
        // First, links from an Actor to the UseCase
        for (AssociationEnd a : uc.getTargetingEnd()) {
            // Unmask the link
            List<IDiagramGraphic> linkDgs = dh.unmask(a.getAssociation(), 0, 0);
            if ((linkDgs != null) && (linkDgs.size() > 0)) {
                IDiagramLink link = (IDiagramLink) linkDgs.get(0);
                this._linksDgs.add(link);
            }
        }
        
        // Then, links from the UseCase to an Actor
        for (AssociationEnd a : uc.getOwnedEnd()) {
            // Unmask the link
            Association link = a.getAssociation();
            List<IDiagramGraphic> linkDgs = dh.unmask(link, 0, 0);
            if ((linkDgs != null) && (linkDgs.size() > 0)) {
                IDiagramLink linkDg = (IDiagramLink) linkDgs.get(0);
                this._linksDgs.add(linkDg);
            }
        
        }
        
        // Unmask the 'top' nodes which are the inherited UseCases
        for (Generalization g : uc.getParent()) {
            // Unmask the link
            List<IDiagramGraphic> links = dh.unmask(g, 0, 0);
            if ((links != null) && (links.size() > 0)) {
                IDiagramLink link = (IDiagramLink) links.get(0);
                this._linksDgs.add(link);
            }
        }
        
        // Unmask the 'left' nodes which are the included and extended UseCases
        for (UseCaseDependency d : uc.getUsed()) {
            // Unmask the link
            List<IDiagramGraphic> links = dh.unmask(d, 0, 0);
            if ((links != null) && (links.size() > 0)) {
                IDiagramLink link = (IDiagramLink) links.get(0);
                this._linksDgs.add(link);
            }
        }
        
    }

    @objid ("116308bf-987f-4887-926a-b05cca2d2356")
    private boolean isInSamePackage(final ModelTree elt1, final ModelTree elt2) {
        ModelTree parent1 = getOwnerPackage(elt1);
        ModelTree parent2 = getOwnerPackage(elt2);
        return parent1 != null && parent2 != null && parent1.equals(parent2);
    }

    @objid ("64fde413-9ae4-4e9f-b0e9-bf8c34d70ced")
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

    @objid ("beeb701c-0708-4ab2-84b0-8b5c13ffded5")
    @Override
    protected void layoutNodes(final IDiagramHandle dh) {
        if (this._ucDG != null) {
            UseCaseFocusLayout layout = new UseCaseFocusLayout();
            try {
                layout.layoutNodes(this._ucDG, this._actorsDgs, this._parentUseCaseDgs, this._linkedUseCaseDgs, this._linksDgs);
            } catch (Exception e) {
                // Should never happen
                DiagramAuto.LOG.debug(e);
            }
        }
        
    }

    @objid ("5be8318f-0cf5-40a3-ae1e-987b50bdcbbe")
    @Override
    protected void layoutLinks(final IDiagramHandle dh) {
        if (this._ucDG != null) {
            UseCaseFocusLayout layout = new UseCaseFocusLayout();
            try {
                layout.layoutLinks(this._ucDG, this._actorsDgs, this._parentUseCaseDgs, this._linkedUseCaseDgs, this._linksDgs);
            } catch (Exception e) {
                // Should never happen
                DiagramAuto.LOG.debug(e);
            }
        }
        
    }

    @objid ("015232ce-21a9-4487-ac8b-ecbaf9264642")
    @Override
    public ModelElement getMainElement(AbstractDiagram autoDiagram) {
        return autoDiagram.getOrigin();
    }

    @objid ("415b2977-4e17-446d-a847-a9aa707ee3a1")
    @Override
    protected AbstractDiagram createDiagramElement() {
        IStandardModelFactory standardFactory = this.modelServices.getModelFactory().getFactory(IStandardModelFactory.class);
        return standardFactory.createUseCaseDiagram();
    }

    /**
     * Clear the internal state structures.
     */
    @objid ("fa07e7b7-66ab-452e-9749-93dd0ad92f84")
    @Override
    protected void reset() {
        this._ucDG = null;
        this._actorsDgs.clear();
        this._linksDgs.clear();
        this._parentUseCaseDgs.clear();
        this._linkedUseCaseDgs.clear();
        this._unmasker = new NodeRollingUnmasker();
        
    }

    @objid ("d37d0da0-c7c4-4bc6-ad5e-700fab4a7c82")
    private static class UseCaseFocusLayout {
        @objid ("8b225f2c-a817-4c68-860a-65e61d6af860")
        private static final int BLOCK_H_SPACING = 120;

        @objid ("d8f5e6eb-bc81-42ea-b3ac-6c2ad633e5c8")
        private static final int NODE_H_SPACING = 10;

        @objid ("64758412-d46d-47ed-a59b-2ae1175ce473")
        private static final int NODE_V_SPACING = 25;

        @objid ("a70a3a98-d06d-4d64-9c97-4543c76d81e0")
        public void layoutNodes(IDiagramNode ucDg, List<IDiagramNode> actorsDgs, List<IDiagramNode> parentUseCaseDgs, List<IDiagramNode> linkedUseCaseDgs, List<IDiagramLink> linksDgs) {
            DgNodeGroup leftGroup = new DgNodeGroup(actorsDgs);
            DgNodeGroup rightGroup = new DgNodeGroup(linkedUseCaseDgs);
            DgNodeGroup topGroup = new DgNodeGroup(parentUseCaseDgs);
            
            // Layout Actors : left DGs
            leftGroup.vLayout(0, 0, NODE_V_SPACING);
            
            // Layout linked UseCase: right DGs
            rightGroup.vLayout(0, 0, NODE_V_SPACING);
            
            // Layout parent UseCases : topDgs
            topGroup.hLayout(0, 0, NODE_H_SPACING);
            
            Rectangle leftBlock = leftGroup.getBounds();
            Rectangle rightBlock = rightGroup.getBounds();
            Rectangle topBLock = topGroup.getBounds();
            
            // Position the focused UseCase
            ucDg.fitToContent();
            double xUc = leftBlock.preciseWidth() + max(topBLock.preciseWidth(), ucDg.getBounds().preciseWidth()) / 2;
            double yUc = topBLock.preciseHeight() + max(leftBlock.preciseHeight(), ucDg.getBounds().preciseWidth(), rightBlock.preciseHeight()) / 2;
            ucDg.setLocation((int) xUc, (int) yUc);
            
            // Position the top block
            double xTop = ucDg.getBounds().preciseX() + ucDg.getBounds().preciseWidth() / 2 - topBLock.preciseWidth() / 2; // centered on focused UseCase
            double yTop = ucDg.getBounds().preciseY() - ucDg.getBounds().preciseHeight() / 2 -
                    max(leftBlock.preciseWidth(), ucDg.getBounds().preciseWidth(), rightBlock.preciseHeight()) / 2;
            topGroup.moveTo(xTop, yTop);
            
            // Position the left block
            double xLeft = ucDg.getBounds().preciseX() + ucDg.getBounds().preciseWidth() / 2 -
                    max(ucDg.getBounds().preciseWidth(), topBLock.preciseWidth()) / 2 -
                    leftBlock.preciseWidth() - BLOCK_H_SPACING;
            double yLeft = ucDg.getBounds().preciseY() + ucDg.getBounds().preciseHeight() / 2 - leftBlock.preciseHeight() / 2;
            leftGroup.moveTo(xLeft, yLeft);
            
            // Position the right block
            double xRight = ucDg.getBounds().preciseX() + ucDg.getBounds().preciseWidth() / 2 +
                    max(ucDg.getBounds().preciseWidth() / 2 + BLOCK_H_SPACING, topBLock.preciseWidth() / 2 + BLOCK_H_SPACING);
            double yRight = ucDg.getBounds().preciseY() + ucDg.getBounds().preciseHeight() / 2 - rightBlock.preciseHeight() / 2;
            rightGroup.moveTo(xRight, yRight);
            
        }

        @objid ("6ccffc6f-6d59-473a-a8e2-b7fc3140faa9")
        public void layoutLinks(IDiagramNode ucDg, List<IDiagramNode> actorsDgs, List<IDiagramNode> parentUseCaseDgs, List<IDiagramNode> linkedUseCaseDgs, List<IDiagramLink> linksDgs) {
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

        @objid ("e16c2008-ea7b-45f2-9adf-fbd4a5148b29")
        private double max(double a, double b, double c) {
            return Math.max(Math.max(a, b), c);
        }

        @objid ("7f79719b-2c99-4613-b3ba-4f6269314d60")
        private double max(double a, double b) {
            return Math.max(a, b);
        }

    }

}
