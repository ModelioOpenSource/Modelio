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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.InvalidDestinationPointException;
import org.modelio.api.modelio.diagram.InvalidPointsPathException;
import org.modelio.api.modelio.diagram.InvalidSourcePointException;
import org.modelio.diagram.diagramauto.diagram.DiagramStyleHandle;
import org.modelio.diagram.diagramauto.plugin.DiagramAuto;
import org.modelio.diagram.diagramauto.tools.layout.FourGroupStructuralLayout;
import org.modelio.diagram.styles.plugin.DiagramStyles;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.AggregationKind;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.KindOfAccess;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.VisibilityMode;

@objid ("a5b21ccf-9573-466b-b3b3-3042ac15d720")
public class ClassStructureDiagramTemplate extends AbstractDiagramTemplate {
    @objid ("00fea4a5-ad28-49dc-b1c9-fb6b4b23a715")
    private IDiagramNode _mainDG;

    @objid ("e8ecae6a-6bb3-40fc-843e-8843f7360d0e")
    public List<IDiagramNode> _topDgs;

    @objid ("097c5522-6cfa-41d0-b6ca-b96c1946b59a")
    public List<IDiagramNode> _bottomDgs;

    @objid ("1c0f5166-eac1-4d1b-a916-634ce7877ae9")
    public List<IDiagramNode> _leftDgs;

    @objid ("2c8cf494-8fd5-49e5-857d-33eac4ff13c0")
    public List<IDiagramNode> _rightDgs;

    @objid ("858d626d-9da9-47dc-a753-34d644541ed3")
    public List<IDiagramLink> _reflexiveLinksDgs;

    /**
     * Mandatory default c'tor needed by eclipse when loading the extension point.
     */
    @objid ("bb8cc562-0dc4-4705-9397-26f54eeac028")
    public ClassStructureDiagramTemplate() {
        super();
        this._topDgs = new ArrayList<>();
        this._bottomDgs = new ArrayList<>();
        this._leftDgs = new ArrayList<>();
        this._rightDgs = new ArrayList<>();
        this._reflexiveLinksDgs = new ArrayList<>();
    }

    @objid ("98fb4d8c-f14d-42fa-b81c-ce34186320dd")
    @Override
    public String getOldType() {
        return "structure_autodiagram";
    }

    @objid ("4c943f6e-e87e-46d5-b455-78a8d82dabde")
    @Override
    public ModelElement resolveOrigin(final ModelElement main) {
        if (main instanceof Classifier) {
            return main;
        } else {
            return null;
        }
    }

    @objid ("b2403289-0ed1-492b-870e-e7fb7c4836ec")
    @Override
    protected void generateContent(final IDiagramHandle dh, final ModelElement main) {
        if (main instanceof Classifier) {
            generateContent(dh, (Classifier) main);
        }
    }

    @objid ("ab0e607b-d810-4a62-9947-f7125d99e1f2")
    protected void generateContent(final IDiagramHandle dh, final Classifier main) {
        // Reset
        reset();
        
        // Mask old content
        for (IDiagramNode node : dh.getDiagramNode().getNodes()) {
            node.mask();
        }
        
        // the main element
        this._mainDG = (IDiagramNode) dh.unmask(main, 400, 400).get(0);
        this._mainDG.setSize(100, 100);
        this._mainDG.setRepresentationMode(1);
        initStyle(main, main, this._mainDG);
        
        // unmask generalizations
        for (Generalization g : main.getParent()) {
            // Unmask parent node
            NameSpace parent = g.getSuperType();
            List<IDiagramGraphic> nodes = dh.unmask(parent, 0, 0);
            if ((nodes != null) && (nodes.size() > 0) && nodes.get(0) instanceof IDiagramNode) {
                IDiagramNode node = (IDiagramNode) nodes.get(0);
        
                // Add intern/extern style
                initStyle(main, parent, node);
        
                this._topDgs.add(node);
            }
        
            // Unmask link
            List<IDiagramGraphic> links = dh.unmask(g, 0, 0);
            if ((links != null) && (links.size() > 0)) {
                IDiagramLink link = (IDiagramLink) links.get(0);
        
                if (link.getFrom().equals(link.getTo())) {
                    this._reflexiveLinksDgs.add(link);
                }
            }
        }
        
        // unmask realizations
        for (InterfaceRealization ir : main.getRealized()) {
            // Unmask parent node
            Interface parent = ir.getImplemented();
            List<IDiagramGraphic> nodes = dh.unmask(parent, 0, 0);
            if ((nodes != null) && (nodes.size() > 0) && nodes.get(0) instanceof IDiagramNode) {
                IDiagramNode node = (IDiagramNode) nodes.get(0);
        
                // Add intern/extern style
                initStyle(main, parent, node);
        
                this._topDgs.add(node);
            }
        
            // Unmask link
            List<IDiagramGraphic> links = dh.unmask(ir, 0, 0);
            if ((links != null) && (links.size() > 0)) {
                IDiagramLink link = (IDiagramLink) links.get(0);
        
                if (link.getFrom().equals(link.getTo())) {
                    this._reflexiveLinksDgs.add(link);
                }
            }
        }
        
        // unmask left nodes and links (incoming)
        for (AssociationEnd a : main.getOwnedEnd()) {
        
            AssociationEnd other = a.getOpposite();
            if (other.getSource() != null && (other.getAggregation() != AggregationKind.KINDISASSOCIATION)) {
                // Unmask left node
                Classifier owner = other.getSource() != null ? other.getSource() : a.getTarget();
                List<IDiagramGraphic> nodes = dh.unmask(owner, 0, 0);
                if ((nodes != null) && (nodes.size() > 0) && nodes.get(0) instanceof IDiagramNode) {
                    IDiagramNode node = (IDiagramNode) nodes.get(0);
        
                    // Add intern/extern style
                    initStyle(main, owner, node);
        
                    this._leftDgs.add(node);
                }
        
                // Unmask incoming link
                List<IDiagramGraphic> links = dh.unmask(other.getAssociation(), 0, 0);
                if ((links != null) && (links.size() > 0)) {
                    IDiagramLink link = (IDiagramLink) links.get(0);
        
                    if (link.getFrom().equals(link.getTo())) {
                        this._reflexiveLinksDgs.add(link);
                    }
                }
            }
        }
        
        // unmask right nodes and links (outgoing links)
        for (AssociationEnd a : main.getOwnedEnd()) {
            AssociationEnd other = a.getOpposite();
            if (a.getSource() != null && (other.getAggregation() == AggregationKind.KINDISASSOCIATION)) {
                // Unmask right node
                Classifier owner = other.getSource() != null ? other.getSource() : a.getTarget();
                List<IDiagramGraphic> nodes = dh.unmask(owner, 0, 0);
                if ((nodes != null) && (nodes.size() > 0) && nodes.get(0) instanceof IDiagramNode) {
                    IDiagramNode node = (IDiagramNode) nodes.get(0);
        
                    // Add intern/extern style
                    initStyle(main, owner, node);
        
                    this._rightDgs.add(node);
                }
        
                // Unmask outgoing link
                List<IDiagramGraphic> links = dh.unmask(other.getAssociation(), 0, 0);
                if ((links != null) && (links.size() > 0)) {
                    IDiagramLink link = (IDiagramLink) links.get(0);
        
                    if (link.getFrom().equals(link.getTo())) {
                        this._reflexiveLinksDgs.add(link);
                    }
                }
            }
        }
        
        // remove all inner elements from the diagram
        for (IDiagramNode innerNode : this._mainDG.getNodes()) {
            this._topDgs.remove(innerNode);
            this._bottomDgs.remove(innerNode);
            this._leftDgs.remove(innerNode);
            this._rightDgs.remove(innerNode);
            innerNode.mask();
        }
        
        // unmask attributes
        for (Attribute att : main.getOwnedAttribute()) {
            if (att.getVisibility() == VisibilityMode.PUBLIC || att.getChangeable() != KindOfAccess.ACCESNONE) {
                dh.unmask(att, 0, 0);
            }
        }
    }

