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

package org.modelio.uml.statediagram.editor.elements.join;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statediagram.editor.elements.ForkJoinOrientation;
import org.modelio.uml.statediagram.editor.style.StateAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmJoin when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("f5594231-55b6-11e2-877f-002564c97630")
public class GmJoinStructuredStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("8177c4ab-55c2-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("JOIN_REPMODE", MetaKey.REPMODE);

    @objid ("8177c4ad-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = createStyleKey("JOIN_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("8177c4af-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = createStyleKey("JOIN_FILLMODE", MetaKey.FILLMODE);

    @objid ("8177ebba-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("JOIN_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("8177ebbc-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("JOIN_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("8177ebbe-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("JOIN_FONT", MetaKey.FONT);

    @objid ("817812ca-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("JOIN_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("817812cc-55c2-11e2-9337-002564c97630")
    public static final StyleKey ORIENTATION = createStyleKey("JOIN_ORIENTATION", ForkJoinOrientation.class);

    @objid ("817839d9-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("JOIN_SHOWSTEREOTYPES",
                                                                  MetaKey.SHOWSTEREOTYPES);

    @objid ("817839db-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("JOIN_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("817839dd-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWLABEL = createStyleKey("JOIN_SHOWLABEL", MetaKey.SHOWLABEL);

}
