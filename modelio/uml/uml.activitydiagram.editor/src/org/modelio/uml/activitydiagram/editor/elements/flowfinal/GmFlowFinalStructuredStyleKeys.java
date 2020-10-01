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

package org.modelio.uml.activitydiagram.editor.elements.flowfinal;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmFlowfinal when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("2a7ce2a7-55b6-11e2-877f-002564c97630")
public class GmFlowFinalStructuredStyleKeys extends ActivityAbstractStyleKeyProvider {
    /**
     * Representation mode: structured, simplified, image
     */
    @objid ("d2029524-55c0-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("FLOWFINAL_REPMODE", MetaKey.REPMODE);

    /**
     * Fill color.
     */
    @objid ("d2029527-55c0-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = createStyleKey("FLOWFINAL_FILLCOLOR", MetaKey.FILLCOLOR);

    /**
     * Fill mode: transparent, opaque, gradient
     */
    @objid ("d202952a-55c0-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = createStyleKey("FLOWFINAL_FILLMODE", MetaKey.FILLMODE);

    /**
     * Outline color.
     */
    @objid ("d202952d-55c0-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("FLOWFINAL_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width.
     */
    @objid ("d2029530-55c0-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("FLOWFINAL_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Font.
     */
    @objid ("d2029533-55c0-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("FLOWFINAL_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("d2029536-55c0-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("FLOWFINAL_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Show stereotypes.
     */
    @objid ("d2041bab-55c0-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("FLOWFINAL_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    /**
     * Show tags.
     */
    @objid ("d2041bae-55c0-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("FLOWFINAL_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Show the element name label.
     */
    @objid ("d2041bb1-55c0-11e2-9337-002564c97630")
    public static final StyleKey SHOWLABEL = createStyleKey("FLOWFINAL_SHOWLABEL", MetaKey.SHOWLABEL);

}
