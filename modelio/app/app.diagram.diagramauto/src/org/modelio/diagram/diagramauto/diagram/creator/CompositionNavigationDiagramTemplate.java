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
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.diagramauto.diagram.DiagramStyleHandle;
import org.modelio.diagram.diagramauto.tools.layout.MatrixLayout;
import org.modelio.diagram.diagramauto.tools.layout.NodeRollingUnmasker;
import org.modelio.diagram.styles.plugin.DiagramStyles;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;

@objid ("1309fe15-7a74-4686-908f-2117ea13c1d2")
public class CompositionNavigationDiagramTemplate extends AbstractDiagramTemplate {
    @objid ("e0dccdfe-9032-44d1-8a6e-ca1bd9292894")
    private List<IDiagramNode> contentDgs;

    /**
     * A NodeRollingUnmasker that avoids unmasking nodes on each other which could results in erroneous re-parenting
     */
    @objid ("33b4d1c8-32ee-4c73-a49b-8f4c0f1defce")
    protected NodeRollingUnmasker _unmasker;

    /**
     * Mandatory default c'tor needed by eclipse when loading the extension point.
     */
    @objid ("cdcde0aa-53a2-4028-8709-57745d8c23a1")
    public  CompositionNavigationDiagramTemplate() {
        super();
        this.contentDgs = new ArrayList<>();
        this._unmasker = new NodeRollingUnmasker();
        
    }

    @objid ("d1feaaa2-7e6c-47f1-a810-80644a9ad7be")
    @Override
    public String getOldType() {
        return "composition_navigation_autodiagram";
    }

    @objid ("76923692-258b-4edd-b408-88e93b9c158d")
    @Override
    public ModelElement resolveOrigin(final ModelElement main) {
        return main;
    }

    @objid ("5b7ecfc6-7de3-4f35-acb2-3980acf18d27")
    @Override
    protected void generateNodesContent(final IDiagramHandle dh, final ModelElement elt) {
        if (!(elt instanceof org.modelio.metamodel.uml.statik.Package))
            return;
        
        org.modelio.metamodel.uml.statik.Package main = (org.modelio.metamodel.uml.statik.Package) elt;
        
        
        
        // Unmask content
        for (ModelTree child : main.getOwnedElement()) {
            IDiagramNode node = this._unmasker.unmask(dh, child);
            if (node != null) {
                node.setRepresentationMode(1);
                // Add intern/extern style
                initStyle(main, node);
                this.contentDgs.add(node);
            }
        }
        
    }

    @objid ("2fc96964-568d-44fd-8b9b-cbf0b9681f3d")
    @Override
    protected void generateLinksContent(final IDiagramHandle dh, final ModelElement elt) {
        
    }

    @objid ("93f3987c-2be7-47df-b8cf-d763f33aea9c")
    public void initStyle(final ModelTree main, final IDiagramNode node) {
        node.setStyle(new DiagramStyleHandle(DiagramStyles.getStyleManager().getStyle(DiagramStyles.INTERN_STYLE_NAME)));
    }

    @objid ("b4112f60-2238-43a0-a43f-4bc00b411009")
    @Override
    protected void layoutNodes(final IDiagramHandle dh) {
        MatrixLayout layout = new MatrixLayout(10, 10);
        layout.layout(dh, this.contentDgs);
        
    }

    @objid ("6ebd1453-8be6-4c02-9766-8081b6624d93")
    @Override
    protected void layoutLinks(final IDiagramHandle dh) {
        return;
    }

    @objid ("c5fbe005-5762-4c92-b02b-a4b141d5acd8")
    @Override
    public ModelElement getMainElement(AbstractDiagram autoDiagram) {
        return autoDiagram.getOrigin();
    }

    @objid ("c8474bb1-578f-4d4d-b166-721d20c098d8")
    @Override
    protected AbstractDiagram createDiagramElement() {
        IStandardModelFactory standardFactory = this.modelServices.getModelFactory().getFactory(IStandardModelFactory.class);
        return standardFactory.createClassDiagram();
    }

    /**
     * Clear the internal state structures.
     */
    @objid ("98d5138e-0798-4ad4-81fa-b2ea729ae63e")
    @Override
    protected void reset() {
        this.contentDgs.clear();
        this._unmasker = new NodeRollingUnmasker();
        
    }

}
