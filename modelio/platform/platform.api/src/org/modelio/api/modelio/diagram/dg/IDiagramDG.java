/* 
 * Copyright 2013-2020 Modeliosoft
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

package org.modelio.api.modelio.diagram.dg;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode;

/**
 * This class represents a diagram graphic for the diagram itself.
 * <p>
 * A <code>IDiagramDG</code> id a {@link IDiagramNode}, root nodes can be retrieved
 * using {@link #getNodes()}.
 * <p>
 * Since Modelio 3.1 a diagram contains layers containing either elements graphics or
 * drawing, graphics ordered from background to foreground:<ul>
 * <li> a {@link IDiagramDrawingsLayer#BACKGROUND} drawing layer. It can contain only drawings
 * <li> a {@link IDiagramElementsLayer#MAIN} elements graphic layer.
 * It can contain only model graphics.
 * <li> a {@link IDiagramDrawingsLayer#TOP} drawing layer. It can contain only drawings
 * </ul>
 * In future versions, a diagram will be able to contain more layers.
 */
@objid ("55f641c9-6b43-11e0-b0b9-002564c97630")
public interface IDiagramDG extends IDiagramNode {
    /**
     * Return the list of children links of this diagram.
     * 
     * @return A list of links in any case, possibly an empty one. Never returns null
     */
    @objid ("55f668da-6b43-11e0-b0b9-002564c97630")
    List<IDiagramLink> getLinks();

    /**
     * Get all diagram layers.
     * <p>
     * The returned list is ordered from the background layer to the top layer.
     * 
     * @return A list of diagram layer .
     * 
     * @since 3.1
     */
    @objid ("2b6a43f5-c3ba-4fb3-8d52-5cc0ef0bf2ff")
    List<IDiagramLayer> getLayers();

    /**
     * Get the diagram layer identified by the provided string.
     * <p>
     * Identifier constants are provided in {@link IDiagramDrawingsLayer} and {@link IDiagramElementsLayer}.
     * 
     * @param layerIdentifier the layer identifier.
     * @return the matching diagram layer or <code>null</code>.
     * 
     * @since 3.1
     */
    @objid ("d1902a1b-63b6-4b7e-b4f3-04e4207c9133")
    IDiagramLayer getLayer(String layerIdentifier);

    /**
     * Get the diagram drawings layer identified by the provided string.
     * <p>
     * Identifier constants are provided in {@link IDiagramDrawingsLayer}.
     * 
     * @param layerIdentifier the layer identifier.
     * @return the matching diagram layer or <code>null</code>.
     * 
     * @since 3.1
     */
    @objid ("9a24c168-07b7-433e-b67c-0812081179bd")
    IDiagramDrawingsLayer getDrawingsLayer(String layerIdentifier);

    /**
     * Get the diagram elements layer identified by the provided string.
     * <p>
     * Identifier constants are provided in {@link IDiagramElementsLayer}.
     * 
     * @param layerIdentifier the layer identifier.
     * @return the matching diagram layer or <code>null</code>.
     * 
     * @since 3.1
     */
    @objid ("499c80fc-3f7a-404d-a2df-4a512c3d897d")
    IDiagramElementsLayer getElementsLayer(String layerIdentifier);

}
