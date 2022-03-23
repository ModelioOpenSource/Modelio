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
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a {@link StateMachine} when its representation mode is
 * RepresentationMode.IMAGE
 */
@objid ("e5e5de40-5e16-47ae-8f91-363d05a2b2da")
public class GmStateMachineUserImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("d2f25462-6d3f-44ce-b996-178daedc17f2")
    static final StyleKey REPMODE = GmStateMachineStructuredStyleKeys.REPMODE;

    @objid ("f675f37c-fb52-4158-8f44-a7ae25076349")
    static final StyleKey FONT = GmStateMachineStructuredStyleKeys.FONT;

    @objid ("f5e5fdf4-6aa2-473e-8dd3-815ef2f85af3")
    static final StyleKey TEXTCOLOR = GmStateMachineStructuredStyleKeys.TEXTCOLOR;

    @objid ("98c88deb-8acf-41fb-bb74-f4772c6f9a6c")
    static final StyleKey SHOWSTEREOTYPES = GmStateMachineStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("14620d18-a908-40e2-b1c5-3fc39c467af1")
    static final StyleKey SHOWTAGS = GmStateMachineStructuredStyleKeys.SHOWTAGS;

}
