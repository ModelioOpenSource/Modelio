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

package org.modelio.diagram.editor.activity.elements.changeevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Font;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmChangeevent when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("2a0149fe-55b6-11e2-877f-002564c97630")
public class GmChangeEventStructuredStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d1b167dd-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("CHANGEEVENT_REPMODE", MetaKey.REPMODE);

    @objid ("d1b167df-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("CHANGEEVENT_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("d1b167e1-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("CHANGEEVENT_FILLMODE", MetaKey.FILLMODE);

    @objid ("d1b167e3-55c0-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("CHANGEEVENT_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d1b167e5-55c0-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("CHANGEEVENT_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d1b167e7-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("CHANGEEVENT_FONT", MetaKey.FONT);

    @objid ("d1b167e9-55c0-11e2-9337-002564c97630")
    public static StyleKey EXPRESSIONFONT = createStyleKey("CHANGEEVENT_EXPRESSIONFONT", Font.class);

    @objid ("d1b167ea-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("CHANGEEVENT_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d1b167ec-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("CHANGEEVENT_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("d1b167ee-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("CHANGEEVENT_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("d1b167f0-55c0-11e2-9337-002564c97630")
     static final StyleKey AUTOSHOWPINS = createStyleKey("CHANGEEVENT_AUTOSHOWPINS", MetaKey.AUTOSHOWPINS);

}
