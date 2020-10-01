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
@objid ("15ca54c2-3aab-4557-829d-40d105e744c4")
public class GmDecisionMergeUserImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    /**
     * Representation model: structured, simple or image.
     */
    @objid ("abc4f415-d624-4ff5-b05d-808333b20f17")
    public static final StyleKey REPMODE = GmDecisionMergeStructuredStyleKeys.REPMODE;

    /**
     * font
     */
    @objid ("b42bfe2f-3964-4f35-91e4-556e2081dc34")
    public static final StyleKey FONT = GmDecisionMergeStructuredStyleKeys.FONT;

    /**
     * Text color
     */
    @objid ("9c6eed9e-b573-4967-9389-6938da35b166")
    public static final StyleKey TEXTCOLOR = GmDecisionMergeStructuredStyleKeys.TEXTCOLOR;

    /**
     * Stereotype display mode: none, text or icon.
     */
    @objid ("1376f568-7035-431b-b402-193363d5c381")
    public static final StyleKey SHOWSTEREOTYPES = GmDecisionMergeStructuredStyleKeys.SHOWSTEREOTYPES;

    /**
     * Display tagged values.
     */
    @objid ("f4565f9c-628e-4dcb-b5a6-8565d5d0d202")
    public static final StyleKey SHOWTAGS = GmDecisionMergeStructuredStyleKeys.SHOWTAGS;

    /**
     * Show the element name label.
     */
    @objid ("1fbd560e-2f9d-4d64-9b69-a603bc8d494e")
    public static final StyleKey SHOWLABEL = GmDecisionMergeStructuredStyleKeys.SHOWLABEL;

}
