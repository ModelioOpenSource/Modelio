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

package org.modelio.diagram.editor.bpmn.plugin;

import java.net.URL;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.modelio.diagram.editor.bpmn.editor.palette.BpmnCollaborationDiagramConfigurer;
import org.modelio.diagram.editor.bpmn.editor.palette.BpmnProcessDesignDiagramConfigurer;
import org.modelio.diagram.editor.bpmn.editor.palette.BpmnSubProcessDiagramConfigurer;
import org.modelio.diagram.editor.bpmn.elements.bpmnboundaryevent.GmBpmnBoundaryEventImageStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmnboundaryevent.GmBpmnBoundaryEventSimpleStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmnboundaryevent.GmBpmnBoundaryEventStructuredStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmncallactivity.GmBpmnCallActivityStructuredStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmndataassociation.GmBpmnDataAssociationStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.GmBpmnDataImageStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.GmBpmnDataObjectStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.GmBpmnDataSimpleStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmnendevent.GmBpmnEndEventImageStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmnendevent.GmBpmnEndEventSimpleStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmnendevent.GmBpmnEndEventStructuredStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmnintermediatecatchevent.GmBpmnIntermediateCatchEventImageStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmnintermediatecatchevent.GmBpmnIntermediateCatchEventSimpleStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmnintermediatecatchevent.GmBpmnIntermediateCatchEventStructuredStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmnintermediatethrowevent.GmBpmnIntermediateThrowEventImageStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmnintermediatethrowevent.GmBpmnIntermediateThrowEventSimpleStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmnintermediatethrowevent.GmBpmnIntermediateThrowEventStructuredStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmnlane.GmBpmnLaneStructuredStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmnmessage.GmBpmnMessageImageStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmnmessage.GmBpmnMessageSimpleStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmnmessage.GmBpmnMessageStructuredStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmnmessage.GmBpmnMessageUserImageStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmnmessageflow.GmBpmnMessageFlowStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmnreceivetask.GmBpmnReceiveTaskStructuredStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmnsendtask.GmBpmnSendTaskStructuredStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmnsequenceflow.GmBpmnSequenceFlowStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmnsequenceflowdataassociation.GmBpmnSequenceFlowDataAssociationStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmnservicetask.GmBpmnServiceTaskStructuredStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmnstartevent.GmBpmnStartEventImageStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmnstartevent.GmBpmnStartEventSimpleStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmnstartevent.GmBpmnStartEventStructuredStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.diagrams.GmBpmnDiagramStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.factories.BpmnEditPartFactory;
import org.modelio.diagram.editor.bpmn.elements.factories.BpmnGmLinkFactory;
import org.modelio.diagram.editor.bpmn.elements.factories.BpmnGmNodeFactory;
import org.modelio.diagram.editor.bpmn.elements.participant.ParticipantStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnGatewayImageStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnGatewaySimpleStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnGatewayStructuredStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnSubProcessImageStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnSubProcessSimpleStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnSubProcessStructuredStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnTaskImageStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnTaskSimpleStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnTaskStructuredStyleKeys;
import org.modelio.diagram.editor.plugin.IDiagramConfigurerRegistry;
import org.modelio.diagram.editor.processor.AbstractDiagramInitializationProcessor;
import org.modelio.diagram.elements.core.model.factory.DiagramFactoryRegistry;
import org.modelio.diagram.styles.core.FactoryStyle;
import org.modelio.diagram.styles.core.StyleLoader;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnCollaborationDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.osgi.framework.BundleContext;

/**
 * Processor initializing environment for the {@link BpmnCollaborationDiagram}, {@link BpmnProcessDesignDiagram} and {@link BpmnSubProcessDiagram} diagrams.
 */
@objid ("a390671a-598e-11e2-ae45-002564c97630")
public class BpmnProcessor extends AbstractDiagramInitializationProcessor {
    @objid ("c618fbee-59a6-11e2-ae45-002564c97630")
    @Override
    protected void declareDiagramConfigurers(IDiagramConfigurerRegistry configurerRegistry) {
        configurerRegistry.registerDiagramConfigurer(BpmnCollaborationDiagram.MNAME, null, new BpmnCollaborationDiagramConfigurer());
        configurerRegistry.registerDiagramConfigurer(BpmnProcessDesignDiagram.MNAME, null, new BpmnProcessDesignDiagramConfigurer());
        configurerRegistry.registerDiagramConfigurer(BpmnSubProcessDiagram.MNAME, null, new BpmnSubProcessDiagramConfigurer());
    }

