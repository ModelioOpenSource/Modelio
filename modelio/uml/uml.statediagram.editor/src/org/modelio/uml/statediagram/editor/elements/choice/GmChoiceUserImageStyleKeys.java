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

package org.modelio.uml.statediagram.editor.elements.choice;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statediagram.editor.style.StateAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmChoice when its representation mode is RepresentationMode.IMAGE
 */
@objid ("4876a930-dd3c-4502-9fcb-e612704c9387")
public class GmChoiceUserImageStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("53a09e01-1c8b-4110-8b65-ed4b2d3a7737")
     static final StyleKey REPMODE = GmChoiceStructuredStyleKeys.REPMODE;

    @objid ("c1fad0d0-8782-4cef-8e9f-1b6d57457a61")
     static final StyleKey FONT = GmChoiceStructuredStyleKeys.FONT;

    @objid ("d8aec8bb-d805-4d85-b49b-4327a749a80f")
     static final StyleKey TEXTCOLOR = GmChoiceStructuredStyleKeys.TEXTCOLOR;

    @objid ("b5ca5415-a9d5-4060-8068-b7e608b5152d")
     static final StyleKey SHOWSTEREOTYPES = GmChoiceStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("56c559c5-61a6-4b73-b79b-221672e2b249")
     static final StyleKey SHOWTAGS = GmChoiceStructuredStyleKeys.SHOWTAGS;

    @objid ("d922820b-fba6-4e47-8a90-9887d1ebfd8a")
     static final StyleKey SHOWLABEL = GmChoiceStructuredStyleKeys.SHOWLABEL;

}
