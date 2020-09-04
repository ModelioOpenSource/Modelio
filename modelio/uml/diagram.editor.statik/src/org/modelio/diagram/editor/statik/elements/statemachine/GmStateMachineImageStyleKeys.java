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

package org.modelio.diagram.editor.statik.elements.statemachine;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;

/**
 * This class provides the StyleKey constants for a {@link StateMachine} when its representation mode is
 * RepresentationMode.IMAGE
 */
@objid ("36a8507d-55b7-11e2-877f-002564c97630")
public class GmStateMachineImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a7b66aed-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmStateMachineStructuredStyleKeys.REPMODE;

    @objid ("a7b66aef-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = GmStateMachineStructuredStyleKeys.FONT;

    @objid ("a7b66af1-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmStateMachineStructuredStyleKeys.TEXTCOLOR;

    @objid ("a7b66af3-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmStateMachineStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("a7b7f189-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmStateMachineStructuredStyleKeys.SHOWTAGS;

}
