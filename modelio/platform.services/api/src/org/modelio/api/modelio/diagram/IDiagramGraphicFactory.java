/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.api.modelio.diagram;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.dg.IDiagramDrawingsLayer;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Factory to create or unmask graphic objects.
 * 
 * @since 3.1
 */
@objid ("6506003a-5b51-420c-b9d4-d9b8efa15686")
public interface IDiagramGraphicFactory {
    /**
     * Creates a drawing rectangle.
     * 
     * @param layer the layer to use
     * @param drawingIdentifier An identifier unique in the diagram, to be able to look for the graphic later.
     * If <i>null</i>, an identifier will be automatically generated.
     * @param x the x coordinates of the top left corner.
     * @param y the y coordinates of the top left corner.
     * @param w the node width
     * @param h the node height
     * @return the created graphic node.
     */
    @objid ("e1da075b-f0d9-40df-a08a-0b8235e2276b")
    IDiagramDrawingNode createDrawingRectangle(IDiagramDrawingsLayer layer, String drawingIdentifier, int x, int y, int w, int h);

    /**
     * Creates a drawing ellipse.
     * 
     * @param layer the layer to use
     * @param drawingIdentifier An identifier unique in the diagram, to be able to look for the graphic later.
     * If <i>null</i>, an identifier will be automatically generated.
     * @param x the x coordinates of the graphic position.
     * @param y the y coordinates of the graphic position.
     * @param w the node width
     * @param h the node height
     * @return the created graphic node.
     */
    @objid ("fc095700-36fd-46a7-aa85-19a0f8c4c214")
    IDiagramDrawingNode createDrawingEllipse(IDiagramDrawingsLayer layer, String drawingIdentifier, int x, int y, int w, int h);

    /**
     * Creates a drawing rectangle.
     * 
     * @param layer the layer to use
     * @param drawingIdentifier An identifier unique in the diagram, to be able to look for the graphic later.
     * If <i>null</i>, an identifier will be automatically generated.
     * @param label the text label
     * @param x the x coordinates of the graphic position.
     * @param y the y coordinates of the graphic position.
     * @param w the node width
     * @param h the node height
     * @return the created graphic node.
     */
    @objid ("72508aae-9d0d-44d0-83ad-6fabae3a0918")
    IDiagramDrawingNode createDrawingText(IDiagramDrawingsLayer layer, String drawingIdentifier, String label, int x, int y, int w, int h);

    /**
     * Creates a drawing line
     * 
     * @param layer the layer to use
     * @param drawingIdentifier An identifier unique in the diagram, to be able to look for the graphic later.
     * If <i>null</i>, an identifier will be automatically generated.
     * @param x the x coordinates of the first point.
     * @param y the y coordinates of the first point.
     * @param x2 the x coordinates of the second point.
     * @param y2 the y coordinates of the second point.
     * @return the created line.
     */
    @objid ("5a427d8c-1596-4a4f-a0bc-416618656a17")
    IDiagramDrawingLink createDrawingLine(IDiagramDrawingsLayer layer, String drawingIdentifier, int x, int y, int x2, int y2);

    /**
     * Unmask a model element in a diagram.
     * <p>
     * The model element is unmasked at the given position.
     * 
     * @param element the model element to unmask.
     * @param x the x coordinates of the unmasking position.
     * @param y the y coordinates of the unmasking position.
     * @return a list of all the new diagram graphics. Might be empty, but not <code>null</code>.
     */
    @objid ("642b7cbc-3602-4210-8ffd-e2af9f6099c4")
    List<IDiagramGraphic> unmask(MObject element, int x, int y);

}
