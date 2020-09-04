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

package org.modelio.diagram.editor.bpmn.wizard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.metamodel.bpmn.activities.BpmnAdHocSubProcess;
import org.modelio.metamodel.bpmn.activities.BpmnBusinessRuleTask;
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;
import org.modelio.metamodel.bpmn.activities.BpmnManualTask;
import org.modelio.metamodel.bpmn.activities.BpmnReceiveTask;
import org.modelio.metamodel.bpmn.activities.BpmnScriptTask;
import org.modelio.metamodel.bpmn.activities.BpmnSendTask;
import org.modelio.metamodel.bpmn.activities.BpmnServiceTask;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.activities.BpmnTask;
import org.modelio.metamodel.bpmn.activities.BpmnTransaction;
import org.modelio.metamodel.bpmn.activities.BpmnUserTask;
import org.modelio.metamodel.bpmn.gateways.BpmnComplexGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnEventBasedGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnExclusiveGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnInclusiveGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnParallelGateway;
import org.modelio.metamodel.diagrams.AbstractDiagram;

@objid ("e6960473-00ee-4aac-90be-8b7a8d0dcfc6")
public class TransformerRegistry {
    @objid ("948e305b-ef65-4ff2-9c79-b6ccd6563eb7")
    private List<ModelTransformerCommand> transformers = new ArrayList<>();

    @objid ("32fd47b0-6364-4a3c-826a-fb2fb7a00a11")
    private static TransformerRegistry INSTANCE;

    @objid ("08b84b6a-4ed8-433d-ab45-3f6626a774b7")
    private TransformerRegistry(IEclipseContext context) {
        int index = 0;
        this.transformers.add(new ModelTransformerCommand(index++, "$GroupAsSubProcessTransformer", "org.modelio.ModelTransformCommand", new GroupAsSubProcessTransformer()));
        
        this.transformers.add(new ModelTransformerCommand(index++, "$UndoSubProcessTransformer", "org.modelio.ModelTransformCommand", new UndoSubProcessTransformer()));
        
        this.transformers.add(new ModelTransformerCommand(index++, "$ActivityTransmuter.BpmnAdHocSubProcess", "org.modelio.ModelTransformCommand", new BpmnSubProcessTransmuter(BpmnAdHocSubProcess.MQNAME)));
        this.transformers.add(new ModelTransformerCommand(index++, "$ActivityTransmuter.BpmnBusinessRuleTask", "org.modelio.ModelTransformCommand", new BpmnActivityTransmuter(BpmnBusinessRuleTask.MQNAME)));
        this.transformers.add(new ModelTransformerCommand(index++, "$ActivityTransmuter.BpmnCallActivity", "org.modelio.ModelTransformCommand", new BpmnActivityTransmuter(BpmnCallActivity.MQNAME)));
        this.transformers.add(new ModelTransformerCommand(index++, "$ActivityTransmuter.BpmnManualTask", "org.modelio.ModelTransformCommand", new BpmnActivityTransmuter(BpmnManualTask.MQNAME)));
        this.transformers.add(new ModelTransformerCommand(index++, "$ActivityTransmuter.BpmnReceiveTask", "org.modelio.ModelTransformCommand", new BpmnActivityTransmuter(BpmnReceiveTask.MQNAME)));
        this.transformers.add(new ModelTransformerCommand(index++, "$ActivityTransmuter.BpmnScriptTask", "org.modelio.ModelTransformCommand", new BpmnActivityTransmuter(BpmnScriptTask.MQNAME)));
        this.transformers.add(new ModelTransformerCommand(index++, "$ActivityTransmuter.BpmnSendTask", "org.modelio.ModelTransformCommand", new BpmnActivityTransmuter(BpmnSendTask.MQNAME)));
        this.transformers.add(new ModelTransformerCommand(index++, "$ActivityTransmuter.BpmnServiceTask", "org.modelio.ModelTransformCommand", new BpmnActivityTransmuter(BpmnServiceTask.MQNAME)));
        this.transformers.add(new ModelTransformerCommand(index++, "$ActivityTransmuter.BpmnSubProcess", "org.modelio.ModelTransformCommand", new BpmnSubProcessTransmuter(BpmnSubProcess.MQNAME)));
        this.transformers.add(new ModelTransformerCommand(index++, "$ActivityTransmuter.BpmnTask", "org.modelio.ModelTransformCommand", new BpmnActivityTransmuter(BpmnTask.MQNAME)));
        this.transformers.add(new ModelTransformerCommand(index++, "$ActivityTransmuter.BpmnTransaction", "org.modelio.ModelTransformCommand", new BpmnSubProcessTransmuter(BpmnTransaction.MQNAME)));
        this.transformers.add(new ModelTransformerCommand(index++, "$ActivityTransmuter.BpmnUserTask", "org.modelio.ModelTransformCommand", new BpmnActivityTransmuter(BpmnUserTask.MQNAME)));
        
        this.transformers.add(new ModelTransformerCommand(index++, "$BpmnGatewayTransmuter.BpmnComplexGateway", "org.modelio.ModelTransformCommand", new BpmnGatewayTransmuter(BpmnComplexGateway.MQNAME)));
        this.transformers.add(new ModelTransformerCommand(index++, "$BpmnGatewayTransmuter.BpmnEventBasedGateway", "org.modelio.ModelTransformCommand", new BpmnGatewayTransmuter(BpmnEventBasedGateway.MQNAME)));
        this.transformers.add(new ModelTransformerCommand(index++, "$BpmnGatewayTransmuter.BpmnExclusiveGateway", "org.modelio.ModelTransformCommand", new BpmnGatewayTransmuter(BpmnExclusiveGateway.MQNAME)));
        this.transformers.add(new ModelTransformerCommand(index++, "$BpmnGatewayTransmuter.BpmnInclusiveGateway", "org.modelio.ModelTransformCommand", new BpmnGatewayTransmuter(BpmnInclusiveGateway.MQNAME)));
        this.transformers.add(new ModelTransformerCommand(index++, "$BpmnGatewayTransmuter.BpmnParallelGateway", "org.modelio.ModelTransformCommand", new BpmnGatewayTransmuter(BpmnParallelGateway.MQNAME)));
        
        // this.transformers.add(new ModelTransformerCommand(index++, "$BpmnEventTransmuter.BpmnEndEvent", "org.modelio.ModelTransformCommand", new BpmnEventTransmuter(BpmnEndEvent.MQNAME)));
        // this.transformers.add(new ModelTransformerCommand(index++, "$BpmnEventTransmuter.BpmnIntermediateCatchEvent", "org.modelio.ModelTransformCommand", new BpmnEventTransmuter(BpmnIntermediateCatchEvent.MQNAME)));
        // this.transformers.add(new ModelTransformerCommand(index++, "$BpmnEventTransmuter.BpmnIntermediateThrowEvent", "org.modelio.ModelTransformCommand", new BpmnEventTransmuter(BpmnIntermediateThrowEvent.MQNAME)));
        // this.transformers.add(new ModelTransformerCommand(index++, "$BpmnEventTransmuter.BpmnStartEvent", "org.modelio.ModelTransformCommand", new BpmnEventTransmuter(BpmnStartEvent.MQNAME)));
        
        // this.transformers.add(new ModelTransformerCommand(index++, "$MessageFlowTransformer", "org.modelio.ModelTransformCommand", new MessageFlowTransformer()));
        
        for (ModelTransformerCommand mt : this.transformers) {
            ContextInjectionFactory.inject(mt.transformer, context);
        }
    }

