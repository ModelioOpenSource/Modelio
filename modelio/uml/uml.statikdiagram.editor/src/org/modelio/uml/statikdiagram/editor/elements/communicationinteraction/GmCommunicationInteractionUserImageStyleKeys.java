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
package org.modelio.uml.statikdiagram.editor.elements.communicationinteraction;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationInteraction;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a {@link CommunicationInteraction} when its representation mode is
 * RepresentationMode.IMAGE
 */
@objid ("3e03da97-a73f-40ff-820b-c274fdc60012")
public class GmCommunicationInteractionUserImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("8a85719f-4688-4f6c-884e-3e2ea07185c7")
    static final StyleKey REPMODE = GmCommunicationInteractionStructuredStyleKeys.REPMODE;

    @objid ("d361678f-1fe5-40f8-bbf7-3da1b86b20b3")
    static final StyleKey FONT = GmCommunicationInteractionStructuredStyleKeys.FONT;

    @objid ("9ef5a040-5463-46d4-8b60-220380daf7ed")
    static final StyleKey TEXTCOLOR = GmCommunicationInteractionStructuredStyleKeys.TEXTCOLOR;

    @objid ("5b9ea610-a970-4276-8aa3-d90a685b702a")
    static final StyleKey SHOWSTEREOTYPES = GmCommunicationInteractionStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("094eaaa2-b807-4c48-8ee6-bff09ad984f4")
    static final StyleKey SHOWTAGS = GmCommunicationInteractionStructuredStyleKeys.SHOWTAGS;

}
