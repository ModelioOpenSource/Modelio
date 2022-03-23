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
import org.modelio.diagram.diagramauto.tools.layout.DiagonalLayout;
import org.modelio.diagram.styles.plugin.DiagramStyles;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;

@objid ("6792b1d3-82cc-4635-93d4-e7c7f4aab619")
public class PackageContentStructureDiagramTemplate extends AbstractDiagramTemplate {
    @objid ("e8bbf0e1-5f23-4c80-99f0-86c4cf561a43")
    public List<IDiagramNode> _contentDgs;

    /**
     * Mandatory default c'tor needed by eclipse when loading the extension point.
     */
    @objid ("f0acf05c-f837-4cca-b24f-2945ec08b40f")
    public  PackageContentStructureDiagramTemplate() {
        super();
        
        this._contentDgs = new ArrayList<>();
        
    }

    @objid ("7788d30f-0a7f-46b2-90e0-371fb226d7b0")
    @Override
    public String getOldType() {
        return "structure_autodiagram";
    }

    @objid ("2a7cbb54-ffbe-4f59-bf7b-d91e17e1296f")
    @Override
    public ModelElement resolveOrigin(final ModelElement main) {
        if (main instanceof NameSpace) {
            return main;
        } else {
            return null;
        }
        
    }

    @objid ("f3c9db29-c938-47bf-9093-e09f2ef0d4eb")
    @Override
    protected void generateContent(final IDiagramHandle dh, final ModelElement main) {
        if (main instanceof Package) {
            initialUnmasking(dh, (ModelTree) main);
        }
        
    }

    @objid ("be7be4b6-123d-417a-a2aa-2eadf505ccce")
    private void initialUnmasking(final IDiagramHandle dh, final ModelTree main) {
        // Mask old content
        for (IDiagramNode node : dh.getDiagramNode().getNodes()) {
            node.mask();
        }
        
        // unmask content
        for (ModelTree child : main.getOwnedElement()) {
            // Ignore packages
            if (child instanceof Package) {
                continue;
            }
        
            List<IDiagramGraphic> nodes = dh.unmask(child, 0, 0);
            if ((nodes != null) && (nodes.size() > 0) && nodes.get(0) instanceof IDiagramNode) {
                IDiagramNode node = (IDiagramNode) nodes.get(0);
                node.setRepresentationMode(1);
        
                // Add intern/extern style
                initStyle(main, node);
        
                this._contentDgs.add(node);
            }
        }
        
        // Unmask generalizations, realizations & associations
        for (ModelTree child : main.getOwnedElement()) {
            if (child instanceof NameSpace) {
                // Unmask generalizations
                for (Generalization g : ((NameSpace)child).getParent()) {
                    // Get already unmasked parent node only
                    NameSpace parent = g.getSuperType();
                    List<IDiagramGraphic> nodes = dh.getDiagramGraphics(parent);
                    if ((nodes == null) || (nodes.isEmpty())) {
                        continue;
                    }
        
                    // Unmask link
                    List<IDiagramGraphic> links = dh.unmask(g, 0, 0);
                    if ((links != null) && (links.size() > 0)) {
                        IDiagramLink link = (IDiagramLink) links.get(0);
        
                        // Ignore reflexive links
                        if (link.getFrom().equals(link.getTo())) {
                            link.mask();
                        }
                    }
                }
        
                // Unmask realizations
                for (InterfaceRealization ir : ((NameSpace)child).getRealized()) {
                    // Get already unmasked parent node only
                    Interface parent = ir.getImplemented();
                    List<IDiagramGraphic> nodes = dh.getDiagramGraphics(parent);
                    if ((nodes == null) || (nodes.isEmpty())) {
                        continue;
                    }
        
                    // Unmask link
                    List<IDiagramGraphic> links = dh.unmask(ir, 0, 0);
                    if ((links != null) && (links.size() > 0)) {
                        IDiagramLink link = (IDiagramLink) links.get(0);
        
                        // Ignore reflexive links
                        if (link.getFrom().equals(link.getTo())) {
                            link.mask();
                        }
        
                        // TODO layout ?
                    }
                }
            }
        
            if (child instanceof Classifier) {
                // unmask associations
                for (AssociationEnd a : ((Classifier)child).getOwnedEnd()) {
                    AssociationEnd other = a.getOpposite();
        
                    // Ignore nodes that are not unmasked
                    Classifier owner = other.getSource() != null ?  other.getSource() : a.getTarget();
                    List<IDiagramGraphic> nodes = dh.getDiagramGraphics(owner);
                    if ((nodes == null) || (nodes.isEmpty())) {
                        continue;
                    }
        
                    // Unmask link
                    List<IDiagramGraphic>  links = dh.unmask(other.getAssociation(), 0, 0);
                    if ((links != null) && (links.size() > 0)) {
                        IDiagramLink link = (IDiagramLink) links.get(0);
        
                        // Ignore reflexive links
                        if (link.getFrom().equals(link.getTo())) {
                            link.mask();
                        }
        
                        // TODO layout ?
                    }
                }
            }
        }
        
    }

    @objid ("aa037884-d4c6-4984-a6b7-ba8d6445af16")
    public void initStyle(final ModelTree main, final IDiagramNode node) {
        if (main.equals(node.getElement())) {
            node.setStyle(new DiagramStyleHandle(DiagramStyles.getStyleManager().getStyle(DiagramStyles.MAIN_STYLE_NAME)));
        } else if (node.getElement().getStatus().isRamc()) {
            node.setStyle(new DiagramStyleHandle(DiagramStyles.getStyleManager().getStyle(DiagramStyles.RAMC_STYLE_NAME)));
        } else {
            node.setStyle(new DiagramStyleHandle(DiagramStyles.getStyleManager().getStyle(DiagramStyles.INTERN_STYLE_NAME)));
        }
        
    }

    @objid ("e2d8e1ba-0e37-4486-8db1-7f08860e44d6")
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

    @objid ("4ab4a0a2-6c84-4a87-b330-5c17541a38fc")
    @Override
    public ModelElement getMainElement(AbstractDiagram autoDiagram) {
        return autoDiagram.getOrigin();
    }

    @objid ("3ed6354f-149b-4995-8cf6-d72d190495c5")
    @Override
    protected AbstractDiagram createDiagramElement() {
        IStandardModelFactory standardFactory = this.modelServices.getModelFactory().getFactory(IStandardModelFactory.class);
        return standardFactory.createClassDiagram();
    }

    /**
     * Clear the internal state structures.
     */
    @objid ("8622f578-9915-4de0-b7e8-8eae113e1b57")
    @Override
    protected void reset() {
        this._contentDgs.clear();
    }

}
