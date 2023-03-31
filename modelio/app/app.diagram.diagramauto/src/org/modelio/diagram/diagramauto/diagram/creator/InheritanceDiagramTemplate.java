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
import org.modelio.diagram.diagramauto.tools.layout.NodeRollingUnmasker;
import org.modelio.diagram.styles.plugin.DiagramStyles;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;

@objid ("fe674e41-0430-4d92-87b7-d1c75d038a1a")
public class InheritanceDiagramTemplate extends AbstractDiagramTemplate {
    @objid ("00fd185c-4f3d-4f24-bf6c-a1ad144cdda7")
    private IDiagramNode _mainDG;

    @objid ("c3d5467e-f3e8-485f-a80a-65d18dfc5029")
    public List<IDiagramNode> _topDgs;

    @objid ("40aab172-8e21-4917-a261-f796bddc9b2d")
    public List<IDiagramNode> _bottomDgs;

    /**
     * A NodeRollingUnmasker that avoids unmasking nodes on each other which could results in erroneous re-parenting
     */
    @objid ("5d5bb8f1-68b4-4b94-a7f0-ea786e2d3117")
    protected NodeRollingUnmasker _unmasker;

    /**
     * Mandatory default c'tor needed by eclipse when loading the extension point.
     */
    @objid ("a436b93d-adab-4107-9730-74415cfc432b")
    public  InheritanceDiagramTemplate() {
        super();
        
        this._topDgs = new ArrayList<>();
        this._bottomDgs = new ArrayList<>();
        this._unmasker = new NodeRollingUnmasker();
        
    }

    @objid ("6db9a607-04db-4a04-9c49-c3065dee1193")
    @Override
    public String getOldType() {
        return "inheritance_autodiagram";
    }

    @objid ("6ec0a483-c84f-4870-a5af-ac7d18ab9a8f")
    @Override
    public ModelElement resolveOrigin(final ModelElement main) {
        if (main instanceof Classifier) {
            return main;
        } else {
            return null;
        }
        
    }

    @objid ("70d93d5b-448a-4b53-9c99-6588a98c9228")
    @Override
    protected void generateNodesContent(final IDiagramHandle dh, final ModelElement elt) {
        if (!(elt instanceof Classifier))
            return;
        NameSpace main = (NameSpace) elt;
        
        // the main element
        this._mainDG = (IDiagramNode) dh.unmask(main, 400, 400).get(0);
        this._mainDG.setSize(100, 100);
        this._mainDG.setRepresentationMode(1);
        initStyle(main, main, this._mainDG);
        
        // unmask parent generalizations
        for (Generalization g : main.getParent()) {
            // Unmask parent node
            NameSpace parent = g.getSuperType();
        
            IDiagramNode node = this._unmasker.unmask(dh, parent);
            if (node != null) {
                // Add intern/extern style
                initStyle(main, parent, node);
        
                this._topDgs.add(node);
        
            }
        
        }
        
        // unmask realized realizations
        for (InterfaceRealization ir : main.getRealized()) {
            // Unmask parent node
            Interface parent = ir.getImplemented();
        
            IDiagramNode node = this._unmasker.unmask(dh, parent);
            if (node != null) {
                // Add intern/extern style
                initStyle(main, parent, node);
                this._topDgs.add(node);
            }
        
        
        }
        
        // unmask specialized generalizations
        for (Generalization g : main.getSpecialization()) {
            // Unmask child node
            NameSpace child = g.getSubType();
        
            IDiagramNode node = this._unmasker.unmask(dh, child);
            if (node != null) {
                // Add intern/extern style
                initStyle(main, child, node);
                this._bottomDgs.add(node);
            }
        
        
        }
        
        // unmask realized realizations
        if (main instanceof Interface) {
            for (InterfaceRealization ir : ((Interface) main).getImplementedLink()) {
                // Unmask child node
                NameSpace child = ir.getImplementer();
                IDiagramNode node = this._unmasker.unmask(dh, child);
                if (node != null) {
                    // Add intern/extern style
                    initStyle(main, child, node);
                    this._bottomDgs.add(node);
                }
        
        
            }
        }
        
        // remove all inner elements from the diagram
        for (IDiagramNode innerNode : this._mainDG.getNodes()) {
            this._topDgs.remove(innerNode);
            this._bottomDgs.remove(innerNode);
            innerNode.mask();
        }
        
    }