    @objid ("836a8379-6d51-4ac3-83c5-1a8f99ab6254")
    public static TransformerRegistry getInstance(IEclipseContext context) {
        if (TransformerRegistry.INSTANCE == null) {
            TransformerRegistry.INSTANCE = new TransformerRegistry(context);
        }
        return TransformerRegistry.INSTANCE;
    }

    @objid ("7ea76efc-6e60-48af-92d5-366131c840c4")
    public List<ModelTransformerCommand> getTransformers(AbstractDiagram diagram, ISelection selection) {
        return this.transformers.stream()
                .filter(mt -> mt.transformer.isAvailable(diagram, selection))
                .collect(Collectors.toList());
    }

    @objid ("11ae59a5-61f5-42be-b9d6-3fdd94635dbf")
    public IModelTransformer getTransformer(int index) {
        return this.transformers.get(index).transformer;
    }

    @objid ("e593cc6f-7d8f-454e-a754-2da630b524c0")
    public static class ModelTransformerCommand {
        @objid ("a4940b4d-02ed-40c9-8abb-39f424686b30")
        public int index;

        @objid ("4c1ec083-ac89-4828-904c-a07092e73cae")
        public String i18nKey;

        @objid ("ac04d63d-729b-4d44-b907-65155dc88295")
        public String e4CmdId;

        @objid ("1dbe92d5-e1e2-48a9-ac08-4e21c013904c")
        public IModelTransformer transformer;

        @objid ("dda90302-c677-4958-9525-354f248dade3")
        public ModelTransformerCommand(int index, String i18nKey, String e4CmdId, IModelTransformer transformer) {
            this.index = index;
            this.transformer = transformer;
            this.i18nKey = i18nKey;
            this.e4CmdId = e4CmdId;
        }

    }

}
