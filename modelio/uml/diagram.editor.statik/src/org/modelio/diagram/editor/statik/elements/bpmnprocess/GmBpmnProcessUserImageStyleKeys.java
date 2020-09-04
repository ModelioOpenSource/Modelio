/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.statik.elements.bpmnprocess;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;

/**
 * This class provides the StyleKey constants for a {@link BpmnProcess} when its representation mode is
 * RepresentationMode.IMAGE
 */
@objid ("755598f6-2122-4239-a316-ea72161ff11c")
public class GmBpmnProcessUserImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("79d6301c-9f26-40a8-bb61-942c956e7913")
     static final StyleKey REPMODE = GmBpmnProcessStructuredStyleKeys.REPMODE;

    @objid ("1f28bf35-ba7c-4128-9171-011f2cbc65cb")
     static final StyleKey FONT = GmBpmnProcessStructuredStyleKeys.FONT;

    @objid ("0a448b24-8fb0-4558-92e8-18ff48800b24")
     static final StyleKey TEXTCOLOR = GmBpmnProcessStructuredStyleKeys.TEXTCOLOR;

    @objid ("3229d3cc-7440-48c2-aebe-89d806e22ca5")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnProcessStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("f340851b-e228-4796-abdf-bb76bf0d94ce")
     static final StyleKey SHOWTAGS = GmBpmnProcessStructuredStyleKeys.SHOWTAGS;

}
