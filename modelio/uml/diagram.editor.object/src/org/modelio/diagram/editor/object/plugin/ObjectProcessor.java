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

package org.modelio.diagram.editor.object.plugin;

import java.net.URL;
import java.util.Arrays;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.modelio.diagram.editor.object.editor.ObjectDiagramConfigurer;
import org.modelio.diagram.editor.object.elements.factories.ObjectEditPartFactory;
import org.modelio.diagram.editor.object.elements.factories.ObjectGmNodeFactory;
import org.modelio.diagram.editor.object.elements.objectdiagram.GmObjectDiagramStyleKeys;
import org.modelio.diagram.editor.plugin.IDiagramConfigurerRegistry;
import org.modelio.diagram.editor.processor.AbstractDiagramInitializationProcessor;
import org.modelio.diagram.elements.core.model.factory.DelegatingGmLinkFactory;
import org.modelio.diagram.elements.core.model.factory.DiagramFactoryRegistry;
import org.modelio.diagram.styles.core.FactoryStyle;
import org.modelio.diagram.styles.core.StyleLoader;
import org.modelio.metamodel.diagrams.ObjectDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.osgi.framework.BundleContext;

/**
 * Processor initializing environment for the {@link ObjectDiagram} diagram.
 */
@objid ("113cadae-5a45-11e2-9e33-00137282c51b")
public class ObjectProcessor extends AbstractDiagramInitializationProcessor {
    @objid ("194affc9-5a45-11e2-9e33-00137282c51b")
    @Override
    protected void declareDiagramConfigurers(IDiagramConfigurerRegistry configurerRegistry) {
        configurerRegistry.registerDiagramConfigurer(ObjectDiagram.MNAME, null, new ObjectDiagramConfigurer());
    }

    @objid ("194affce-5a45-11e2-9e33-00137282c51b")
    @Override
    protected void declareFactories(DiagramFactoryRegistry factoryRegistry) {
        factoryRegistry.registerDiagramFactories(ObjectDiagram.MNAME, new ObjectGmNodeFactory(), new DelegatingGmLinkFactory(Arrays.asList(StaticDiagram.MNAME)), new ObjectEditPartFactory());
        
        // Static elements should be usable in Object diagram
        factoryRegistry.registerExtensions(ObjectDiagram.MNAME, StaticDiagram.MNAME);
        
        // Object elements should be usable in every Static diagram
        factoryRegistry.registerExtensions(StaticDiagram.MNAME, ObjectDiagram.MNAME);
    }

    @objid ("194affd2-5a45-11e2-9e33-00137282c51b")
    @Override
    protected void declareFactorySettings(FactoryStyle factoryStyle) {
        // Load the default values. Do it only here, after key providers registration.
        StyleLoader loader = new StyleLoader();
        BundleContext bundle = DiagramEditorObject.getContext();
        URL url = FileLocator.find(bundle.getBundle(), new Path("res/factory.settings"), null);
        
        loader.load(url);
        
        factoryStyle.injectDefaultValues(loader.getStyleProperties());
    }

    @objid ("a3665e67-115b-4b53-af55-2655c5bd9dd6")
    @Override
    protected void declareStyleProviders(FactoryStyle factoryStyle) {
        // Communication Channel
        factoryStyle.declareProvider(GmObjectDiagramStyleKeys.class);
    }

}
