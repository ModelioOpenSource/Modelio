/* 
 * Copyright 2013-2019 Modeliosoft
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
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.diagramauto.diagram.DiagramStyleHandle;
import org.modelio.diagram.diagramauto.diagram.layout.MatrixLayout;
import org.modelio.diagram.styles.plugin.DiagramStyles;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;

@objid ("1309fe15-7a74-4686-908f-2117ea13c1d2")
public class CompositionNavigationCreator extends AbstractDiagramCreator {
    @objid ("e0dccdfe-9032-44d1-8a6e-ca1bd9292894")
    private List<IDiagramNode> contentDgs;

    @objid ("cdcde0aa-53a2-4028-8709-57745d8c23a1")
    public CompositionNavigationCreator(IMModelServices modelServices) {
        super(modelServices);
        this.contentDgs = new ArrayList<>();
    }

    @objid ("d1feaaa2-7e6c-47f1-a810-80644a9ad7be")
    @Override
    public String getAutoDiagramName() {
        return "composition_navigation_autodiagram";
    }

    @objid ("bb7b4e8b-5f14-41a6-a8d4-4efe99523573")
    @Override
    public String getAutoDiagramGroup() {
        return "Structure";
    }

    @objid ("76923692-258b-4edd-b408-88e93b9c158d")
    @Override
    public ModelElement getAutoDiagramContext(final ModelElement main) {
        return main;
    }

    @objid ("5b7ecfc6-7de3-4f35-acb2-3980acf18d27")
    @Override
    protected void initialUnmasking(final IDiagramHandle dh, final ModelElement main) {
        if (main instanceof org.modelio.metamodel.uml.statik.Package) {
            initialUnmasking(dh, (ModelTree) main);
        }
    }

    @objid ("04e7eb26-dda3-4b75-a9cf-7c0c6405370e")
    private void initialUnmasking(final IDiagramHandle dh, final ModelTree main) {
        // Mask previous content
        for (IDiagramNode node : dh.getDiagramNode().getNodes()) {
            node.mask();
        }
        
        // unmask content
        for (ModelTree child : main.getOwnedElement()) {
            if (child instanceof ModelTree) {
                List<IDiagramGraphic> nodes = dh.unmask(child, 0, 0);
                if ((nodes != null) && (nodes.size() > 0)) {
                    IDiagramNode node = (IDiagramNode) nodes.get(0);
                    node.setRepresentationMode(1);
        
                    // Add intern/extern style
                    initStyle(main, node);
        
                    this.contentDgs.add(node);
                }
            }
        }
    }

    @objid ("93f3987c-2be7-47df-b8cf-d763f33aea9c")
    public void initStyle(final ModelTree main, final IDiagramNode node) {
        node.setStyle(new DiagramStyleHandle(DiagramStyles.getStyleManager().getStyle(DiagramStyles.INTERN_STYLE_NAME)));
    }

    @objid ("b4112f60-2238-43a0-a43f-4bc00b411009")
    @Override
    protected void layout(final IDiagramHandle dh) {
        MatrixLayout layout = new MatrixLayout(10, 10);
        layout.layout(dh, this.contentDgs);
    }

    @objid ("c5fbe005-5762-4c92-b02b-a4b141d5acd8")
    @Override
    public ModelElement getMainElement(AbstractDiagram autoDiagram) {
        return autoDiagram.getOrigin();
    }

// The CompositionNavigation auto diagram are class diagrams
    @objid ("c8474bb1-578f-4d4d-b166-721d20c098d8")
    @Override
    protected AbstractDiagram createDiagramElement(IStandardModelFactory standardFactory) {
        return standardFactory.createClassDiagram();
    }

}
