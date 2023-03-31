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
package org.modelio.api.modelio.diagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Diagram drawings are diagram graphics that are by nature not related to a model elements.
 * <p>
 * Diagram drawings nodes have an associated label.
 * </p>
 * @since 3.8
 */
@objid ("5f49ec98-a86f-42a5-b0d6-863381690712")
public interface IDiagramDrawingNode extends IDiagramDrawing, IDiagramNode {
    /**
     * @return the label displayed by the drawing.
     */
    @objid ("317cee04-08d3-4aa3-8aa6-df3443c89440")
    String getLabel();

    /**
     * Change the label displayed by the drawing.
     * @param label the new drawing's label.
     */
    @objid ("7ce1e4d3-c7cb-4023-a5f4-74d477e8ee8f")
    void setLabel(String label);
}

