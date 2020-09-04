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

package org.modelio.diagram.editor.activity.plugin;

import java.net.URL;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.modelio.diagram.editor.activity.elements.acceptsignal.GmAcceptSignalStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.action.GmActionStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.activitydiagram.GmActivityDiagramStyleKeys;
import org.modelio.diagram.editor.activity.elements.activityfinal.GmActivityFinalStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.callbehavior.GmCallBehaviorStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.callevent.GmCallEventStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.calloperation.GmCallOperationStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.centralbuffer.GmCentralBufferStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.changeevent.GmChangeEventStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.clause.GmClauseStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.conditional.GmConditionalStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.controlflow.GmControlStyleKeys;
import org.modelio.diagram.editor.activity.elements.datastore.GmDataStoreStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.decisionmerge.GmDecisionMergeStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.exceptionhandler.GmExceptionHandlerStyleKeys;
import org.modelio.diagram.editor.activity.elements.expansionnode.GmExpansionNodeStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.expansionregion.GmExpansionRegionStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.factories.ActivityEditPartFactory;
import org.modelio.diagram.editor.activity.elements.factories.ActivityGmLinkFactory;
import org.modelio.diagram.editor.activity.elements.factories.ActivityGmNodeFactory;
import org.modelio.diagram.editor.activity.elements.flowfinal.GmFlowFinalStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.forkjoin.GmForkJoinStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.initial.GmInitialStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.inputpin.GmInputPinStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.interruptible.GmInterruptibleStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.loopnode.GmLoopNodeStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.objectflow.GmObjectFlowStyleKeys;
import org.modelio.diagram.editor.activity.elements.objectnode.GmObjectNodeStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.outputpin.GmOutputPinStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.partition.GmPartitionStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.sendsignal.GmSendSignalStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.structuredactivity.GmStructuredActivityStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.timeevent.GmTimeEventStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.valuepin.GmValuePinStructuredStyleKeys;
import org.modelio.diagram.editor.plugin.IDiagramConfigurerRegistry;
import org.modelio.diagram.editor.processor.AbstractDiagramInitializationProcessor;
import org.modelio.diagram.elements.core.model.factory.DiagramFactoryRegistry;
import org.modelio.diagram.styles.core.FactoryStyle;
import org.modelio.diagram.styles.core.StyleLoader;
import org.modelio.metamodel.diagrams.ActivityDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.osgi.framework.BundleContext;

/**
 * Processor initializing environment for the {@link ActivityDiagram} diagram.
 */
@objid ("2b75e8b3-55b6-11e2-877f-002564c97630")
public class ActivityProcessor extends AbstractDiagramInitializationProcessor {
    @objid ("2ed78931-58a7-11e2-9574-002564c97630")
    @Override
    protected void declareFactories(DiagramFactoryRegistry factoryRegistry) {
        factoryRegistry.registerDiagramFactories(ActivityDiagram.MNAME, new ActivityGmNodeFactory(), new ActivityGmLinkFactory(), new ActivityEditPartFactory());
        
        // Static UML elements should be usable in Activity diagrams
        factoryRegistry.registerExtensions(StaticDiagram.MNAME, ActivityDiagram.MNAME);
    }

    @objid ("2ed78933-58a7-11e2-9574-002564c97630")
    @Override
    protected void declareStyleProviders(FactoryStyle factoryStyle) {
        // Activity Diagram
        factoryStyle.declareProvider(GmActivityDiagramStyleKeys.class);
        // OpaqueAction
        factoryStyle.declareProvider(GmActionStructuredStyleKeys.class);
        // CallBehavior
        factoryStyle.declareProvider(GmCallBehaviorStructuredStyleKeys.class);
        // CallOperation
        factoryStyle.declareProvider(GmCallOperationStructuredStyleKeys.class);
        // ObjectNode
        factoryStyle.declareProvider(GmObjectNodeStructuredStyleKeys.class);
        // CentralBuffer
        factoryStyle.declareProvider(GmCentralBufferStructuredStyleKeys.class);
        // DataStore
        factoryStyle.declareProvider(GmLoopNodeStructuredStyleKeys.class);
        // Loop node
        factoryStyle.declareProvider(GmDataStoreStructuredStyleKeys.class);
        // Control flow
        factoryStyle.declareProvider(GmControlStyleKeys.class);
        factoryStyle.declareProvider(GmControlStyleKeys.InfoFlows.class);
        // value pin
        factoryStyle.declareProvider(GmValuePinStructuredStyleKeys.class);
        // expansion node
        factoryStyle.declareProvider(GmExpansionNodeStructuredStyleKeys.class);
        // expansion region
        factoryStyle.declareProvider(GmExpansionRegionStructuredStyleKeys.class);
        // Exception handler
        factoryStyle.declareProvider(GmExceptionHandlerStyleKeys.class);
        // Object flow
        factoryStyle.declareProvider(GmObjectFlowStyleKeys.class);
        factoryStyle.declareProvider(GmObjectFlowStyleKeys.InfoFlows.class);
        // Send signal action
        factoryStyle.declareProvider(GmSendSignalStructuredStyleKeys.class);
        // accept call event action
        factoryStyle.declareProvider(GmCallEventStructuredStyleKeys.class);
        // accept change event action
        factoryStyle.declareProvider(GmChangeEventStructuredStyleKeys.class);
        // accept signal action
        factoryStyle.declareProvider(GmAcceptSignalStructuredStyleKeys.class);
        // structured activity node
        factoryStyle.declareProvider(GmStructuredActivityStructuredStyleKeys.class);
        // conditional node
        factoryStyle.declareProvider(GmConditionalStructuredStyleKeys.class);
        // clause
        factoryStyle.declareProvider(GmClauseStructuredStyleKeys.class);
        // time event
        factoryStyle.declareProvider(GmTimeEventStructuredStyleKeys.class);
        // initial
        factoryStyle.declareProvider(GmInitialStructuredStyleKeys.class);
        // final
        factoryStyle.declareProvider(GmActivityFinalStructuredStyleKeys.class);
        // fork join
        factoryStyle.declareProvider(GmForkJoinStructuredStyleKeys.class);
        // flowfinal
        factoryStyle.declareProvider(GmFlowFinalStructuredStyleKeys.class);
        // decision merge
        factoryStyle.declareProvider(GmDecisionMergeStructuredStyleKeys.class);
        // Input pint
        factoryStyle.declareProvider(GmInputPinStructuredStyleKeys.class);
        // Output pin
        factoryStyle.declareProvider(GmOutputPinStructuredStyleKeys.class);
        // Partition
        factoryStyle.declareProvider(GmPartitionStructuredStyleKeys.class);
        // Interruptible region
        factoryStyle.declareProvider(GmInterruptibleStructuredStyleKeys.class);
    }

    @objid ("a4d67dc4-58d3-11e2-be0b-002564c97630")
    @Override
    protected void declareFactorySettings(FactoryStyle factoryStyle) {
        StyleLoader loader = new StyleLoader();
        BundleContext bundle = DiagramEditorActivity.getContext();
        URL url = FileLocator.find(bundle.getBundle(), new Path("res/factory.settings"), null);
        
        loader.load(url);
        
        factoryStyle.injectDefaultValues(loader.getStyleProperties());
    }

    @objid ("13b303e3-e8ae-48c1-9393-917916fa204d")
    @Override
    protected void declareDiagramConfigurers(IDiagramConfigurerRegistry configurerRegistry) {
        configurerRegistry.registerDiagramConfigurer(ActivityDiagram.MNAME, null, new ActivityDiagramConfigurer());
    }

}
