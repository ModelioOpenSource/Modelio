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
package org.modelio.uml.statediagram.editor.elements.connectionpoint;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statediagram.editor.style.StateAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmInputPin when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("f500c1f2-55b6-11e2-877f-002564c97630")
public class GmConnectionPointSimpleStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("815150ee-55c2-11e2-9337-002564c97630")
    static final StyleKey REPMODE = createStyleKey("CONNECTIONPOINT_REPMODE", MetaKey.REPMODE);

    @objid ("815150f0-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLCOLOR = createStyleKey("CONNECTIONPOINT_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("815150f2-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLMODE = createStyleKey("CONNECTIONPOINT_FILLMODE", MetaKey.FILLMODE);

    @objid ("815150f4-55c2-11e2-9337-002564c97630")
    static final StyleKey LINECOLOR = createStyleKey("CONNECTIONPOINT_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("815150f6-55c2-11e2-9337-002564c97630")
    static final StyleKey LINEWIDTH = createStyleKey("CONNECTIONPOINT_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("815150f8-55c2-11e2-9337-002564c97630")
    static final StyleKey FONT = createStyleKey("CONNECTIONPOINT_FONT", MetaKey.FONT);

    @objid ("815150fa-55c2-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = createStyleKey("CONNECTIONPOINT_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("815150fc-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = createStyleKey("CONNECTIONPOINT_SHOWSTEREOTYPES",
                                                               MetaKey.SHOWSTEREOTYPES);

    @objid ("815150fe-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = createStyleKey("CONNECTIONPOINT_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("81515100-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWLABEL = createStyleKey("CONNECTIONPOINT_SHOWLABEL", MetaKey.SHOWLABEL);

}
