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
package org.modelio.uml.statediagram.editor.plugin;

import java.net.URL;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.modelio.diagram.editor.plugin.IDiagramConfigurerRegistry;
import org.modelio.diagram.editor.processor.AbstractDiagramInitializationProcessor;
import org.modelio.diagram.elements.core.model.factory.DiagramFactoryRegistry;
import org.modelio.diagram.styles.core.FactoryStyle;
import org.modelio.diagram.styles.core.StyleLoader;
import org.modelio.metamodel.diagrams.StateMachineDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.uml.statediagram.editor.editor.StateDiagramConfigurer;
import org.modelio.uml.statediagram.editor.elements.choice.GmChoiceStructuredStyleKeys;
import org.modelio.uml.statediagram.editor.elements.connectionpoint.GmConnectionPointStructuredStyleKeys;
import org.modelio.uml.statediagram.editor.elements.deephistory.GmDeepHistoryStructuredStyleKeys;
import org.modelio.uml.statediagram.editor.elements.entry.GmEntryStructuredStyleKeys;
import org.modelio.uml.statediagram.editor.elements.exit.GmExitStructuredStyleKeys;
import org.modelio.uml.statediagram.editor.elements.factories.StateEditPartFactory;
import org.modelio.uml.statediagram.editor.elements.factories.StateGmLinkFactory;
import org.modelio.uml.statediagram.editor.elements.factories.StateGmNodeFactory;
import org.modelio.uml.statediagram.editor.elements.finalstate.GmFinalStateStructuredStyleKeys;
import org.modelio.uml.statediagram.editor.elements.fork.GmForkStateStructuredStyleKeys;
import org.modelio.uml.statediagram.editor.elements.initialstate.GmInitialStateStructuredStyleKeys;
import org.modelio.uml.statediagram.editor.elements.internaltransition.GmInternalTransitionStructuredStyleKeys;
import org.modelio.uml.statediagram.editor.elements.join.GmJoinStructuredStyleKeys;
import org.modelio.uml.statediagram.editor.elements.junction.GmJunctionStructuredStyleKeys;
import org.modelio.uml.statediagram.editor.elements.region.GmRegionStructuredStyleKeys;
import org.modelio.uml.statediagram.editor.elements.shallowhistory.GmShallowHistoryStructuredStyleKeys;
import org.modelio.uml.statediagram.editor.elements.state.GmStateStructuredStyleKeys;
import org.modelio.uml.statediagram.editor.elements.statediagram.GmStateDiagramStyleKeys;
import org.modelio.uml.statediagram.editor.elements.terminal.GmTerminalStructuredStyleKeys;
import org.modelio.uml.statediagram.editor.elements.transition.GmTransitionStyleKeys;
import org.osgi.framework.BundleContext;

/**
 * Processor initializing environment for the {@link StateMachineDiagram} diagram.
 */
@objid ("d3d1b4e7-addf-427c-9d43-990994e7ac10")
public class StateProcessor extends AbstractDiagramInitializationProcessor {
    @objid ("9f90dd9c-5fc1-45ff-a32f-9f78b869f8e3")
    @Override
    protected void declareDiagramConfigurers(IDiagramConfigurerRegistry configurerRegistry) {
        configurerRegistry.registerDiagramConfigurer(StateMachineDiagram.MNAME, null, new StateDiagramConfigurer());
    }

    @objid ("7dbd2e8d-7785-41a6-bab2-21a2ee36ad43")
    @Override
    protected void declareFactories(DiagramFactoryRegistry factoryRegistry) {
        factoryRegistry.registerDiagramFactories(StateMachineDiagram.MNAME, new StateGmNodeFactory(), new StateGmLinkFactory(), new StateEditPartFactory());
        
        factoryRegistry.registerExtensions(StaticDiagram.MNAME, StateMachineDiagram.MNAME);
        
    }

    @objid ("ca1f656f-0421-48bf-bb03-d12780242178")
    @Override
    protected void declareStyleProviders(FactoryStyle factoryStyle) {
        factoryStyle.declareProvider(GmStateDiagramStyleKeys.class);
        
        factoryStyle.declareProvider(GmStateStructuredStyleKeys.class);
        // region
        factoryStyle.declareProvider(GmRegionStructuredStyleKeys.class);
        // internal transition
        factoryStyle.declareProvider(GmInternalTransitionStructuredStyleKeys.class);
        // Initial state
        factoryStyle.declareProvider(GmInitialStateStructuredStyleKeys.class);
        // Final state
        factoryStyle.declareProvider(GmFinalStateStructuredStyleKeys.class);
        // choice state
        factoryStyle.declareProvider(GmChoiceStructuredStyleKeys.class);
        // terminal state
        factoryStyle.declareProvider(GmTerminalStructuredStyleKeys.class);
        // exit state
        factoryStyle.declareProvider(GmExitStructuredStyleKeys.class);
        // entry state
        factoryStyle.declareProvider(GmEntryStructuredStyleKeys.class);
        // connection point
        factoryStyle.declareProvider(GmConnectionPointStructuredStyleKeys.class);
        // connection point
        factoryStyle.declareProvider(GmJunctionStructuredStyleKeys.class);
        // shallow history
        factoryStyle.declareProvider(GmShallowHistoryStructuredStyleKeys.class);
        // deep history
        factoryStyle.declareProvider(GmDeepHistoryStructuredStyleKeys.class);
        // fork
        factoryStyle.declareProvider(GmForkStateStructuredStyleKeys.class);
        // join
        factoryStyle.declareProvider(GmJoinStructuredStyleKeys.class);
        // transition
        factoryStyle.declareProvider(GmTransitionStyleKeys.class);
        
    }

    @objid ("1ae9e4b8-fe5f-4182-886d-e2d0437c833f")
    @Override
    protected void declareFactorySettings(FactoryStyle factoryStyle) {
        // Load the default values. Do it only here, after key providers registration.
        StyleLoader loader = new StyleLoader();
        BundleContext bundle = DiagramEditorState.getContext();
        URL url = FileLocator.find(bundle.getBundle(), new Path("res/factory.settings"), null);
        
        loader.load(url);
        
        factoryStyle.injectDefaultValues(loader.getStyleProperties());
        
    }

}
