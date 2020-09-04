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

package org.modelio.api.modelio.diagram.dg;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode;

/**
 * Layer containing element nodes and links.
 * 
 * @since 3.1
 */
@objid ("c8f7be5c-ba01-45d7-9e62-21888473eced")
public interface IDiagramElementsLayer extends IDiagramLayer {
    /**
     * Identifier of the main model layer.
     */
    @objid ("c3002849-7624-4ed2-98bc-d98be71cbb98")
    public static final String MAIN = "main_layer";

    /**
     * @return the element nodes.
     */
    @objid ("0c4ba134-cf92-4e49-9b6e-7297f80d82c1")
    List<IDiagramNode> getElementNodes();

    /**
     * @return the element links.
     */
    @objid ("29dac33c-3a37-4054-90ea-9b113ca3b37a")
    List<IDiagramLink> getElementLinks();

}
