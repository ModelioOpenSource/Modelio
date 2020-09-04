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

package org.modelio.diagram.editor.activity.elements.callevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmCallEvent when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("29ceef82-55b6-11e2-877f-002564c97630")
public class GmCallEventStructuredStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d0ef9e6a-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("CALLEVENT_REPMODE", MetaKey.REPMODE);

    @objid ("d0ef9e6c-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("CALLEVENT_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("d0ef9e6e-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("CALLEVENT_FILLMODE", MetaKey.FILLMODE);

    @objid ("d0ef9e70-55c0-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("CALLEVENT_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d0ef9e72-55c0-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("CALLEVENT_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d0ef9e74-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("CALLEVENT_FONT", MetaKey.FONT);

    @objid ("d0ef9e76-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("CALLEVENT_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d0ef9e78-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("CALLEVENT_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("d0ef9e7a-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("CALLEVENT_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("d0ef9e7c-55c0-11e2-9337-002564c97630")
     static final StyleKey AUTOSHOWPINS = createStyleKey("CALLEVENT_AUTOSHOWPINS", MetaKey.AUTOSHOWPINS);

}
