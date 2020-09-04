/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.elements.common.genericnode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.style.ElementsAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * {@link GmGenericNode} style keys for the simple representation mode.
 */
@objid ("31050303-ecfc-4bff-926a-b97fbfa0e62c")
public class GmGenericNodeStyleKeys extends ElementsAbstractStyleKeyProvider {
    @objid ("d3bd3490-f20e-4ac8-92ec-e7066c66c268")
     static final StyleKey FILLCOLOR = createStyleKey("GENERICNODE_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("9b7190a0-67ed-4770-b021-30d4ecce7fea")
     static final StyleKey FILLMODE = createStyleKey("GENERICNODE_FILLMODE", MetaKey.FILLMODE);

    @objid ("668b115b-2500-4f25-943b-0b46d55f1492")
     static final StyleKey LINECOLOR = createStyleKey("GENERICNODE_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("c16d88a2-03a6-4254-956e-519e265b768f")
     static final StyleKey LINEWIDTH = createStyleKey("GENERICNODE_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("2f62d753-aab6-41fa-8717-f2bcb2fae2cb")
     static final StyleKey FONT = createStyleKey("GENERICNODE_FONT", MetaKey.FONT);

    @objid ("77a23a44-7071-4fee-a9dc-7c060d29754b")
     static final StyleKey TEXTCOLOR = createStyleKey("GENERICNODE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("98d67c93-e439-40f9-ae88-f4f7ea15572e")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("GENERICNODE_SHOWSTEREOTYPES", MetaKey.SHOWSTEREOTYPES);

    @objid ("b3ab2d5c-9d41-43ee-9583-cb628a396b1d")
     static final StyleKey SHOWTAGS = createStyleKey("GENERICNODE_SHOWTAGS", MetaKey.SHOWTAGS);

}
