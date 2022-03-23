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
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.InvalidDestinationPointException;
import org.modelio.api.modelio.diagram.InvalidPointsPathException;
import org.modelio.api.modelio.diagram.InvalidSourcePointException;
import org.modelio.diagram.diagramauto.diagram.DiagramStyleHandle;
import org.modelio.diagram.diagramauto.plugin.DiagramAuto;
import org.modelio.diagram.diagramauto.tools.layout.DiagonalLayout;
import org.modelio.diagram.styles.plugin.DiagramStyles;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.Package;

/**
 * Model View template for to create a SubPackage Structure diagram.
 * <p>
 * A SubPackage Structure diagram shows the internal structure of a {@link Package} or {@link Component} in terms of packages, and the dependencies between them. It includes:
 * <ul>
 * <li>Its sub-packages.</li>
 * <li>The blue links (impact links) between those sub-packages.</li>
 * </ul>
 * </p>
 */
@objid ("6ea3c15a-6a80-4418-84db-93923707e9bf")
public class SubPackageStructureDiagramTemplate extends AbstractDiagramTemplate {
    @objid ("dc2c84aa-e4e4-46c3-b441-fc2d525bedac")
    private List<IDiagramNode> _contentDgs;

    /**
     * Mandatory default c'tor needed by eclipse when loading the extension point.
     */
    @objid ("77d98f85-f2ae-4863-9c99-ff93bc09fdd7")
    public  SubPackageStructureDiagramTemplate() {
        super();
        
        this._contentDgs = new ArrayList<>();
        
    }

    @objid ("59fdfe02-8d2b-4997-9a0b-dcba038ae62e")
    @Override
    public String getOldType() {
        return "subpackage_structure_autodiagram";
    }

    @objid ("98aa1323-e088-489a-b929-14600654ef44")
    @Override
    public ModelElement resolveOrigin(final ModelElement main) {
        if (main instanceof Package || main instanceof Component) {
            return main;
        } else {
            return null;
        }
        
    }

    @objid ("732f94cd-fcb5-4e1a-beb8-64c887bb201a")
    @Override
    protected void generateContent(final IDiagramHandle dh, final ModelElement main) {
        if (main instanceof Package || main instanceof Component) {
            initialUnmasking(dh, (ModelTree) main);
        }
        
    }

    @objid ("bab7ec8a-a51d-4d5d-8b0b-a260c093b0e3")
    private void initialUnmasking(final IDiagramHandle dh, final ModelTree main) {
        // Mask old content
        for (IDiagramNode node : dh.getDiagramNode().getNodes()) {
            node.mask();
        }
        
        // unmask content
        for (Package child : main.getOwnedElement(Package.class)) {
            List<IDiagramGraphic> nodes = dh.unmask(child, 0, 0);
            if (nodes != null && nodes.size() > 0 && nodes.get(0) instanceof IDiagramNode) {
                IDiagramNode node = (IDiagramNode) nodes.get(0);
                node.setRepresentationMode(1);
        
                // Add intern/extern style
                initStyle(main, node);
        
                this._contentDgs.add(node);
            }
        
        }
        
        // Unmask blue links
        for (Package child : main.getOwnedElement(Package.class)) {
            for (ImpactLink blueLink : getImpactLinkToElement(child)) {
                dh.unmask(blueLink, 0, 0);
            }
        }
        
    }

    @objid ("452ce988-c665-4fa5-8592-06fa9d23a7d0")
    private List<ImpactLink> getImpactLinkToElement(final Package element) {
        List<ImpactLink> nsus = new ArrayList<>();
        for (ImpactLink impactLink : element.getImpactDependsOn()) {
            final ModelElement used = impactLink.getDependsOn();
            if (used instanceof Package && isInSamePackage(element, (Package) used)) {
                nsus.add(impactLink);
            }
        }
        return nsus;
    }

    @objid ("433e8b3f-389d-4650-86c6-ec223b11dd9f")
    public void initStyle(final ModelTree main, final IDiagramNode node) {
        if (main.equals(node.getElement())) {
            node.setStyle(new DiagramStyleHandle(DiagramStyles.getStyleManager().getStyle(DiagramStyles.MAIN_STYLE_NAME)));
        } else if (node.getElement().getStatus().isRamc()) {
            node.setStyle(new DiagramStyleHandle(DiagramStyles.getStyleManager().getStyle(DiagramStyles.RAMC_STYLE_NAME)));
        } else {
            node.setStyle(new DiagramStyleHandle(DiagramStyles.getStyleManager().getStyle(DiagramStyles.INTERN_STYLE_NAME)));
        }
        
    }

    @objid ("a719621f-9d96-4064-9bfd-7aaf907ec95b")
    private boolean isInSamePackage(final Package elt1, final Package elt2) {
        return Objects.equals(elt1.getOwner(), elt2.getOwner());
    }

    @objid ("c658a4b2-8d7f-4ba0-910c-6b4c0c045cb6")
    @Override
    protected void layout(final IDiagramHandle dh) {
        DiagonalLayout layout = new DiagonalLayout();
        try {
            layout.layout(dh, this._contentDgs);
        } catch (InvalidSourcePointException | InvalidPointsPathException | InvalidDestinationPointException e) {
            // Should never happen
            DiagramAuto.LOG.debug(e);
        }
        
    }

    @objid ("9e009f97-c1e1-442c-9b68-b2b84ef068c1")
    @Override
    public ModelElement getMainElement(AbstractDiagram autoDiagram) {
        return autoDiagram.getOrigin();
    }

    @objid ("185a59bf-f7f1-41c0-a50e-449d01d25b6d")
    @Override
    protected AbstractDiagram createDiagramElement() {
        IStandardModelFactory standardFactory = this.modelServices.getModelFactory().getFactory(IStandardModelFactory.class);
        return standardFactory.createClassDiagram();
    }

    /**
     * Clear the internal state structures.
     */
    @objid ("692305a6-dd88-4b12-a440-a3d047595c8c")
    @Override
    protected void reset() {
        this._contentDgs.clear();
    }

}
