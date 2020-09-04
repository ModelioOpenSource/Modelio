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

package org.modelio.diagram.editor.sequence.plugin;

import java.net.URL;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.modelio.diagram.editor.plugin.IDiagramConfigurerRegistry;
import org.modelio.diagram.editor.processor.AbstractDiagramInitializationProcessor;
import org.modelio.diagram.editor.sequence.elements.combinedfragment.GmCombinedFragmentStyleKeys;
import org.modelio.diagram.editor.sequence.elements.executionoccurencespecification.GmExecutionOccurenceSpecificationStyleKeys;
import org.modelio.diagram.editor.sequence.elements.executionspecification.GmExecutionSpecificationStructuredStyleKeys;
import org.modelio.diagram.editor.sequence.elements.factories.SequenceEditPartFactory;
import org.modelio.diagram.editor.sequence.elements.factories.SequenceGmLinkFactory;
import org.modelio.diagram.editor.sequence.elements.factories.SequenceGmNodeFactory;
import org.modelio.diagram.editor.sequence.elements.gate.GmGateStructuredStyleKeys;
import org.modelio.diagram.editor.sequence.elements.interactionoperand.GmInteractionOperandStyleKeys;
import org.modelio.diagram.editor.sequence.elements.interactionuse.GmInteractionUseStyleKeys;
import org.modelio.diagram.editor.sequence.elements.interactionuse.gate.GmGateOnInteractionUseStructuredStyleKeys;
import org.modelio.diagram.editor.sequence.elements.lifeline.GmLifelineStructuredStyleKeys;
import org.modelio.diagram.editor.sequence.elements.message.GmMessageStyleKeys;
import org.modelio.diagram.editor.sequence.elements.sequencediagram.GmSequenceDiagramStyleKeys;
import org.modelio.diagram.editor.sequence.elements.stateinvariant.GmStateInvariantStructuredStyleKeys;
import org.modelio.diagram.elements.core.model.factory.DiagramFactoryRegistry;
import org.modelio.diagram.styles.core.FactoryStyle;
import org.modelio.diagram.styles.core.StyleLoader;
import org.modelio.metamodel.diagrams.SequenceDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.osgi.framework.BundleContext;

/**
 * Processor initializing environment for the {@link SequenceDiagram} diagram.
 */
@objid ("ef3b46f8-4778-4fba-8be4-d8dc2c327182")
public class SequenceProcessor extends AbstractDiagramInitializationProcessor {
    @objid ("f7e41841-49ed-4ca6-ab5e-3fe6a93f0310")
    @Override
    protected void declareDiagramConfigurers(IDiagramConfigurerRegistry configurerRegistry) {
        configurerRegistry.registerDiagramConfigurer(SequenceDiagram.MNAME, null, new SequenceDiagramConfigurer());
    }

    @objid ("74573ff1-5afb-411b-a94e-c33298fc8e3d")
    @Override
    protected void declareFactories(DiagramFactoryRegistry factoryRegistry) {
        factoryRegistry.registerDiagramFactories(SequenceDiagram.MNAME, new SequenceGmNodeFactory(), new SequenceGmLinkFactory(), new SequenceEditPartFactory());
        
        factoryRegistry.registerExtensions(StaticDiagram.MNAME, SequenceDiagram.MNAME);
    }

    @objid ("c45215d1-ae9d-4a2e-84d7-7ba1105400ab")
    @Override
    protected void declareStyleProviders(FactoryStyle factoryStyle) {
        final FactoryStyle factory = factoryStyle;
        
        // Declare the StyleKey providers
        // ------------------------------
        
        // Sequence Diagram
        // ----------------
        factory.declareProvider(GmSequenceDiagramStyleKeys.class);
        
        // Message
        factory.declareProvider(GmMessageStyleKeys.class);
        factory.declareProvider(GmMessageStyleKeys.InfoFlows.class);
        
        // Lifeline
        factory.declareProvider(GmLifelineStructuredStyleKeys.class);
        
        // Execution
        factory.declareProvider(GmExecutionSpecificationStructuredStyleKeys.class);
        
        // ExecutionOccurenceSpecification
        factory.declareProvider(GmExecutionOccurenceSpecificationStyleKeys.class);
        
        // InteractionUse
        factory.declareProvider(GmInteractionUseStyleKeys.class);
        
        // GateOnInteractionUSe
        factory.declareProvider(GmGateOnInteractionUseStructuredStyleKeys.class);
        
        // Gate (on diagram background)
        factory.declareProvider(GmGateStructuredStyleKeys.class);
        
        // CombinedFragment
        factory.declareProvider(GmCombinedFragmentStyleKeys.class);
        
        // InteractionOperand
        factory.declareProvider(GmInteractionOperandStyleKeys.class);
        
        // StateInvariant
        factory.declareProvider(GmStateInvariantStructuredStyleKeys.class);
    }

    @objid ("b1725a5f-ec9e-4fe1-8a14-3e4af693f01f")
    @Override
    protected void declareFactorySettings(FactoryStyle factoryStyle) {
        // Load the default values.
        StyleLoader loader = new StyleLoader();
        BundleContext bundle = DiagramEditorSequence.getContext();
        URL url = FileLocator.find(bundle.getBundle(), new Path("res/factory.settings"), null);
        
        loader.load(url);
        
        factoryStyle.injectDefaultValues(loader.getStyleProperties());
    }

}
