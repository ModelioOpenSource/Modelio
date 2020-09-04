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

package org.modelio.diagram.editor.statik.elements.collab;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmUseCase when its representation mode is RepresentationMode.IMAGE
 */
@objid ("4d9b1f44-16ac-4a9f-8905-03a3c802fd7e")
public class CollaborationUserImageStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Representation mode.
     */
    @objid ("57232433-88fe-4946-a3c1-247c8ceadfa1")
     static final StyleKey REPMODE = CollaborationStructuredStyleKeys.REPMODE;

    /**
     * Text font.
     */
    @objid ("711a9a5e-ec57-4d5a-8e20-62005b97d78b")
     static final StyleKey FONT = CollaborationStructuredStyleKeys.FONT;

    /**
     * Text color.
     */
    @objid ("71427e41-ee5e-4e43-90a3-3408dd85388c")
     static final StyleKey TEXTCOLOR = CollaborationStructuredStyleKeys.TEXTCOLOR;

    /**
     * Display name.
     */
    @objid ("3f2b1a70-1da6-4b01-8e52-290ffdf7d207")
     static final StyleKey SHOWNAME = CollaborationStructuredStyleKeys.SHOWNAME;

    /**
     * Display stereotypes.
     */
    @objid ("ab7336a0-6927-46c2-a250-49d9dd515ea0")
     static final StyleKey SHOWSTEREOTYPES = CollaborationStructuredStyleKeys.SHOWSTEREOTYPES;

    /**
     * Display tagged values.
     */
    @objid ("515ef498-423f-4401-a6c9-6359ac5a8da3")
     static final StyleKey SHOWTAGS = CollaborationStructuredStyleKeys.SHOWTAGS;

    /**
     * Display visibility.
     */
    @objid ("0e51a45d-e1b9-433d-90e1-e3ed0b0aa702")
    public static final StyleKey SHOWVISIBILITY = CollaborationStructuredStyleKeys.SHOWVISIBILITY;

}