    @objid ("1f13dec7-1609-49e7-9ce0-8599f7df529e")
    public void initStyle(final ModelTree main, final ModelTree owner, final IDiagramNode node) {
        if (main.equals(node.getElement())) {
            node.setStyle(new DiagramStyleHandle(DiagramStyles.getStyleManager().getStyle(DiagramStyles.MAIN_STYLE_NAME)));
        } else if (node.getElement().getStatus().isRamc()) {
            node.setStyle(new DiagramStyleHandle(DiagramStyles.getStyleManager().getStyle(DiagramStyles.RAMC_STYLE_NAME)));
        } else if (isInSamePackage(main, owner)) {
            node.setStyle(new DiagramStyleHandle(DiagramStyles.getStyleManager().getStyle(DiagramStyles.INTERN_STYLE_NAME)));
        } else {
            node.setStyle(new DiagramStyleHandle(DiagramStyles.getStyleManager().getStyle(DiagramStyles.EXTERN_STYLE_NAME)));
        }
    }

    @objid ("fa5e18d9-ed2a-44dc-855a-d451673e9e8b")
    private boolean isInSamePackage(final ModelTree elt1, final ModelTree elt2) {
        ModelTree parent1 = getOwnerPackage(elt1);
        ModelTree parent2 = getOwnerPackage(elt2);
        return parent1 != null && parent2 != null && parent1.equals(parent2);
    }

    @objid ("3c8dc91f-6012-4136-8231-21689eeadb7e")
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

    @objid ("6487617e-f437-433b-965e-78b6e5f2ee67")
    @Override
    protected void layout(final IDiagramHandle dh) {
        if (this._mainDG != null) {
            FourGroupStructuralLayout layout = new FourGroupStructuralLayout();
            try {
                layout.layout(this._mainDG, this._topDgs, this._bottomDgs, this._leftDgs, this._rightDgs, this._reflexiveLinksDgs);
            } catch (InvalidSourcePointException | InvalidPointsPathException | InvalidDestinationPointException e) {
                // Should never happen
                DiagramAuto.LOG.debug(e);
            }
        }
    }

    @objid ("27b7e869-5880-4944-b212-6d35aa3c9008")
    @Override
    public ModelElement getMainElement(AbstractDiagram autoDiagram) {
        return autoDiagram.getOrigin();
    }

    @objid ("c51abb17-5d47-4006-a429-52e26c2ff7fb")
    @Override
    protected AbstractDiagram createDiagramElement() {
        IStandardModelFactory standardFactory = this.modelServices.getModelFactory().getFactory(IStandardModelFactory.class);
        return standardFactory.createClassDiagram();
    }

    /**
     * Clear the internal state structures.
     */
    @objid ("8e45961c-2e10-45e8-a79f-d5594b7c60cc")
    @Override
    protected void reset() {
        this._mainDG = null;
        this._topDgs.clear();
        this._bottomDgs.clear();
        this._leftDgs.clear();
        this._rightDgs.clear();
        this._reflexiveLinksDgs.clear();
    }

}
