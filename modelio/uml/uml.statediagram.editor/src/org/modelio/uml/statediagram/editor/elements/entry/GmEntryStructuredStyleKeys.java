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

package org.modelio.uml.statediagram.editor.elements.entry;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statediagram.editor.style.StateAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmEntry when its representation mode is
 * RepresentationMode.STRUCTURED
 */
@objid ("f5161ebc-55b6-11e2-877f-002564c97630")
public class GmEntryStructuredStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("812e38a2-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("ENTRY_REPMODE", MetaKey.REPMODE);

    @objid ("812e38a4-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("ENTRY_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("812e38a6-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("ENTRY_FILLMODE", MetaKey.FILLMODE);

    @objid ("812e38a8-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("ENTRY_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("812e38aa-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("ENTRY_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("812e38ac-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("ENTRY_FONT", MetaKey.FONT);

    @objid ("812e38ae-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("ENTRY_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("812fbf2a-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("ENTRY_SHOWSTEREOTYPES", MetaKey.SHOWSTEREOTYPES);

    @objid ("812fbf2c-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("ENTRY_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("812fbf2e-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = createStyleKey("ENTRY_SHOWLABEL", MetaKey.SHOWLABEL);

}
