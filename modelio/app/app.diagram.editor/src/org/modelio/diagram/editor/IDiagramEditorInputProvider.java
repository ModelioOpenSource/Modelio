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
package org.modelio.diagram.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.metamodel.diagrams.AbstractDiagram;

/**
 * This interface is called when opening a diagram editor, to initialize its contents.
 * <p>
 * It is to be implemented to contribute to the {@link DiagramEditorInputProvider#INPUTPROVIDER_ID} extension point.
 * </p>
 * <p>
 * Implementers must have a default constructor (i.e. without arguments) in order to be instanciated by the eclipse platform.
 * </p>
 */
@objid ("be793a8a-5a77-11e2-9c97-002564c97630")
public interface IDiagramEditorInputProvider {
    /**
     * Instanciate a {@link DiagramEditorInput} for a diagram.
     * @param diagramUid the UUID of an existing diagram.
     * @param modelManager a diagram model manager.
     * @return the editor input, or <code>null</code> if the diagram's instance can't be handled by this provider.
     */
    @objid ("be793a8b-5a77-11e2-9c97-002564c97630")
    DiagramEditorInput compute(String diagramUid, IModelManager modelManager);

    @objid ("781902f8-3d95-4ba2-a8b8-9f0a113f33b6")
    GmDiagramCreator getDiagramCreator();

    @objid ("34471e22-62b6-49bd-8d2c-61e6c7b80209")
    public interface GmDiagramCreator {
        /**
         * Create a {@link GmAbstractDiagram} from an AbstractDiagram.
         * <p>
         * Warning: returned gm is neither loader nor visible, it should be done by the caller.
         * <p>
         * <code>
         * // Make the diagram visible at GM level.<br/>
         * diagram.setVisible(true);<br/>
         * // Load from the persistence.<br/>
         * diagram.getPersister().load();
         * </code>
         * </p>
         * @param modelManager a diagram model manager
         * @param diagram The element to unmask
         * @return The created graphic node.
         */
        @objid ("45da17df-8623-40e6-b236-f85548a0da28")
        GmAbstractDiagram createDiagram(IModelManager modelManager, AbstractDiagram diagram);

    }

}
