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

package org.modelio.diagram.editor.statik.elements.collab.v0;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmUseCase when its representation mode is RepresentationMode.IMAGE
 */
@objid ("202b81c2-f1b6-46fa-a490-bc991666be54")
class CollaborationUserImageStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Representation mode.
     */
    @objid ("fa8649b7-af9c-4bed-8f0c-46390c880625")
     static final StyleKey REPMODE = CollaborationStructuredStyleKeys.REPMODE;

    /**
     * Text font.
     */
    @objid ("f0b73860-5282-4537-8a4e-da303339fc2e")
     static final StyleKey FONT = CollaborationStructuredStyleKeys.FONT;

    /**
     * Text color.
     */
    @objid ("acf21b34-e414-4114-822f-94f75417e912")
     static final StyleKey TEXTCOLOR = CollaborationStructuredStyleKeys.TEXTCOLOR;

    /**
     * Display name.
     */
    @objid ("eae8eed0-c32a-4b29-9c6e-e5794f278c47")
     static final StyleKey SHOWNAME = CollaborationStructuredStyleKeys.SHOWNAME;

    /**
     * Display stereotypes.
     */
    @objid ("07e20077-de5a-4cb9-bbac-51df86456954")
     static final StyleKey SHOWSTEREOTYPES = CollaborationStructuredStyleKeys.SHOWSTEREOTYPES;

    /**
     * Display tagged values.
     */
    @objid ("704d972b-ac52-4557-8fc0-65acdbac2841")
     static final StyleKey SHOWTAGS = CollaborationStructuredStyleKeys.SHOWTAGS;

    /**
     * Display visibility.
     */
    @objid ("53208e92-5765-4c85-bfac-c1130b48f510")
    public static final StyleKey SHOWVISIBILITY = CollaborationStructuredStyleKeys.SHOWVISIBILITY;

}
