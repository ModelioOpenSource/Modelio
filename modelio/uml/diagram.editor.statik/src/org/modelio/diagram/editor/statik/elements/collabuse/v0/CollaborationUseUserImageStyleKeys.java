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

package org.modelio.diagram.editor.statik.elements.collabuse.v0;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmUseCase when its representation mode is RepresentationMode.IMAGE
 */
@objid ("313656c9-43df-434e-bab7-57110ea3a7a2")
class CollaborationUseUserImageStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Representation mode.
     */
    @objid ("70360a9c-a256-4473-b075-e8e3dfdb567f")
     static final StyleKey REPMODE = CollaborationUseStructuredStyleKeys.REPMODE;

    /**
     * Text font.
     */
    @objid ("02eba945-6dd7-46ea-bc2e-e80a9840bd73")
     static final StyleKey FONT = CollaborationUseStructuredStyleKeys.FONT;

    /**
     * Text color.
     */
    @objid ("56f5340b-1ed0-44b2-afba-48a741b13571")
     static final StyleKey TEXTCOLOR = CollaborationUseStructuredStyleKeys.TEXTCOLOR;

    /**
     * Display name.
     */
    @objid ("4685458e-84b9-4cc1-a85a-ffce6e27ee36")
     static final StyleKey SHOWNAME = CollaborationUseStructuredStyleKeys.SHOWNAME;

    /**
     * Display stereotypes.
     */
    @objid ("2cd38f87-71e3-4cd2-9fb5-72a9ed704db1")
     static final StyleKey SHOWSTEREOTYPES = CollaborationUseStructuredStyleKeys.SHOWSTEREOTYPES;

    /**
     * Display tagged values.
     */
    @objid ("d1219cfc-d06a-4ace-9705-d0a02fd1571a")
     static final StyleKey SHOWTAGS = CollaborationUseStructuredStyleKeys.SHOWTAGS;

}
