/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.statik.elements.componentRealization;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * header displayed on the link.
 * 
 * @author cmarin
 */
@objid ("13723c48-8637-49e0-8313-976276576f62")
public class GmComponentRealizationHeader extends GmDefaultModelElementLabel {
    @objid ("33f4f62d-e513-4dee-90e0-5fddee18a5ca")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor.
     * 
     * @param diagram the diagram
     * @param relatedRef a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("125d52b8-3ba4-406b-9fe3-2ec1d4fca33b")
    public GmComponentRealizationHeader(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * For deserialization only.
     */
    @objid ("a5d44ce7-be08-40a8-a1c5-886add47672e")
    public GmComponentRealizationHeader() {
    }

    @objid ("1f9b8add-18e2-464d-b385-2c5c220d65c7")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
