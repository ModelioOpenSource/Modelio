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

package org.modelio.diagram.editor.processor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.gef.EditPartFactory;
import org.modelio.diagram.editor.plugin.IDiagramConfigurer;
import org.modelio.diagram.editor.plugin.IDiagramConfigurerRegistry;
import org.modelio.diagram.elements.core.model.factory.DiagramFactoryRegistry;
import org.modelio.diagram.elements.core.model.factory.IGmLinkFactory;
import org.modelio.diagram.elements.core.model.factory.IGmNodeFactory;
import org.modelio.diagram.styles.core.FactoryStyle;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * E4 processor to be extended by plugins providing diagram implementations.
 */
@objid ("4add729b-d755-4a97-9bb5-0ae2d97687c7")
public abstract class AbstractDiagramInitializationProcessor {
    @objid ("8762af56-d83d-4336-9278-ba5d2a54db65")
    @Execute
    protected void execute(IDiagramConfigurerRegistry configurerRegistry) {
        declareDiagramConfigurers(configurerRegistry);
        
        declareFactories(DiagramFactoryRegistry.getInstance());
        
        FactoryStyle factoryStyle = FactoryStyle.getInstance();
        declareStyleProviders(factoryStyle);
        
        // Do it only after style key providers registration.
        declareFactorySettings(factoryStyle);
    }

    /**
     * Register supported {@link IDiagramConfigurer}.
     */
    @objid ("467a4cc7-352b-4a17-a0a0-25122dccab73")
    protected abstract void declareDiagramConfigurers(IDiagramConfigurerRegistry configurerRegistry);

    /**
     * Declare {@link IGmNodeFactory}, {@link IGmLinkFactory} and {@link EditPartFactory} for each supported diagram.
     */
    @objid ("d4ba4c84-32b6-420c-bf92-57ef1c2ec8ca")
    protected abstract void declareFactories(DiagramFactoryRegistry factoryRegistry);

    /**
     * Declare supported {@link StyleKey}.
     */
    @objid ("9cb3e803-3535-4d3b-ae50-4bce48798824")
    protected abstract void declareStyleProviders(FactoryStyle factoryStyle);

    /**
     * Load the default values for the supported {@link StyleKey}.
     */
    @objid ("0f3e92c3-a86f-46f2-a957-1ed88c93a4b9")
    protected abstract void declareFactorySettings(FactoryStyle factoryStyle);

}
