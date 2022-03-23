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
package org.modelio.uml.activitydiagram.editor.elements.initial;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmInitial when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("2aa48ed9-55b6-11e2-877f-002564c97630")
public class GmInitialStructuredStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d21f92fe-55c0-11e2-9337-002564c97630")
    static final StyleKey REPMODE = createStyleKey("INITIAL_REPMODE", MetaKey.REPMODE);

    @objid ("d21f9300-55c0-11e2-9337-002564c97630")
    static final StyleKey FILLCOLOR = createStyleKey("INITIAL_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("d21f9302-55c0-11e2-9337-002564c97630")
    static final StyleKey FILLMODE = createStyleKey("INITIAL_FILLMODE", MetaKey.FILLMODE);

    @objid ("d21f9304-55c0-11e2-9337-002564c97630")
    static final StyleKey LINEWIDTH = createStyleKey("INITIAL_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d21f9306-55c0-11e2-9337-002564c97630")
    static final StyleKey FONT = createStyleKey("INITIAL_FONT", MetaKey.FONT);

    @objid ("d21f9308-55c0-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = createStyleKey("INITIAL_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d21f930a-55c0-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = createStyleKey("INITIAL_SHOWSTEREOTYPES", MetaKey.SHOWSTEREOTYPES);

    @objid ("d21f930c-55c0-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = createStyleKey("INITIAL_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Show the element name label.
     * 
     * @see MetaKey#SHOWLABEL
     */
    @objid ("d21f930e-55c0-11e2-9337-002564c97630")
    static final StyleKey SHOWLABEL = createStyleKey("INITIAL_SHOWLABEL", MetaKey.SHOWLABEL);

}
