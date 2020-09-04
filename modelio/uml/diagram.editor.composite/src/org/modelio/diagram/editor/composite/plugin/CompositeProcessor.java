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

package org.modelio.diagram.editor.composite.plugin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.composite.editor.CompositeDiagramConfigurer;
import org.modelio.diagram.editor.plugin.IDiagramConfigurerRegistry;
import org.modelio.diagram.editor.processor.AbstractDiagramInitializationProcessor;
import org.modelio.diagram.elements.core.model.factory.DiagramFactoryRegistry;
import org.modelio.diagram.styles.core.FactoryStyle;
import org.modelio.metamodel.diagrams.CompositeStructureDiagram;
import org.modelio.metamodel.diagrams.ObjectDiagram;

/**
 * Processor initializing environment for the {@link CompositeStructureDiagram} diagram.
 * <p>
 * As a {@link CompositeStructureDiagram} is basically an {@link ObjectDiagram} with a different palette, most methods here are empty.
 * </p>
 */
@objid ("d2b1d20e-5bd8-11e2-9e33-00137282c51b")
public class CompositeProcessor extends AbstractDiagramInitializationProcessor {
    @objid ("e274f01b-4559-462a-971e-b2a0da5af06b")
    @Override
    protected void declareFactories(DiagramFactoryRegistry factoryRegistry) {
        // No factories
    }

    @objid ("ff780479-59e9-429d-be64-0ca20ca9b1c2")
    @Override
    protected void declareDiagramConfigurers(IDiagramConfigurerRegistry configurerRegistry) {
        configurerRegistry.registerDiagramConfigurer(CompositeStructureDiagram.MNAME, null, new CompositeDiagramConfigurer());
    }

    @objid ("f7d54c2a-29a5-4049-a9b0-5cf1f4341bc4")
    @Override
    protected void declareStyleProviders(FactoryStyle factoryStyle) {
        // No StyleKeys
    }

    @objid ("aba13a8b-9f57-4295-afee-3ba0b8c6a05c")
    @Override
    protected void declareFactorySettings(FactoryStyle factoryStyle) {
        // No factory settings
    }

}
