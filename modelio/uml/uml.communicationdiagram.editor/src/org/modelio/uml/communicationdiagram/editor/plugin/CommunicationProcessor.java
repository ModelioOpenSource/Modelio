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
package org.modelio.uml.communicationdiagram.editor.plugin;

import java.net.URL;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.modelio.diagram.editor.plugin.IDiagramConfigurerRegistry;
import org.modelio.diagram.editor.processor.AbstractDiagramInitializationProcessor;
import org.modelio.diagram.elements.core.model.factory.DiagramFactoryRegistry;
import org.modelio.diagram.styles.core.FactoryStyle;
import org.modelio.diagram.styles.core.StyleLoader;
import org.modelio.metamodel.diagrams.CommunicationDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.uml.communicationdiagram.editor.editor.CommunicationDiagramConfigurer;
import org.modelio.uml.communicationdiagram.editor.elements.communicationchannel.GmCommunicationChannelStyleKeys;
import org.modelio.uml.communicationdiagram.editor.elements.communicationdiagram.GmCommunicationDiagramStyleKeys;
import org.modelio.uml.communicationdiagram.editor.elements.communicationnode.GmCommunicationNodeStructuredStyleKeys;
import org.modelio.uml.communicationdiagram.editor.elements.factories.CommunicationEditPartFactory;
import org.modelio.uml.communicationdiagram.editor.elements.factories.CommunicationGmLinkFactory;
import org.modelio.uml.communicationdiagram.editor.elements.factories.CommunicationGmNodeFactory;
import org.osgi.framework.BundleContext;

/**
 * Processor initializing environment for the {@link CommunicationDiagram} diagram.
 */
@objid ("9e1fe2ba-598e-11e2-ae45-002564c97630")
public class CommunicationProcessor extends AbstractDiagramInitializationProcessor {
    @objid ("059657d0-599a-11e2-ae45-002564c97630")
    @Override
    protected void declareFactories(DiagramFactoryRegistry factoryRegistry) {
        factoryRegistry.registerDiagramFactories(CommunicationDiagram.MNAME, new CommunicationGmNodeFactory(), new CommunicationGmLinkFactory(), new CommunicationEditPartFactory());
        
        // Static UML elements should be usable in Communication diagrams
        factoryRegistry.registerExtensions(StaticDiagram.MNAME, CommunicationDiagram.MNAME);
        
    }

    @objid ("059657d2-599a-11e2-ae45-002564c97630")
    @Override
    protected void declareStyleProviders(FactoryStyle factoryStyle) {
        // Communication Channel
        factoryStyle.declareProvider(GmCommunicationChannelStyleKeys.class);
        factoryStyle.declareProvider(GmCommunicationChannelStyleKeys.InfoFlows.class);
        
        // Communication Diagram
        factoryStyle.declareProvider(GmCommunicationDiagramStyleKeys.class);
        
        // Communication node
        factoryStyle.declareProvider(GmCommunicationNodeStructuredStyleKeys.class);
        
    }

    @objid ("059657d4-599a-11e2-ae45-002564c97630")
    @Override
    protected void declareFactorySettings(FactoryStyle factoryStyle) {
        StyleLoader loader = new StyleLoader();
        BundleContext bundle = DiagramEditorCommunication.getContext();
        URL url = FileLocator.find(bundle.getBundle(), new Path("res/factory.settings"), null);
        
        loader.load(url);
        
        factoryStyle.injectDefaultValues(loader.getStyleProperties());
        
    }

    @objid ("df3f520e-ee65-445b-bbaf-f690f6d2db19")
    @Override
    protected void declareDiagramConfigurers(IDiagramConfigurerRegistry configurerRegistry) {
        configurerRegistry.registerDiagramConfigurer(CommunicationDiagram.MNAME, null, new CommunicationDiagramConfigurer());
    }

}
