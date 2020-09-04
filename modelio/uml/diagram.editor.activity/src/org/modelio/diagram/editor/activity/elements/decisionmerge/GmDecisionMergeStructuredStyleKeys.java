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

package org.modelio.diagram.editor.activity.elements.decisionmerge;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmDecisionmerge when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("2a446d81-55b6-11e2-877f-002564c97630")
public class GmDecisionMergeStructuredStyleKeys extends ActivityAbstractStyleKeyProvider {
    /**
     * Representation mode: structured, simple, stereotype image
     */
    @objid ("d1d606da-55c0-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("DECISIONMERGE_REPMODE", MetaKey.REPMODE);

    /**
     * Fill color
     */
    @objid ("d1d606dd-55c0-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = createStyleKey("DECISIONMERGE_FILLCOLOR", MetaKey.FILLCOLOR);

    /**
     * Fill mode: transparent, opaque, gradient
     */
    @objid ("d1d606e0-55c0-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = createStyleKey("DECISIONMERGE_FILLMODE", MetaKey.FILLMODE);

    /**
     * Outline color
     */
    @objid ("d1d606e3-55c0-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("DECISIONMERGE_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Outline width
     */
    @objid ("d1d606e6-55c0-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("DECISIONMERGE_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Text font
     */
    @objid ("d1d606e9-55c0-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("DECISIONMERGE_FONT", MetaKey.FONT);

    /**
     * Text color
     */
    @objid ("d1d606ec-55c0-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("DECISIONMERGE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Stereotype display mode:
     * <li>none
     * <li>text in the label
     * <li>icon on the label
     */
    @objid ("d1d606ef-55c0-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("DECISIONMERGE_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    /**
     * show tagged values in the label.
     */
    @objid ("d1d606f2-55c0-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("DECISIONMERGE_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Show the element name label.
     */
    @objid ("d1d606f5-55c0-11e2-9337-002564c97630")
    public static final StyleKey SHOWLABEL = createStyleKey("DECISIONMERGE_SHOWLABEL", MetaKey.SHOWLABEL);

}
