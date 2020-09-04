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

package org.modelio.uml.ui.dg.usecase;

import java.util.Collection;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramLink;
import org.modelio.diagram.editor.usecase.elements.usecasedependency.GmUseCaseDependency;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmNode;

/**
 * This class represents the DiagramGraphic of a 'UseCaseDependency' element.
 */
@objid ("6c50a578-71fc-48ea-b592-b9f7fba78018")
public class UseCaseDependencyDG extends DiagramLink {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param link The graphic model link represented by this class.
     */
    @objid ("ff3186dc-c7ce-49bb-bfad-66b5deb774c4")
    public UseCaseDependencyDG(DiagramHandle diagramHandle, IGmLink link) {
        super(diagramHandle, link);
    }

    @objid ("3a6bb88d-18f3-4838-8f10-0f369e1245fa")
    @Override
    protected Collection<IGmNode> getGmNodes(ExtensionRole role) {
        switch (role) {
        case EXTENSIONPOINT:  
            return getGmLink().getExtensions(GmUseCaseDependency.ROLE_EXTENSIONPOINTS);
        case MAIN:
            return getGmLink().getExtensions(IGmLink.ROLE_MAIN_LABEL);
            //$CASES-OMITTED$
        default:
            return Collections.emptyList();
        
        }
    }

}
