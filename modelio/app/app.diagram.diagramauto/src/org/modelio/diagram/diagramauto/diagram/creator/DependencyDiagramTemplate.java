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
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.InvalidDestinationPointException;
import org.modelio.api.modelio.diagram.InvalidPointsPathException;
import org.modelio.api.modelio.diagram.InvalidSourcePointException;
import org.modelio.diagram.diagramauto.diagram.DiagramStyleHandle;
import org.modelio.diagram.diagramauto.plugin.DiagramAuto;
import org.modelio.diagram.diagramauto.tools.layout.FourGroupStructuralLayout;
import org.modelio.diagram.diagramauto.tools.layout.NodeRollingUnmasker;
import org.modelio.diagram.styles.plugin.DiagramStyles;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("022e2845-0bdd-4ce8-8ac3-9e3cd045a67b")
public class DependencyDiagramTemplate extends AbstractDiagramTemplate {
    @objid ("a56a08ff-50a5-4321-993d-6b70518b2f87")
    private IDiagramNode _mainDG;

    @objid ("0801017a-62ff-4f2e-81bb-7eeb458ffe39")
    public List<IDiagramNode> _leftDgs;

    @objid ("3563bc79-2e37-48ba-9743-9565db0b4d36")
    public List<IDiagramNode> _rightDgs;

    /**
     * A NodeRollingUnmasker that avoids unmasking nodes on each other which could results in erroneous re-parenting
     */
    @objid ("911de682-94c0-4ad6-87dd-21c5e2d5ace1")
    protected NodeRollingUnmasker _unmasker;

    /**
     * Mandatory default c'tor needed by eclipse when loading the extension point.
     */
    @objid ("b2f35d46-bdf0-4b35-af54-f7d51b948ff7")
    public  DependencyDiagramTemplate() {
        super();
        
        this._leftDgs = new ArrayList<>();
        this._rightDgs = new ArrayList<>();
        this._unmasker = new NodeRollingUnmasker();
        
    }

    @objid ("a9d4794a-2a64-465a-a23e-e96c1e98ffdd")
    @Override
    public String getOldType() {
        return "dependency_autodiagram";
    }

    @objid ("867e48da-87e9-474b-ba3e-5d24cf9739b5")
    @Override
    public ModelElement resolveOrigin(final ModelElement main) {
        MObject owner = main.getCompositionOwner();
        if (owner instanceof AbstractProject) {
            return null;
        } else if (owner instanceof UmlModelElement) {
            return (ModelElement) owner;
        } else {
            return null;
        }
        
    }

    @objid ("c6761c35-0916-4fc1-b117-718a0f936235")
    @Override
    protected void generateNodesContent(final IDiagramHandle dh, final ModelElement elt) {
        // Get rid of dumb case
        if (!(elt instanceof NameSpace))
            return;
        
        NameSpace main = (NameSpace) elt;
        
        // the main element
        this._mainDG = this._unmasker.unmask(dh, main, 100, 100);
        if (_mainDG != null) {
            this._mainDG.setRepresentationMode(1);
            initStyle(main, main, this._mainDG);
        }
        
        // Unmask incoming blue links
        for (ImpactLink blueLink : main.getImpactImpacted()) {
            // Unmask user node unless it belongs to main
            ModelElement impactedElement = blueLink.getImpacted();
            if (!impactedElement.getCompositionOwner().equals(main)) {
                IDiagramNode node = this._unmasker.unmask(dh, impactedElement);
                if (node != null) {
                    // Add intern/extern style
                    initStyle(main, impactedElement, node);
                    this._leftDgs.add(node);
                }
            }
        }
        
        // Unmask outgoing blue links
        for (ImpactLink blueLink : main.getImpactDependsOn()) {
            // Unmask used node unless it belongs to main
            ModelElement dependsOn = blueLink.getDependsOn();
            if (!dependsOn.getCompositionOwner().equals(main)) {
                IDiagramNode node = this._unmasker.unmask(dh, dependsOn);
                if (node != null) {
                    // Add intern/extern style
                    initStyle(main, dependsOn, node);
                    this._rightDgs.add(node);
                }
            }
        
        }
        
    }

