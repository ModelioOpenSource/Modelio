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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramLink;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmNode;

/**
 * This class represents the DiagramGraphic of a 'InterfaceRealization' element.
 */
@objid ("61e681b7-1358-4d98-98fe-5273c27983ce")
public class InterfaceRealizationDG extends DiagramLink {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param link The gm link represented by this class.
     */
    @objid ("31c8daf0-d32e-42dc-b3aa-16dddbd6ec10")
    public InterfaceRealizationDG(DiagramHandle diagramHandle, IGmLink link) {
        super(diagramHandle, link);
    }

    @objid ("9cb976df-fa83-4512-a8fb-8b68186076a0")
    @Override
    protected Collection<IGmNode> getGmNodes(ExtensionRole role) {
        return defaultGetGmNodes(role);
    }

}
