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

package org.modelio.uml.activitydiagram.editor.elements.acceptsignal;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmAcceptsignal when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("297e103d-55b6-11e2-877f-002564c97630")
public class GmAcceptSignalStructuredStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d0e4f01d-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("ACCEPTSIGNAL_REPMODE", MetaKey.REPMODE);

    @objid ("d0e4f01f-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("ACCEPTSIGNAL_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("d0e4f021-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("ACCEPTSIGNAL_FILLMODE", MetaKey.FILLMODE);

    @objid ("d0e4f023-55c0-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("ACCEPTSIGNAL_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d0e4f025-55c0-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("ACCEPTSIGNAL_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d0e4f027-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("ACCEPTSIGNAL_FONT", MetaKey.FONT);

    @objid ("d0e676aa-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("ACCEPTSIGNAL_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d0e676ac-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("ACCEPTSIGNAL_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("d0e676ae-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("ACCEPTSIGNAL_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("d0e676b0-55c0-11e2-9337-002564c97630")
     static final StyleKey AUTOSHOWPINS = createStyleKey("ACCEPTSIGNAL_AUTOSHOWPINS", MetaKey.AUTOSHOWPINS);

}
