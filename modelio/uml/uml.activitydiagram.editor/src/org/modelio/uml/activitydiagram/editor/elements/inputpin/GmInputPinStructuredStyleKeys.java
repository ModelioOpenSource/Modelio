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
package org.modelio.uml.activitydiagram.editor.elements.inputpin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmInputPin when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("2aaf3d39-55b6-11e2-877f-002564c97630")
public class GmInputPinStructuredStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d22426dc-55c0-11e2-9337-002564c97630")
    static final StyleKey REPMODE = createStyleKey("INPUTPIN_REPMODE", MetaKey.REPMODE);

    @objid ("d22426de-55c0-11e2-9337-002564c97630")
    static final StyleKey FILLCOLOR = createStyleKey("INPUTPIN_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("d22426e0-55c0-11e2-9337-002564c97630")
    static final StyleKey FILLMODE = createStyleKey("INPUTPIN_FILLMODE", MetaKey.FILLMODE);

    @objid ("d22426e2-55c0-11e2-9337-002564c97630")
    static final StyleKey LINECOLOR = createStyleKey("INPUTPIN_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d22426e4-55c0-11e2-9337-002564c97630")
    static final StyleKey LINEWIDTH = createStyleKey("INPUTPIN_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d22426e6-55c0-11e2-9337-002564c97630")
    static final StyleKey FONT = createStyleKey("INPUTPIN_FONT", MetaKey.FONT);

    @objid ("d22426e8-55c0-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = createStyleKey("INPUTPIN_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d22426ea-55c0-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = createStyleKey("INPUTPIN_SHOWSTEREOTYPES",
                MetaKey.SHOWSTEREOTYPES);

    @objid ("d22426ec-55c0-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = createStyleKey("INPUTPIN_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("d22426ee-55c0-11e2-9337-002564c97630")
    static final StyleKey SHOWLABEL = createStyleKey("INPUTPIN_SHOWLABEL", MetaKey.SHOWLABEL);

}