    @objid ("a950b10a-e17a-4b2e-9e8f-9e32a93a0ad0")
    protected void generateLinksContent(final IDiagramHandle dh, final ModelElement elt) {
        // Get rid of dumb case
        if (!(elt instanceof NameSpace))
            return;
        
        NameSpace main = (NameSpace) elt;
        
        // Unmask incoming blue links unless source belongs to main (inner)
        for (ImpactLink blueLink : main.getImpactImpacted()) {
            ModelElement impactedElement = blueLink.getImpacted();
            if (!impactedElement.getCompositionOwner().equals(main)) {
                dh.unmask(blueLink, 0, 0);
            }
        }
        
        // Unmask outgoing blue links unless target belongs to main (inner)
        for (ImpactLink blueLink : main.getImpactDependsOn()) {
            ModelElement dependsOn = blueLink.getDependsOn();
            if (!dependsOn.getCompositionOwner().equals(main)) {
                dh.unmask(blueLink, 0, 0);
            }
        }
        
    }

    @objid ("b3141c26-8030-4a98-8b08-f6ef2d0dc987")
    public void initStyle(final ModelElement main, final ModelElement owner, final IDiagramNode node) {
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

    @objid ("b257d36c-39d5-454c-b926-655b901448a9")
    private boolean isInSamePackage(final ModelElement elt1, final ModelElement elt2) {
        ModelTree parent1 = getOwnerPackage(elt1);
        ModelTree parent2 = getOwnerPackage(elt2);
        return parent1 != null && parent2 != null && parent1.equals(parent2);
    }

    @objid ("061f4d3d-a4e7-4e2a-b1d6-c4b6d7d09c9e")
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

    @objid ("7fa6ffe7-4fff-4737-bb83-b49de80f67d4")
    @Override
    protected void layoutNodes(final IDiagramHandle dh) {
        // The 'FourGroupNodeLayout' used here implies that the node layout depends on the unmasked links (in order to place the nodes where links can be correctly routed).
        // At this stage the links are not unmasked yet and their DGs not existing,
        // therefore the node layout is postponed to the layoutLinks() call where we are sure that the link DGs are unmasked and accessible.
        
    }

    @objid ("a99c731b-44b7-40e1-8e51-c491ae2202a8")
    @Override
    protected void layoutLinks(final IDiagramHandle dh) {
        if (this._mainDG != null) {
            FourGroupStructuralLayout layout = new FourGroupStructuralLayout();
            try {
                layout.layout(this._mainDG, new ArrayList<IDiagramNode>(), new ArrayList<IDiagramNode>(), this._leftDgs, this._rightDgs, new ArrayList<IDiagramLink>());
            } catch (InvalidSourcePointException | InvalidPointsPathException | InvalidDestinationPointException e) {
                // Should never happen
                DiagramAuto.LOG.debug(e);
            }
        }
        
    }

    @objid ("f2e3349a-ceb7-4a52-a5a4-ab0288050f3a")
    @Override
    public ModelElement getMainElement(AbstractDiagram autoDiagram) {
        final ModelElement owner = autoDiagram.getOrigin();
        
        for (MObject child : owner.getCompositionChildren()) {
            String expectedName = child.getName() + " (" + getId() + ")";
            if (autoDiagram.getName().equals(expectedName)) {
                return (ModelElement) child;
            }
        
            // Migration case
            String oldExpectedName = child.getName() + " (" + getId() + ")";
            if (autoDiagram.getName().equals(oldExpectedName)) {
                return (ModelElement) child;
            }
        }
        return null;
    }

    @objid ("283d86d9-a19f-4e31-9493-31e569dd1869")
    private ModelTree getOwnerPackage(final ModelElement elt) {
        MObject parent = elt.getCompositionOwner();
        
        // Take parents for Inner elements
        while ((parent != null)) {
            if (parent instanceof Package) {
                return (Package) parent;
            } else if (parent instanceof ModelTree) {
                return getOwnerPackage((ModelTree) parent);
            } else {
                parent = parent.getCompositionOwner();
            }
        }
        return null;
    }

    @objid ("2396ea3c-1824-4a2f-875b-3c9012bc3911")
    @Override
    protected AbstractDiagram createDiagramElement() {
        IStandardModelFactory standardFactory = this.modelServices.getModelFactory().getFactory(IStandardModelFactory.class);
        return standardFactory.createClassDiagram();
    }

    /**
     * Clear the internal state structures.
     */
    @objid ("f208669d-cf55-461c-a70b-ee735a51ad83")
    @Override
    protected void reset() {
        this._mainDG = null;
        this._leftDgs.clear();
        this._rightDgs.clear();
        this._unmasker = new NodeRollingUnmasker();
        
    }

}
