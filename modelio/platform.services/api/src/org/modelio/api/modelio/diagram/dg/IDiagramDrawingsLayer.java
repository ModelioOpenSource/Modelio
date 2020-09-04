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
 * Layer containing drawing node and links.
 * 
 * @since 3.1
 */
@objid ("67c3964e-8217-4984-b72d-957e48d494da")
public interface IDiagramDrawingsLayer extends IDiagramLayer {
    /**
     * Identifier of the background drawings layer.
     */
    @objid ("f458211a-8a51-432a-9b4c-dd1d3ac962ba")
    public static final String BACKGROUND = "background_layer";

    /**
     * Identifier of the foreground drawings layer.
     * <p>
     * This layer is on top of the main diagram elements layer.
     */
    @objid ("b8bf8229-877d-4f00-8f1e-f11864bc02a7")
    public static final String TOP = "top_layer";

    /**
     * @return the drawing nodes.
     */
    @objid ("26bef9ff-120c-48da-891c-27954aa2f7d0")
    List<IDiagramNode> getDrawingNodes();

    /**
     * @return the drawing links.
     */
    @objid ("9845a5b2-c9c1-4ffc-ae63-4a6a4bdbb91e")
    List<IDiagramLink> getDrawingLinks();

}
