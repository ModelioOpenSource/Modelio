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

package org.modelio.diagram.editor.activity.elements.callbehavior;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmCallBehavior when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("29c133c8-55b6-11e2-877f-002564c97630")
public class GmCallBehaviorStructuredStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d171518a-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("CALLBEHAVIOR_REPMODE", MetaKey.REPMODE);

    @objid ("d171518c-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("CALLBEHAVIOR_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("d171518e-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("CALLBEHAVIOR_FILLMODE", MetaKey.FILLMODE);

    @objid ("d1715190-55c0-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("CALLBEHAVIOR_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d1715192-55c0-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("CALLBEHAVIOR_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d1715194-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("CALLBEHAVIOR_FONT", MetaKey.FONT);

    @objid ("d1715196-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("CALLBEHAVIOR_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d1715198-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("CALLBEHAVIOR_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("d171519a-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("CALLBEHAVIOR_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("d171519c-55c0-11e2-9337-002564c97630")
     static final StyleKey AUTOSHOWPINS = createStyleKey("CALLBEHAVIOR_AUTOSHOWPINS", MetaKey.AUTOSHOWPINS);

}
