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
package org.modelio.uml.statikdiagram.editor.elements.statemachine;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmStateMachine when its representation mode is
 * RepresentationMode.SIMPLE
 */
@objid ("36ace434-55b7-11e2-877f-002564c97630")
public class GmStateMachineSimpleStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a7bafecc-55c2-11e2-9337-002564c97630")
    static final StyleKey REPMODE = GmStateMachineStructuredStyleKeys.REPMODE;

    @objid ("a7bafece-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLCOLOR = GmStateMachineStructuredStyleKeys.FILLCOLOR;

    @objid ("a7bafed0-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLMODE = GmStateMachineStructuredStyleKeys.FILLMODE;

    @objid ("a7bafed2-55c2-11e2-9337-002564c97630")
    static final StyleKey LINECOLOR = GmStateMachineStructuredStyleKeys.LINECOLOR;

    @objid ("a7bafed4-55c2-11e2-9337-002564c97630")
    static final StyleKey LINEWIDTH = GmStateMachineStructuredStyleKeys.LINEWIDTH;

    @objid ("a7bc856a-55c2-11e2-9337-002564c97630")
    static final StyleKey FONT = GmStateMachineStructuredStyleKeys.FONT;

    @objid ("a7bc856c-55c2-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = GmStateMachineStructuredStyleKeys.TEXTCOLOR;

    @objid ("a7bc856e-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = GmStateMachineStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("a7bc8570-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = GmStateMachineStructuredStyleKeys.SHOWTAGS;

}
