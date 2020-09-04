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

package org.modelio.diagram.editor.activity.elements.changeevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmChangeevent when its representation mode is RepresentationMode.IMAGE
 */
@objid ("a3652e16-63f6-4d64-a599-2c818a092380")
public class GmChangeEventUserImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("5fef1852-ed3d-4aa9-8bd0-4dda87d11f1b")
     static final StyleKey REPMODE = GmChangeEventStructuredStyleKeys.REPMODE;

    @objid ("df454af2-7891-461a-8176-58e4a25ec835")
     static final StyleKey FONT = GmChangeEventStructuredStyleKeys.FONT;

    @objid ("354194b6-665b-4ea2-af65-b7c042651ce1")
     static final StyleKey TEXTCOLOR = GmChangeEventStructuredStyleKeys.TEXTCOLOR;

    @objid ("abe4185e-a549-457b-a3a6-db74a5a1bd6a")
     static final StyleKey SHOWSTEREOTYPES = GmChangeEventStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("79607bb8-f3a5-46ac-9778-29d68fd05e8c")
     static final StyleKey SHOWTAGS = GmChangeEventStructuredStyleKeys.SHOWTAGS;

    @objid ("daf2224f-c5d6-472a-9606-f221a71b108b")
     static final StyleKey AUTOSHOWPINS = GmChangeEventStructuredStyleKeys.AUTOSHOWPINS;

}