    @objid ("c618fbf3-59a6-11e2-ae45-002564c97630")
    @Override
    protected void declareFactories(DiagramFactoryRegistry factoryRegistry) {
        factoryRegistry.registerDiagramFactories(BpmnCollaborationDiagram.MNAME, new BpmnGmNodeFactory(), new BpmnGmLinkFactory(), new BpmnEditPartFactory());
        
        factoryRegistry.registerDiagramFactories(BpmnProcessDesignDiagram.MNAME, new BpmnGmNodeFactory(), new BpmnGmLinkFactory(), new BpmnEditPartFactory());
        
        factoryRegistry.registerDiagramFactories(BpmnSubProcessDiagram.MNAME, new BpmnGmNodeFactory(), new BpmnGmLinkFactory(), new BpmnEditPartFactory());
    }

    @objid ("c618fbf5-59a6-11e2-ae45-002564c97630")
    @Override
    protected void declareStyleProviders(FactoryStyle factoryStyle) {
        factoryStyle.declareProvider(GmBpmnSequenceFlowStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnSequenceFlowDataAssociationStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnMessageFlowStyleKeys.class);
        
        factoryStyle.declareProvider(GmBpmnMessageStructuredStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnMessageImageStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnMessageSimpleStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnMessageUserImageStyleKeys.class);
        
        factoryStyle.declareProvider(GmBpmnTaskStructuredStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnTaskImageStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnTaskSimpleStyleKeys.class);
        
        factoryStyle.declareProvider(GmBpmnSubProcessStructuredStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnSubProcessImageStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnSubProcessSimpleStyleKeys.class);
        
        factoryStyle.declareProvider(GmBpmnDiagramStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnGatewayImageStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnGatewaySimpleStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnGatewayStructuredStyleKeys.class);
        
        factoryStyle.declareProvider(GmBpmnBoundaryEventStructuredStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnBoundaryEventSimpleStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnBoundaryEventImageStyleKeys.class);
        
        factoryStyle.declareProvider(GmBpmnEndEventImageStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnEndEventSimpleStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnEndEventStructuredStyleKeys.class);
        
        factoryStyle.declareProvider(GmBpmnStartEventImageStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnStartEventSimpleStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnStartEventStructuredStyleKeys.class);
        
        factoryStyle.declareProvider(GmBpmnIntermediateCatchEventImageStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnIntermediateCatchEventSimpleStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnIntermediateCatchEventStructuredStyleKeys.class);
        
        factoryStyle.declareProvider(GmBpmnIntermediateThrowEventImageStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnIntermediateThrowEventSimpleStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnIntermediateThrowEventStructuredStyleKeys.class);
        
        factoryStyle.declareProvider(GmBpmnDataObjectStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnDataSimpleStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnDataImageStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnDataAssociationStyleKeys.class);
        
        factoryStyle.declareProvider(GmBpmnLaneStructuredStyleKeys.class);
        
        factoryStyle.declareProvider(ParticipantStyleKeys.INSTANCE);
        
        factoryStyle.declareProvider(GmBpmnCallActivityStructuredStyleKeys.class);
        
        factoryStyle.declareProvider(GmBpmnSendTaskStructuredStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnServiceTaskStructuredStyleKeys.class);
        factoryStyle.declareProvider(GmBpmnReceiveTaskStructuredStyleKeys.class);
    }

    @objid ("c618fbf7-59a6-11e2-ae45-002564c97630")
    @Override
    protected void declareFactorySettings(FactoryStyle factoryStyle) {
        StyleLoader loader = new StyleLoader();
        BundleContext bundle = DiagramEditorBpmn.getContext();
        URL url = FileLocator.find(bundle.getBundle(), new Path("res/factory.settings"), null);
        
        loader.load(url);
        
        factoryStyle.injectDefaultValues(loader.getStyleProperties());
    }

}
