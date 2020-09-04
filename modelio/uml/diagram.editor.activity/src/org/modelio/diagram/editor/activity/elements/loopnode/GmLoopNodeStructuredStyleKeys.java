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

package org.modelio.diagram.editor.activity.elements.loopnode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmLoopnode when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("2ac49a1c-55b6-11e2-877f-002564c97630")
public class GmLoopNodeStructuredStyleKeys extends ActivityAbstractStyleKeyProvider {
    /**
     * Representation mode.
     */
    @objid ("d236764a-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("LOOPNODE_REPMODE", MetaKey.REPMODE);

    /**
     * Fill color.
     */
    @objid ("d236764d-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("LOOPNODE_FILLCOLOR", MetaKey.FILLCOLOR);

    /**
     * Fill mode: transparent, solid, gradient.
     */
    @objid ("d2367650-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("LOOPNODE_FILLMODE", MetaKey.FILLMODE);

    /**
     * Outline color.
     */
    @objid ("d2367653-55c0-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("LOOPNODE_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Outline width.
     */
    @objid ("d2367656-55c0-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("LOOPNODE_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Text font.
     */
    @objid ("d237fceb-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("LOOPNODE_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("d237fcee-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("LOOPNODE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Stereotypes display mode.
     */
    @objid ("d237fcf1-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("LOOPNODE_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    /**
     * Show tagged values.
     */
    @objid ("d237fcf4-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("LOOPNODE_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("d237fcf7-55c0-11e2-9337-002564c97630")
     static final StyleKey AUTOSHOWPINS = createStyleKey("LOOPNODE_AUTOSHOWPINS", MetaKey.AUTOSHOWPINS);

}
