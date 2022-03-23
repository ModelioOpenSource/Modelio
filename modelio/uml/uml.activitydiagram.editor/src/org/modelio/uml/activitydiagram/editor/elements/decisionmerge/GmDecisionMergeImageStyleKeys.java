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
 * This class provides the StyleKey constants for a GmDecisionmerge when its representation mode is RepresentationMode.IMAGE
 */
@objid ("2a3fd9a2-55b6-11e2-877f-002564c97630")
public class GmDecisionMergeImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    /**
     * Representation model: structured, simple or image.
     */
    @objid ("d1d4802f-55c0-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = GmDecisionMergeStructuredStyleKeys.REPMODE;

    /**
     * font
     */
    @objid ("d1d606cb-55c0-11e2-9337-002564c97630")
    public static final StyleKey FONT = GmDecisionMergeStructuredStyleKeys.FONT;

    /**
     * Text color
     */
    @objid ("d1d606ce-55c0-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = GmDecisionMergeStructuredStyleKeys.TEXTCOLOR;

    /**
     * Stereotype display mode: none, text or icon.
     */
    @objid ("d1d606d1-55c0-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = GmDecisionMergeStructuredStyleKeys.SHOWSTEREOTYPES;

    /**
     * Display tagged values.
     */
    @objid ("d1d606d4-55c0-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = GmDecisionMergeStructuredStyleKeys.SHOWTAGS;

    /**
     * Show the element name label.
     */
    @objid ("d1d606d7-55c0-11e2-9337-002564c97630")
    public static final StyleKey SHOWLABEL = GmDecisionMergeStructuredStyleKeys.SHOWLABEL;

}
