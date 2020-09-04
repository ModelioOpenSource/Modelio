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

package org.modelio.diagram.editor.usecase.plugin;

import java.net.URL;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.modelio.diagram.editor.plugin.IDiagramConfigurerRegistry;
import org.modelio.diagram.editor.processor.AbstractDiagramInitializationProcessor;
import org.modelio.diagram.editor.usecase.elements.actor.GmActorStructuredStyleKeys;
import org.modelio.diagram.editor.usecase.elements.factories.UseCaseEditPartFactory;
import org.modelio.diagram.editor.usecase.elements.factories.UseCaseGmLinkFactory;
import org.modelio.diagram.editor.usecase.elements.factories.UseCaseGmNodeFactory;
import org.modelio.diagram.editor.usecase.elements.system.GmSystemStyleKeys;
import org.modelio.diagram.editor.usecase.elements.usecase.GmUseCaseStructuredStyleKeys;
import org.modelio.diagram.editor.usecase.elements.usecasedependency.GmUseCaseDependencyStyleKeys;
import org.modelio.diagram.editor.usecase.elements.usecasediagram.GmUseCaseDiagramStyleKeys;
import org.modelio.diagram.elements.core.model.factory.DiagramFactoryRegistry;
import org.modelio.diagram.styles.core.FactoryStyle;
import org.modelio.diagram.styles.core.StyleLoader;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.diagrams.UseCaseDiagram;
import org.osgi.framework.BundleContext;

/**
 * Processor initializing environment for the {@link UseCaseDiagram} diagram.
 */
@objid ("7c1c400b-5eff-11e2-b9cc-001ec947c8cc")
public class UseCaseProcessor extends AbstractDiagramInitializationProcessor {
    @objid ("7c1c400d-5eff-11e2-b9cc-001ec947c8cc")
    @Override
    protected void declareDiagramConfigurers(IDiagramConfigurerRegistry configurerRegistry) {
        configurerRegistry.registerDiagramConfigurer(UseCaseDiagram.MNAME, null, new UseCaseDiagramConfigurer());
    }

    @objid ("7c1c4011-5eff-11e2-b9cc-001ec947c8cc")
    @Override
    protected void declareFactories(DiagramFactoryRegistry factoryRegistry) {
        factoryRegistry.registerDiagramFactories(UseCaseDiagram.MNAME, new UseCaseGmNodeFactory(), new UseCaseGmLinkFactory(), new UseCaseEditPartFactory());
        
        factoryRegistry.registerExtensions(StaticDiagram.MNAME, UseCaseDiagram.MNAME);
        factoryRegistry.registerExtensions(UseCaseDiagram.MNAME, StaticDiagram.MNAME);
    }

    @objid ("7c1c4013-5eff-11e2-b9cc-001ec947c8cc")
    @Override
    protected void declareStyleProviders(FactoryStyle factoryStyle) {
        // Actor
        factoryStyle.declareProvider(GmActorStructuredStyleKeys.class);
        factoryStyle.declareProvider(GmActorStructuredStyleKeys.Attribute.class);
        factoryStyle.declareProvider(GmActorStructuredStyleKeys.Operation.class);
        factoryStyle.declareProvider(GmActorStructuredStyleKeys.InternalStructure.class);
        
        // Use Case
        factoryStyle.declareProvider(GmUseCaseStructuredStyleKeys.class);
        factoryStyle.declareProvider(GmUseCaseStructuredStyleKeys.Attribute.class);
        factoryStyle.declareProvider(GmUseCaseStructuredStyleKeys.Operation.class);
        factoryStyle.declareProvider(GmUseCaseStructuredStyleKeys.ExtensionPoint.class);
        factoryStyle.declareProvider(GmUseCaseStructuredStyleKeys.Inner.class);
        factoryStyle.declareProvider(GmUseCaseStructuredStyleKeys.InternalStructure.class);
        
        // Use Case Dependency
        factoryStyle.declareProvider(GmUseCaseDependencyStyleKeys.class);
        
        // Use Case Diagram
        factoryStyle.declareProvider(GmUseCaseDiagramStyleKeys.class);
        
        // System
        factoryStyle.declareProvider(GmSystemStyleKeys.class);
    }

    @objid ("7c1c4015-5eff-11e2-b9cc-001ec947c8cc")
    @Override
    protected void declareFactorySettings(FactoryStyle factoryStyle) {
        // Load the default values.
        StyleLoader loader = new StyleLoader();
        BundleContext bundle = DiagramEditorUseCase.getContext();
        URL url = FileLocator.find(bundle.getBundle(), new Path("res/factory.settings"), null);
        
        loader.load(url);
        
        factoryStyle.injectDefaultValues(loader.getStyleProperties());
    }

}
