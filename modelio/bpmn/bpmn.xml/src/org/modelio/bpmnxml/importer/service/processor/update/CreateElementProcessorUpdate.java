/* 
 * Copyright 2013-2018 Modeliosoft
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.bpmnxml.importer.service.processor.IBPMNImportProcessor;
import org.modelio.bpmnxml.model.TDefinitions;
import org.modelio.bpmnxml.nodes.IProduction;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.nodes.ProductionFactory;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("9d96c33c-e834-4276-bbe0-96f04ce6bb82")
public class CreateElementProcessorUpdate implements IBPMNImportProcessor {
    @objid ("2656c70a-e55d-42d6-8b29-2ae85c8ce289")
    private boolean keepId;

    @objid ("bab07484-86bd-49d9-8126-e2a4e544a4a6")
    private ICoreSession session;

    @objid ("22b9b9fa-54b7-4515-a5f6-f54b11d04d3d")
    private IDiagramService diagramService;

    @objid ("5b62d109-9f95-4b56-bdc2-3403647249c5")
    private ProductionFactory factory;

    @objid ("b57b941b-3eb8-400f-be54-91c40c6d3d3e")
    private Map<String, Object> elementsMap;

    @objid ("8c1eadf0-e772-4595-9bd7-28a27e8b2e94")
    public List<Object> getCreatedElements() {
        return new ArrayList<>(this.elementsMap.values());
    }

    @objid ("55f5cbe2-3cc4-4fcd-a4c6-92ee61dfda31")
    public CreateElementProcessorUpdate(ICoreSession session, IDiagramService diagramService, Map<String, Object> elementsMap, boolean keepId) {
        this.session = session;
        this.diagramService = diagramService;
        this.elementsMap = elementsMap;
        this.factory = new ProductionFactory();
        this.keepId = keepId;
    }

    /**
     * Create Modelio element from a JaxbElement
     */
    @objid ("91da92da-4be9-4fb0-9e69-ebdf08546b9a")
    @Override
    public MObject process(MObject context, Object jaxbElement) {
        IProduction node = this.factory.getImportProductionNode(jaxbElement);
        if (node instanceof IProductionNode) {
            node.setElements(this.elementsMap);
        
            MObject modelioElement = null;
            if (jaxbElement instanceof TDefinitions) {
                modelioElement = context;
            } else {
        
                if (this.keepId) {
                    modelioElement = ((IProductionNode) node).findUMLElementById(jaxbElement, this.session);
                    if (modelioElement != null && modelioElement.isDeleted()) {
                        ((SmObjectImpl) modelioElement).getMetaOf().objUndeleted((SmObjectImpl) modelioElement);
                    }
                }
                if (modelioElement == null) {
                    BpmnImportFactory elementFactory = new BpmnImportFactory((CoreSession) this.session);
                    modelioElement = ((IProductionNode) node).createUMLElement(context, jaxbElement, elementFactory, this.keepId);
                }
            }
        
            this.elementsMap.put(IDUtils.getJaxbId(context, jaxbElement), modelioElement);
        
            return modelioElement;
        }
        return null;
    }

}
