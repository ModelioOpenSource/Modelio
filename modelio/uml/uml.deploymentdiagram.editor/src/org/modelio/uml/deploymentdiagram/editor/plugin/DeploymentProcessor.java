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
package org.modelio.uml.deploymentdiagram.editor.plugin;

import java.net.URL;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.modelio.diagram.editor.plugin.IDiagramConfigurerRegistry;
import org.modelio.diagram.editor.processor.AbstractDiagramInitializationProcessor;
import org.modelio.diagram.elements.core.model.factory.DiagramFactoryRegistry;
import org.modelio.diagram.styles.core.FactoryStyle;
import org.modelio.diagram.styles.core.StyleLoader;
import org.modelio.metamodel.diagrams.DeploymentDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.uml.deploymentdiagram.editor.editor.DeploymentDiagramConfigurer;
import org.modelio.uml.deploymentdiagram.editor.elements.artifact.GmArtifactStructuredStyleKeys;
import org.modelio.uml.deploymentdiagram.editor.elements.deploymentdiagram.GmDeploymentDiagramStyleKeys;
import org.modelio.uml.deploymentdiagram.editor.elements.factories.DeploymentEditPartFactory;
import org.modelio.uml.deploymentdiagram.editor.elements.factories.DeploymentGmLinkFactory;
import org.modelio.uml.deploymentdiagram.editor.elements.factories.DeploymentGmNodeFactory;
import org.modelio.uml.deploymentdiagram.editor.elements.manifestation.GmManifestationStyleKeys;
import org.modelio.uml.deploymentdiagram.editor.elements.node.GmNodeStructuredStyleKeys;
import org.osgi.framework.BundleContext;

/**
 * Processor initializing environment for the {@link DeploymentDiagram} diagram.
 */
@objid ("bebd9066-5bf2-11e2-a156-00137282c51b")
public class DeploymentProcessor extends AbstractDiagramInitializationProcessor {
    @objid ("ebf98df1-5bf2-11e2-a156-00137282c51b")
    @Override
    protected void declareDiagramConfigurers(IDiagramConfigurerRegistry configurerRegistry) {
        configurerRegistry.registerDiagramConfigurer(DeploymentDiagram.MNAME, null, new DeploymentDiagramConfigurer());
    }

    @objid ("ec00b4ff-5bf2-11e2-a156-00137282c51b")
    @Override
    protected void declareFactories(DiagramFactoryRegistry factoryRegistry) {
        factoryRegistry.registerDiagramFactories(DeploymentDiagram.MNAME, new DeploymentGmNodeFactory(), new DeploymentGmLinkFactory(), new DeploymentEditPartFactory());
        
        // Static elements should be usable in Deployment diagram
        factoryRegistry.registerExtensions(DeploymentDiagram.MNAME, StaticDiagram.MNAME);
        
        // Deployment elements should be usable in every Static diagram
        factoryRegistry.registerExtensions(StaticDiagram.MNAME, DeploymentDiagram.MNAME);
        
    }

    @objid ("ec00b501-5bf2-11e2-a156-00137282c51b")
    @Override
    protected void declareStyleProviders(FactoryStyle factoryStyle) {
        // Artifact
        factoryStyle.declareProvider(GmArtifactStructuredStyleKeys.class);
        factoryStyle.declareProvider(GmArtifactStructuredStyleKeys.Attribute);
        factoryStyle.declareProvider(GmArtifactStructuredStyleKeys.Inner);
        factoryStyle.declareProvider(GmArtifactStructuredStyleKeys.InternalStructure);
        factoryStyle.declareProvider(GmArtifactStructuredStyleKeys.Operation);
        
        // Node
        factoryStyle.declareProvider(GmNodeStructuredStyleKeys.class);
        factoryStyle.declareProvider(GmNodeStructuredStyleKeys.Attribute);
        factoryStyle.declareProvider(GmNodeStructuredStyleKeys.Inner);
        factoryStyle.declareProvider(GmNodeStructuredStyleKeys.InternalStructure);
        factoryStyle.declareProvider(GmNodeStructuredStyleKeys.Operation);
        
        // Manifestation
        factoryStyle.declareProvider(GmManifestationStyleKeys.class);
        
        // Deployment Diagram
        factoryStyle.declareProvider(GmDeploymentDiagramStyleKeys.class);
        
    }

    @objid ("ec00b503-5bf2-11e2-a156-00137282c51b")
    @Override
    protected void declareFactorySettings(FactoryStyle factoryStyle) {
        // Load the default values. Do it only here, after key providers registration.
        StyleLoader loader = new StyleLoader();
        BundleContext bundle = DiagramEditorDeployment.getContext();
        URL url = FileLocator.find(bundle.getBundle(), new Path("res/factory.settings"), null);
        
        loader.load(url);
        
        factoryStyle.injectDefaultValues(loader.getStyleProperties());
        
    }

}
