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

package org.modelio.diagram.editor.statik.elements.collabuse.v0;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmCollaborationUse when its representation mode is
 * RepresentationMode.STRUCTURED
 */
@objid ("3482ff69-55b7-11e2-877f-002564c97630")
class CollaborationUseStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Representation mode.
     */
    @objid ("a6328a13-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("COLLABORATIONUSE_REPMODE", MetaKey.REPMODE);

    /**
     * Fill color.
     */
    @objid ("a6328a16-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("COLLABORATIONUSE_FILLCOLOR", MetaKey.FILLCOLOR);

    /**
     * Fill mode.
     */
    @objid ("a6328a19-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("COLLABORATIONUSE_FILLMODE", MetaKey.FILLMODE);

    /**
     * Line color.
     */
    @objid ("a6328a1c-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("COLLABORATIONUSE_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width.
     */
    @objid ("a6328a1f-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("COLLABORATIONUSE_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Text font.
     */
    @objid ("a6328a22-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("COLLABORATIONUSE_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("a6328a25-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("COLLABORATIONUSE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Display name.
     */
    @objid ("a6328a28-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAME = createStyleKey("COLLABORATIONUSE_SHOWNAME", MetaKey.SHOWNAME);

    /**
     * Display stereotypes.
     */
    @objid ("a6328a2b-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("COLLABORATIONUSE_SHOWSTEREOTYPES",
                                                           MetaKey.SHOWSTEREOTYPES);

    /**
     * Display tagged values.
     */
    @objid ("a6328a2e-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("COLLABORATIONUSE_SHOWTAGS", MetaKey.SHOWTAGS);

}
