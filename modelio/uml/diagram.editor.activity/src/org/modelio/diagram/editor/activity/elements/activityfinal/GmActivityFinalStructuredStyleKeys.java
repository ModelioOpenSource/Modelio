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

package org.modelio.diagram.editor.activity.elements.activityfinal;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmActivityfinal when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("29ad5dc2-55b6-11e2-877f-002564c97630")
public class GmActivityFinalStructuredStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d1620f4d-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("ACTIVITYFINAL_REPMODE", MetaKey.REPMODE);

    @objid ("d1620f4f-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("ACTIVITYFINAL_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("d1620f51-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("ACTIVITYFINAL_FILLMODE", MetaKey.FILLMODE);

    @objid ("d1620f53-55c0-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("ACTIVITYFINAL_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d1620f55-55c0-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("ACTIVITYFINAL_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d1620f57-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("ACTIVITYFINAL_FONT", MetaKey.FONT);

    @objid ("d16395ea-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("ACTIVITYFINAL_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d16395ec-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("ACTIVITYFINAL_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("d16395ee-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("ACTIVITYFINAL_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Show the element name label.
     */
    @objid ("d16395f0-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = createStyleKey("ACTIVITYFINAL_SHOWLABEL", MetaKey.SHOWLABEL);

}