    @objid ("d016eb9b-7e71-4661-9ea2-3660bd1c3ecb")
    @Override
    protected void generateLinksContent(final IDiagramHandle dh, final ModelElement elt) {
        if (!(elt instanceof Classifier))
            return;
        NameSpace main = (NameSpace) elt;
        
        // unmask parent generalizations
        for (Generalization g : main.getParent()) {
            // Unmask link
            List<IDiagramGraphic> links = dh.unmask(g, 0, 0);
            if ((links != null) && (links.size() > 0)) {
                IDiagramLink link = (IDiagramLink) links.get(0);
        
                if (link.getFrom().equals(link.getTo())) {
                    link.mask();
                }
            }
        }
        
        // unmask realized realizations
        for (InterfaceRealization ir : main.getRealized()) {
            // Unmask link
            List<IDiagramGraphic> links = dh.unmask(ir, 0, 0);
            if ((links != null) && (links.size() > 0)) {
                IDiagramLink link = (IDiagramLink) links.get(0);
        
                if (link.getFrom().equals(link.getTo())) {
                    link.mask();
                }
            }
        }
        
        // unmask specialized generalizations
        for (Generalization g : main.getSpecialization()) {
            // Unmask link
            List<IDiagramGraphic> links = dh.unmask(g, 0, 0);
            if ((links != null) && (links.size() > 0)) {
                IDiagramLink link = (IDiagramLink) links.get(0);
        
                if (link.getFrom().equals(link.getTo())) {
                    link.mask();
                }
            }
        }
        
        // unmask realized realizations
        if (main instanceof Interface) {
            for (InterfaceRealization ir : ((Interface) main).getImplementedLink()) {
                // Unmask link
                List<IDiagramGraphic> links = dh.unmask(ir, 0, 0);
                if ((links != null) && (links.size() > 0)) {
                    IDiagramLink link = (IDiagramLink) links.get(0);
        
                    if (link.getFrom().equals(link.getTo())) {
                        link.mask();
                    }
                }
            }
        }
        
        // remove all inner elements from the diagram
        for (IDiagramNode innerNode : this._mainDG.getNodes()) {
            this._topDgs.remove(innerNode);
            this._bottomDgs.remove(innerNode);
            innerNode.mask();
        }
        
    }

    @objid ("d8e4ab56-fb39-45f5-a0ea-c52a2dff52f0")
    private void initialUnmasking(final IDiagramHandle dh, final NameSpace main) {
        
    }

    @objid ("1a7d63b1-2635-4cd1-bea2-1dcf028cdecf")
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

    @objid ("0fefc119-863e-4c34-a0bd-199a07f05f46")
    private boolean isInSamePackage(final ModelTree elt1, final ModelTree elt2) {
        ModelTree parent1 = getOwnerPackage(elt1);
        ModelTree parent2 = getOwnerPackage(elt2);
        return parent1 != null && parent2 != null && parent1.equals(parent2);
    }

    @objid ("85a9d9d5-59f6-4a1a-ad2c-d1b75fd7a851")
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

    @objid ("a741f775-141d-47e0-bcb5-8bf356259c97")
    @Override
    protected void layoutNodes(final IDiagramHandle dh) {
        // Deferred to layoutLinks()
    }

    @objid ("5b400d77-a78e-4a83-b4d0-2f9da2f6b3ae")
    @Override
    protected void layoutLinks(final IDiagramHandle dh) {
        if (this._mainDG != null) {
            FourGroupStructuralLayout layout = new FourGroupStructuralLayout();
            try {
                layout.layout(this._mainDG, this._topDgs, this._bottomDgs, new ArrayList<IDiagramNode>(), new ArrayList<IDiagramNode>(), new ArrayList<IDiagramLink>());
            } catch (InvalidSourcePointException | InvalidPointsPathException | InvalidDestinationPointException e) {
                // Should never happen
                DiagramAuto.LOG.debug(e);
            }
        }
        
    }

    @objid ("3f65618b-c449-46c3-b9c0-02baaa300225")
    @Override
    public ModelElement getMainElement(AbstractDiagram autoDiagram) {
        return autoDiagram.getOrigin();
    }

    @objid ("41386d77-e491-4744-bf93-88b2ac00e8be")
    @Override
    protected AbstractDiagram createDiagramElement() {
        IStandardModelFactory standardFactory = this.modelServices.getModelFactory().getFactory(IStandardModelFactory.class);
        return standardFactory.createClassDiagram();
    }

    /**
     * Clear the internal state structures.
     */
    @objid ("49d1ff40-ae85-4bb0-9c31-92876d2eb0c5")
    @Override
    protected void reset() {
        this._topDgs.clear();
        this._bottomDgs.clear();
        this._mainDG = null;
        this._unmasker = new NodeRollingUnmasker();
        
    }

}
