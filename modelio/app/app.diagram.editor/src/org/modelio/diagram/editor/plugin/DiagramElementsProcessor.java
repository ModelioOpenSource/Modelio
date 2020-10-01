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

package org.modelio.diagram.editor.plugin;

import java.net.URL;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.modelio.diagram.editor.processor.AbstractDiagramInitializationProcessor;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagramStyleKeys;
import org.modelio.diagram.elements.common.abstractdiagram.LayoutAssistantStyleKeys;
import org.modelio.diagram.elements.common.genericlink.GmGenericLinkStyleKeys;
import org.modelio.diagram.elements.common.genericnode.GmGenericNodeStyleKeys;
import org.modelio.diagram.elements.core.model.factory.DiagramFactoryRegistry;
import org.modelio.diagram.elements.drawings.ellipse.GmEllipseStyleKeys;
import org.modelio.diagram.elements.drawings.line.GmLineStyleKeys;
import org.modelio.diagram.elements.drawings.rectangle.GmRectangleStyleKeys;
import org.modelio.diagram.elements.drawings.text.GmTextStyleKeys;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.elements.umlcommon.dependency.GmDependency;
import org.modelio.diagram.elements.umlcommon.diagramview.DiagramViewStyleKeys;
import org.modelio.diagram.elements.umlcommon.externdocument.GmExternDocumentStyleKeys;
import org.modelio.diagram.elements.umlcommon.namespaceuse.GmNamespaceUseStyleKeys;
import org.modelio.diagram.elements.umlcommon.note.GmNoteStyleKeys;
import org.modelio.diagram.elements.umlcommon.usage.GmUsage;
import org.modelio.diagram.styles.core.FactoryStyle;
import org.modelio.diagram.styles.core.StyleLoader;
import org.osgi.framework.BundleContext;

/**
 * Processor registering the diagram.elements StyleKeys and initializing the factory.settings.
 */
@objid ("ec74c92a-58d3-11e2-be0b-002564c97630")
public class DiagramElementsProcessor extends AbstractDiagramInitializationProcessor {
    @objid ("f73b9f9b-58d3-11e2-be0b-002564c97630")
    @Override
    protected void declareFactorySettings(FactoryStyle factoryStyle) {
        StyleLoader loader = new StyleLoader();
        BundleContext bundle = DiagramElements.getContext();
        URL url = FileLocator.find(bundle.getBundle(), new Path("res/factory.settings"), null);
        
        loader.load(url);
        
        factoryStyle.injectDefaultValues(loader.getStyleProperties());
    }

    @objid ("f73b9f9d-58d3-11e2-be0b-002564c97630")
    @Override
    protected void declareStyleProviders(FactoryStyle factoryStyle) {
        // Abstract Diagram
        factoryStyle.declareProvider(GmAbstractDiagramStyleKeys.class);
        
        // Free zone layout
        factoryStyle.declareProvider(LayoutAssistantStyleKeys.class);
        
        // Dependency
        factoryStyle.declareProvider(GmDependency.styleKeyProvider);
        factoryStyle.declareProvider(GmUsage.styleKeyProvider);
        
        // Extern document
        factoryStyle.declareProvider(GmExternDocumentStyleKeys.class);
        
        // Impact link
        factoryStyle.declareProvider(GmNamespaceUseStyleKeys.class);
        
        // Note
        factoryStyle.declareProvider(GmNoteStyleKeys.class);
        
        // Drawings
        factoryStyle.declareProvider(GmRectangleStyleKeys.class);
        factoryStyle.declareProvider(GmRectangleStyleKeys.Label.class);
        factoryStyle.declareProvider(GmEllipseStyleKeys.class);
        factoryStyle.declareProvider(GmEllipseStyleKeys.Label.class);
        factoryStyle.declareProvider(GmTextStyleKeys.class);
        factoryStyle.declareProvider(GmLineStyleKeys.class);
        factoryStyle.declareProvider(GmLineStyleKeys.SourceDeco.class);
        factoryStyle.declareProvider(GmLineStyleKeys.TargetDeco.class);
        
        // Diagram View
        factoryStyle.declareProvider(DiagramViewStyleKeys.class);
        
        // Generic Gms
        factoryStyle.declareProvider(GmGenericNodeStyleKeys.class);
        factoryStyle.declareProvider(GmGenericLinkStyleKeys.class);
    }

    @objid ("8b5deda7-d139-41c1-b8b2-7ff3c4fbcef8")
    @Override
    protected void declareDiagramConfigurers(IDiagramConfigurerRegistry configurerRegistry) {
        // No diagram to declare
    }

    @objid ("86c88d4e-9841-4458-98d9-4bdeaa994618")
    @Override
    protected void declareFactories(DiagramFactoryRegistry factoryRegistry) {
        // No factory to declare
    }

}
