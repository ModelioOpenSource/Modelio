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

package org.modelio.uml.statikdiagram.editor.elements.interaction;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a {@link Interaction} when its representation mode is
 * RepresentationMode.IMAGE
 */
@objid ("cf6962c7-00a1-4b0e-9476-23bc9fc80ef2")
public class GmInteractionUserImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("af12bd14-773f-4e4f-b438-044968f6d2af")
     static final StyleKey REPMODE = GmInteractionStructuredStyleKeys.REPMODE;

    @objid ("ffb393d4-aca1-447e-8587-92f260560413")
     static final StyleKey FONT = GmInteractionStructuredStyleKeys.FONT;

    @objid ("23f9d891-9200-4ad6-825e-21df548b0609")
     static final StyleKey TEXTCOLOR = GmInteractionStructuredStyleKeys.TEXTCOLOR;

    @objid ("3d3061f2-736d-4738-bb32-f81e14d4b216")
     static final StyleKey SHOWSTEREOTYPES = GmInteractionStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("66dcba41-fab6-4a72-8500-c2ab910f0d3b")
     static final StyleKey SHOWTAGS = GmInteractionStructuredStyleKeys.SHOWTAGS;

}
