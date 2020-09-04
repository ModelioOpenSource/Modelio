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

package org.modelio.diagram.editor.activity.elements.timeevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmTimeEvent when its representation mode is RepresentationMode.IMAGE
 */
@objid ("d1cc6904-0f1a-4736-8dff-955b75f66277")
public class GmTimeEventUserImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("6dbc8803-de2a-4e7b-845c-ab859268079e")
     static final StyleKey REPMODE = GmTimeEventStructuredStyleKeys.REPMODE;

    @objid ("368c49f5-2b8d-4fe8-b3ed-e6417707ce89")
     static final StyleKey FONT = GmTimeEventStructuredStyleKeys.FONT;

    @objid ("4b3a8c17-76d8-475a-a18b-c8932aff19d9")
     static final StyleKey TEXTCOLOR = GmTimeEventStructuredStyleKeys.TEXTCOLOR;

    @objid ("8e534549-06fb-46fa-bcc6-b92036a6b967")
     static final StyleKey SHOWSTEREOTYPES = GmTimeEventStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("256442ee-0d87-4608-9e9b-7f381ba774f1")
     static final StyleKey SHOWTAGS = GmTimeEventStructuredStyleKeys.SHOWTAGS;

    @objid ("78eb4845-fe3c-4692-be59-f6948b1cafb3")
     static final StyleKey AUTOSHOWPINS = GmTimeEventStructuredStyleKeys.AUTOSHOWPINS;

}
