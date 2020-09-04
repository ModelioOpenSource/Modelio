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

package org.modelio.diagram.editor.statik.elements.activity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmCallBehavior when its representation mode is
 * RepresentationMode.STRUCTURED
 */
@objid ("33dfba6e-55b7-11e2-877f-002564c97630")
public class GmActivityStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a5c940eb-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("ACTIVITY_REPMODE", MetaKey.REPMODE);

    @objid ("a5c940ed-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("ACTIVITY_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("a5c940ef-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("ACTIVITY_FILLMODE", MetaKey.FILLMODE);

    @objid ("a5c940f1-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("ACTIVITY_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("a5c940f3-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("ACTIVITY_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("a5c940f5-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("ACTIVITY_FONT", MetaKey.FONT);

    @objid ("a5c940f7-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("ACTIVITY_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("a5c940f9-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("ACTIVITY_SHOWSTEREOTYPES",
                                                           MetaKey.SHOWSTEREOTYPES);

    @objid ("a5c940fb-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("ACTIVITY_SHOWTAGS", MetaKey.SHOWTAGS);

}
