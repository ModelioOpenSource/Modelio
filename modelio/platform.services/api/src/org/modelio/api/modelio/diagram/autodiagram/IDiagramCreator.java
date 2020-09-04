/* 
 * Copyright 2013-2018 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.modelio.diagram.autodiagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * An IDiagramCreator provides the ability to create, update or get automatic diagrams from a model element.
 * @see IAutoDiagramFactory
 * @since 2.2
 */
@objid ("ceb6cfd3-7e65-11e1-b95c-002564c97630")
public interface IDiagramCreator {
    /**
     * Get The type of the current auto diagram this creator is managing.
     * @return the current auto diagram type.
     */
    @objid ("ceb6f6e6-7e65-11e1-b95c-002564c97630")
    String getAutoDiagramName();

    /**
     * Create or update an automatic diagram for the given element.
     * <p>
     * Only one automatic diagram of the current type can be created for an element.
     * </p>
     * @param main The element to center the automatic diagram on.
     * @return The corresponding automatic diagram.
     */
    @objid ("a3bee3d8-0ecc-11e2-96c4-002564c97630")
    AbstractDiagram createDiagram(final ModelElement main);

    /**
     * Get the current auto diagram centered on the given element.
     * @param main The element to look a diagram for.
     * @return The corresponding automatic diagram.
     */
    @objid ("a3bf0aec-0ecc-11e2-96c4-002564c97630")
    AbstractDiagram getExistingAutoDiagram(final ModelElement main);

    /**
     * Get The name of the current auto diagram this creator is managing.
     * @return the current auto diagram name.
     */
    @objid ("00a81567-5b74-4934-9c73-047b55b09ea2")
    String getAutoDiagramGroup();

    /**
     * Get The context of the current auto diagram this creator is managing.
     * @return the current auto diagram context.
     */
    @objid ("24d73029-ea6b-426d-b39a-8def6c26853a")
    ModelElement getAutoDiagramContext(final ModelElement main);

    /**
     * Get the element the auto diagram was created from.
     * @param main An automatic diagram.
     * @return The element the auto diagram was created from.
     */
    @objid ("86420083-9ae9-4013-98bd-5b346eadf692")
    ModelElement getMainElement(AbstractDiagram autoDiagram);

}
