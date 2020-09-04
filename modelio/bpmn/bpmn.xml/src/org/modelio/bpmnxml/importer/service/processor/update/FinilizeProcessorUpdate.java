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

package org.modelio.bpmnxml.importer.service.processor.update;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.bpmnxml.importer.service.processor.IBPMNImportProcessor;
import org.modelio.bpmnxml.nodes.IFinaliseNode;
import org.modelio.bpmnxml.nodes.ProductionFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("49fbda61-f4f7-4746-9c1e-6494c2afa016")
public class FinilizeProcessorUpdate implements IBPMNImportProcessor {
    @objid ("b7fb5857-5eda-48c3-84fb-c34e35b0417d")
    private IDiagramService diagramService;

    @objid ("175e3e6f-afd9-4128-878a-778590f127eb")
    private ProductionFactory factory;

    @objid ("8b54c5d0-deb0-424d-846d-7a4b1c610eea")
    private Map<String, Object> elementsMap;

    @objid ("fb62664a-3c07-4979-9190-e0f90b52f754")
    private ICoreSession session;

    @objid ("78b07c7f-7180-465d-8f4b-cde5c479db6e")
    public FinilizeProcessorUpdate(ICoreSession session, IDiagramService diagramService, Map<String, Object> elementsMap) {
        this.diagramService = diagramService;
        this.elementsMap = elementsMap;
        this.session = session;
        this.factory = new ProductionFactory();
    }

    @objid ("efb02b93-7bce-4159-8e06-a68c56a9451d")
    @Override
    public MObject process(MObject context, Object jaxbElement) {
        IFinaliseNode node = this.factory.getImportFinalizationNode(jaxbElement);
        if (node != null) {
            node.setElements(this.elementsMap);
            MObject modelioElement = (MObject) this.elementsMap.get(IDUtils.getJaxbId(context, jaxbElement));
            return node.finalizeElement(modelioElement, jaxbElement, this.session, this.diagramService);
        }
        return (MObject) this.elementsMap.get(IDUtils.getJaxbId(context, jaxbElement));
    }

}
