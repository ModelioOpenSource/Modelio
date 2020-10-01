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

package org.modelio.uml.activitydiagram.editor.elements.forkjoin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmForkjoin when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("2a8917b0-55b6-11e2-877f-002564c97630")
public class GmForkJoinStructuredStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d08f7d0a-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("FORKJOIN_REPMODE", MetaKey.REPMODE);

    @objid ("d08f7d0c-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("FORKJOIN_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("d08f7d0e-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("FORKJOIN_FILLMODE", MetaKey.FILLMODE);

    @objid ("d08f7d10-55c0-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("FORKJOIN_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d08f7d12-55c0-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("FORKJOIN_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d08f7d14-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("FORKJOIN_FONT", MetaKey.FONT);

    @objid ("d08f7d16-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("FORKJOIN_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d08f7d18-55c0-11e2-9337-002564c97630")
     static final StyleKey ORIENTATION = createStyleKey("FORKJOIN_ORIENTATION", ForkOrientation.class);

    @objid ("d08f7d1a-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("FORKJOIN_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("d08f7d1c-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("FORKJOIN_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("d08f7d1e-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = createStyleKey("FORKJOIN_SHOWLABEL", MetaKey.SHOWLABEL);

}
