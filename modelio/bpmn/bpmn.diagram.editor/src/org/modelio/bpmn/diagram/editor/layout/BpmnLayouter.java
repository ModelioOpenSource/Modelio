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
package org.modelio.bpmn.diagram.editor.layout;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.Modelio;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("231ad8dc-2da6-4c72-96df-b126b5ca639c")
public class BpmnLayouter {
    @objid ("baf855e1-3909-4d2d-9681-83e55805f4f4")
    private AbstractDiagram diagram;

    @objid ("9efce84f-987f-4d17-9d42-e0b2c2e4e2eb")
    public  BpmnLayouter(AbstractDiagram diagram) {
        this.diagram = diagram;
    }

    @objid ("fc509bf7-e8ec-4b8c-be21-b6ed79aca148")
    public void run() {
        try (IDiagramHandle dh = Modelio.getInstance().getDiagramService().getDiagramHandle(this.diagram)) {
        
            for (IDiagramNode n : dh.getDiagramNode().getNodes()) {
                for (MObject flow : ((BpmnBaseElement) n.getElement()).getOutgoingFlow()) {
                    dh.unmask(flow, 0, 0);
                }
                for (MObject flow : ((BpmnBaseElement) n.getElement()).getIncomingFlow()) {
                    dh.unmask(flow, 0, 0);
                }
            }
        
            Layouter layouter = new Layouter(dh);
            LayoutModel layoutModel = layouter.layout();
        
            Renderer renderer = new Renderer(dh);
            renderer.render(layoutModel);
        
            dh.save();
        }
        
    }

}
