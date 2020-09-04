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

package org.modelio.diagram.editor.statik.elements.interaction;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;

/**
 * This class provides the StyleKey constants for a {@link Interaction} when its representation mode is
 * RepresentationMode.IMAGE
 */
@objid ("356aee46-55b7-11e2-877f-002564c97630")
public class GmInteractionImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a6b7988d-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmInteractionStructuredStyleKeys.REPMODE;

    @objid ("a6b7988f-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = GmInteractionStructuredStyleKeys.FONT;

    @objid ("a6b79891-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmInteractionStructuredStyleKeys.TEXTCOLOR;

    @objid ("a6b79893-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmInteractionStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("a6b79895-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmInteractionStructuredStyleKeys.SHOWTAGS;

}
