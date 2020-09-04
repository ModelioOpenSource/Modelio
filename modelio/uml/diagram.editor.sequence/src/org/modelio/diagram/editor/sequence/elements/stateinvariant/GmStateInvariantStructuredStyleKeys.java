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

package org.modelio.diagram.editor.sequence.elements.stateinvariant;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.sequence.style.SequenceAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * StyleKey provider for the GmStateInvariant class.
 */
@objid ("d999125f-55b6-11e2-877f-002564c97630")
public class GmStateInvariantStructuredStyleKeys extends SequenceAbstractStyleKeyProvider {
    @objid ("5070318a-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("STATEINVARIANT_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("5070318c-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("STATEINVARIANT_FILLMODE", MetaKey.FILLMODE);

    @objid ("5070318e-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("STATEINVARIANT_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("50703190-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("STATEINVARIANT_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("50703192-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("STATEINVARIANT_FONT", MetaKey.FONT);

    @objid ("50703194-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("STATEINVARIANT_TEXTCOLOR", MetaKey.TEXTCOLOR);

}
