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
 * This class provides the StyleKey constants for a GmInputPin when its representation mode is RepresentationMode.IMAGE
 */
@objid ("f4fdb4c7-55b6-11e2-877f-002564c97630")
public class GmConnectionPointImageStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("814fca4c-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("CONNECTIONPOINT_REPMODE", MetaKey.REPMODE);

    @objid ("814fca4e-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("CONNECTIONPOINT_FONT", MetaKey.FONT);

    @objid ("814fca50-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("CONNECTIONPOINT_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("814fca52-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("CONNECTIONPOINT_SHOWSTEREOTYPES",
                                                           MetaKey.SHOWSTEREOTYPES);

    @objid ("814fca54-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("CONNECTIONPOINT_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("814fca56-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = createStyleKey("CONNECTIONPOINT_SHOWLABEL", MetaKey.SHOWLABEL);

}
