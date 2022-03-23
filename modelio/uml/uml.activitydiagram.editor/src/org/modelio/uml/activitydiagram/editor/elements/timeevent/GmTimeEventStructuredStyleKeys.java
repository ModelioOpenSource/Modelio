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
package org.modelio.uml.activitydiagram.editor.elements.timeevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmTimeEvent when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("2b651fc9-55b6-11e2-877f-002564c97630")
public class GmTimeEventStructuredStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d0c0510f-55c0-11e2-9337-002564c97630")
    static final StyleKey REPMODE = createStyleKey("TIMEEVENT_REPMODE", MetaKey.REPMODE);

    @objid ("d0c05111-55c0-11e2-9337-002564c97630")
    static final StyleKey FILLCOLOR = createStyleKey("TIMEEVENT_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("d0c05113-55c0-11e2-9337-002564c97630")
    static final StyleKey FILLMODE = createStyleKey("TIMEEVENT_FILLMODE", MetaKey.FILLMODE);

    @objid ("d0c05115-55c0-11e2-9337-002564c97630")
    static final StyleKey LINECOLOR = createStyleKey("TIMEEVENT_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d0c05117-55c0-11e2-9337-002564c97630")
    static final StyleKey LINEWIDTH = createStyleKey("TIMEEVENT_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d0c05119-55c0-11e2-9337-002564c97630")
    static final StyleKey FONT = createStyleKey("TIMEEVENT_FONT", MetaKey.FONT);

    @objid ("d0c1d7aa-55c0-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = createStyleKey("TIMEEVENT_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d0c1d7ac-55c0-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = createStyleKey("TIMEEVENT_SHOWSTEREOTYPES",
                MetaKey.SHOWSTEREOTYPES);

    @objid ("d0c1d7ae-55c0-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = createStyleKey("TIMEEVENT_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("d0c1d7b0-55c0-11e2-9337-002564c97630")
    static final StyleKey AUTOSHOWPINS = createStyleKey("TIMEEVENT_AUTOSHOWPINS", MetaKey.AUTOSHOWPINS);

}
