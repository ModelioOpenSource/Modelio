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
@objid ("f4f30663-55b6-11e2-877f-002564c97630")
public class GmChoiceImageStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("8146a28e-55c2-11e2-9337-002564c97630")
    static final StyleKey REPMODE = GmChoiceStructuredStyleKeys.REPMODE;

    @objid ("8146a290-55c2-11e2-9337-002564c97630")
    static final StyleKey FONT = GmChoiceStructuredStyleKeys.FONT;

    @objid ("8146a292-55c2-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = GmChoiceStructuredStyleKeys.TEXTCOLOR;

    @objid ("8146a294-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = GmChoiceStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("8146a296-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = GmChoiceStructuredStyleKeys.SHOWTAGS;

    @objid ("8146a298-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWLABEL = GmChoiceStructuredStyleKeys.SHOWLABEL;

}
