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
import org.modelio.diagram.diagramauto.diagram.layout.FourGroupStructuralLayout;
import org.modelio.diagram.styles.plugin.DiagramStyles;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("022e2845-0bdd-4ce8-8ac3-9e3cd045a67b")
public class DependencyCreator extends AbstractDiagramCreator {
    @objid ("a56a08ff-50a5-4321-993d-6b70518b2f87")
    private IDiagramNode _mainDG;

    @objid ("0801017a-62ff-4f2e-81bb-7eeb458ffe39")
    public List<IDiagramNode> _leftDgs;

    @objid ("3563bc79-2e37-48ba-9743-9565db0b4d36")
    public List<IDiagramNode> _rightDgs;

    @objid ("b2f35d46-bdf0-4b35-af54-f7d51b948ff7")
    public DependencyCreator(IMModelServices modelServices) {
        super(modelServices);
        
        this._leftDgs = new ArrayList<>();
        this._rightDgs = new ArrayList<>();
    }

    @objid ("a9d4794a-2a64-465a-a23e-e96c1e98ffdd")
    @Override
    public String getAutoDiagramName() {
        return "dependency_autodiagram";
    }

    @objid ("87b63944-4c09-4796-ad98-26ac29068603")
    @Override
    public String getAutoDiagramGroup() {
        return "Dependency";
    }

    @objid ("867e48da-87e9-474b-ba3e-5d24cf9739b5")
    @Override
    public ModelElement getAutoDiagramContext(final ModelElement main) {
        MObject owner = main.getCompositionOwner();
        if (owner instanceof AbstractProject) {
            return null;
        }
        return (ModelElement) owner;
    }

    @objid ("a0d02b05-a85b-4782-9fe7-c3ecf4d2235c")
    @Override
    protected void initialUnmasking(final IDiagramHandle dh, final ModelElement main) {
        if (main instanceof NameSpace) {
            initialUnmasking(dh, (NameSpace) main);
        }
    }

    @objid ("400f8ae7-913e-475e-b1cf-b0825a66b1f7")
    private void initialUnmasking(final IDiagramHandle dh, final NameSpace main) {
        // Mask old content
        for (IDiagramNode node : dh.getDiagramNode().getNodes()) {
            node.mask();
        }
        
        // the main element
        this._mainDG = (IDiagramNode) dh.unmask(main, 400, 400).get(0);
        this._mainDG.setSize(100, 100);
        this._mainDG.setRepresentationMode(1);
        initStyle(main, main, this._mainDG);
        
        // Unmask incoming blue links
        for (ImpactLink blueLink : main.getImpactImpacted()) {
            // Unmask user node
            ModelElement parent = blueLink.getImpacted();
            List<IDiagramGraphic> nodes = dh.unmask(parent, 0, 0);
            if ((nodes != null) && (nodes.size() > 0)) {
                IDiagramNode node = (IDiagramNode) nodes.get(0);
        
                // Add intern/extern style
                initStyle(main, parent, node);
        
                this._leftDgs.add(node);
            }
        
            dh.unmask(blueLink, 0, 0);
        }
        
        // Unmask outgoing blue links
        for (ImpactLink blueLink : main.getImpactDependsOn()) {
            // Unmask used node
            ModelElement parent = blueLink.getDependsOn();
            List<IDiagramGraphic> nodes = dh.unmask(parent, 0, 0);
            if ((nodes != null) && (nodes.size() > 0)) {
                IDiagramNode node = (IDiagramNode) nodes.get(0);
        
                // Add intern/extern style
                initStyle(main, parent, node);
        
                this._rightDgs.add(node);
            }
        
            dh.unmask(blueLink, 0, 0);
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
        ModelTree parent1 = getOwnerPackage (elt1);
        ModelTree parent2 = getOwnerPackage (elt2);
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
    protected void layout(final IDiagramHandle dh) {
        if (this._mainDG != null) {
            FourGroupStructuralLayout layout = new FourGroupStructuralLayout();
            try {
                layout.layout(this._mainDG, new ArrayList<IDiagramNode>(), new ArrayList<IDiagramNode>(), this._leftDgs, this._rightDgs, new ArrayList<IDiagramLink>());
            } catch (InvalidSourcePointException | InvalidPointsPathException | InvalidDestinationPointException e) {
                // Should never happen
                e.printStackTrace();
            }
        }
    }

    @objid ("f2e3349a-ceb7-4a52-a5a4-ab0288050f3a")
    @Override
    public ModelElement getMainElement(AbstractDiagram autoDiagram) {
        final ModelElement owner = autoDiagram.getOrigin();
        
        for (MObject child : owner.getCompositionChildren()) {
            if (autoDiagram.getName().equals(child.getName() + " (" + getAutoDiagramName() + ")")) {
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

// The Dependency auto diagram are class diagrams
    @objid ("2396ea3c-1824-4a2f-875b-3c9012bc3911")
    @Override
    protected AbstractDiagram createDiagramElement(IStandardModelFactory standardFactory) {
        return standardFactory.createClassDiagram();
    }

}
