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

package org.modelio.uml.ui.dg.statik;

import java.util.Collection;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramLink;
import org.modelio.diagram.editor.statik.elements.instancelink.GmInstanceLink;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmNode;

/**
 * This class represents the DiagramGraphic of a 'Link' element.
 */
@objid ("eaa34f46-8359-4e22-912a-db3652c8f4f7")
public class LinkDG extends DiagramLink {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param link The gm link represented by this class.
     */
    @objid ("646c1da5-04d4-465e-ae02-3a8cab3a839c")
    public LinkDG(DiagramHandle diagramHandle, IGmLink link) {
        super(diagramHandle, link);
    }

    @objid ("5c7fe56e-4f30-4e6a-af0e-a835c2e9f518")
    @Override
    protected Collection<IGmNode> getGmNodes(ExtensionRole role) {
        switch (role) {
        case FROM_AUX:
            return getGmLink().getExtensions(GmInstanceLink.ROLE_SRC_CARD);
        case FROM_MAIN:
            return getGmLink().getExtensions(GmInstanceLink.ROLE_SRC_MAIN);
        case MAIN:
            return getGmLink().getExtensions(IGmLink.ROLE_MAIN_LABEL);
        case TO_AUX:
            return getGmLink().getExtensions(GmInstanceLink.ROLE_TARGET_CARD);
        case TO_MAIN:
            return getGmLink().getExtensions(GmInstanceLink.ROLE_TARGET_MAIN);
        case FROM_INFOFLOW_ARROW:
            return getGmLink().getExtensions(GmInstanceLink.ROLE_SRC_INFOFLOW_ARROW);
        case FROM_INFOFLOW_GROUP:
            return getGmLink().getExtensions(GmInstanceLink.ROLE_SRC_INFOFLOW_GRP);
        case TO_INFOFLOW_ARROW:
            return getGmLink().getExtensions(GmInstanceLink.ROLE_TARGET_INFOFLOW_ARROW);
        case TO_INFOFLOW_GROUP:
            return getGmLink().getExtensions(GmInstanceLink.ROLE_TARGET_INFOFLOW_GRP);
        case FROM_QUALIFIER_GROUP:
        case TO_QUALIFIER_GROUP:
        case EDGE_GUARD:
        case EDGE_WEIGHT:
        default:
            return Collections.emptyList();
        
        }
    }

}
