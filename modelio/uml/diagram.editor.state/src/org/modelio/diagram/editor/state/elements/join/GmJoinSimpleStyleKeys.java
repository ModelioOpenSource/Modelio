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

package org.modelio.diagram.editor.state.elements.join;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.state.elements.ForkJoinOrientation;
import org.modelio.diagram.editor.state.style.StateAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmJoin when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("f557bba6-55b6-11e2-877f-002564c97630")
public class GmJoinSimpleStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("8176da4b-55c2-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("JOIN_REPMODE", MetaKey.REPMODE);

    @objid ("8177015a-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = createStyleKey("JOIN_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("8177015c-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = createStyleKey("JOIN_FILLMODE", MetaKey.FILLMODE);

    @objid ("8177015e-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("JOIN_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("8177286a-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("JOIN_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("8177286c-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("JOIN_FONT", MetaKey.FONT);

    @objid ("81774f79-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("JOIN_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("81774f7b-55c2-11e2-9337-002564c97630")
    public static final StyleKey ORIENTATION = createStyleKey("JOIN_ORIENTATION", ForkJoinOrientation.class);

    @objid ("81774f7d-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("JOIN_SHOWSTEREOTYPES",
                                                                  MetaKey.SHOWSTEREOTYPES);

    @objid ("8177768a-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("JOIN_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("8177768c-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWLABEL = createStyleKey("JOIN_SHOWLABEL", MetaKey.SHOWLABEL);

}
