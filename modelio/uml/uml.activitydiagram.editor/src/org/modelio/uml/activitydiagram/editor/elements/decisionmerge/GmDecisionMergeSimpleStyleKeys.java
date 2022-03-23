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
package org.modelio.uml.activitydiagram.editor.elements.decisionmerge;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmDecisionmerge when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("2a42e6ee-55b6-11e2-877f-002564c97630")
public class GmDecisionMergeSimpleStyleKeys extends ActivityAbstractStyleKeyProvider {
    /**
     * Representation model: structured, simple or image.
     */
    @objid ("d0509f4c-55c0-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = GmDecisionMergeStructuredStyleKeys.REPMODE;

    /**
     * Fill color.
     */
    @objid ("d0509f4f-55c0-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = GmDecisionMergeStructuredStyleKeys.FILLCOLOR;

    /**
     * Fill mode: transparent, opaque, gradient.
     */
    @objid ("d0509f52-55c0-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = GmDecisionMergeStructuredStyleKeys.FILLMODE;

    /**
     * Outline color
     */
    @objid ("d0509f55-55c0-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = GmDecisionMergeStructuredStyleKeys.LINECOLOR;

    /**
     * Outline width
     */
    @objid ("d0509f58-55c0-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = GmDecisionMergeStructuredStyleKeys.LINEWIDTH;

    /**
     * font
     */
    @objid ("d0509f5b-55c0-11e2-9337-002564c97630")
    public static final StyleKey FONT = GmDecisionMergeStructuredStyleKeys.FONT;

    /**
     * Text color
     */
    @objid ("d0509f5e-55c0-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = GmDecisionMergeStructuredStyleKeys.TEXTCOLOR;

    /**
     * Stereotype display mode: none, text or icon.
     */
    @objid ("d0509f61-55c0-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = GmDecisionMergeStructuredStyleKeys.SHOWSTEREOTYPES;

    /**
     * Display tagged values.
     */
    @objid ("d0509f64-55c0-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = GmDecisionMergeStructuredStyleKeys.SHOWTAGS;

    /**
     * Show the element name label.
     */
    @objid ("d05225e9-55c0-11e2-9337-002564c97630")
    public static final StyleKey SHOWLABEL = GmDecisionMergeStructuredStyleKeys.SHOWLABEL;

}
