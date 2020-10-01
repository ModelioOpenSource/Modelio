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
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode;

/**
 * Represents a diagram layer.
 * 
 * @since 3.1
 */
@objid ("482c2f21-4376-4e8c-b9a5-3732f18656f8")
public interface IDiagramLayer extends IDiagramGraphic {
    /**
     * @return the layer nodes
     */
    @objid ("5910c5b7-731e-41ad-88a9-a9733b393680")
    List<IDiagramNode> getNodes();

    /**
     * @return the layer links
     */
    @objid ("16c36542-44e5-4f0b-8983-c18af99f9678")
    List<IDiagramLink> getLinks();

}
