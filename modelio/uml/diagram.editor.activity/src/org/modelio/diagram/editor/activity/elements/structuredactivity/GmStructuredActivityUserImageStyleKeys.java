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

package org.modelio.diagram.editor.activity.elements.structuredactivity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmStructuredactivity when its representation mode is RepresentationMode.IMAGE
 */
@objid ("75eadf92-c4d6-45b3-bc35-604665ecd46c")
public class GmStructuredActivityUserImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("e7346e6e-03b1-4599-977c-7bb360b231de")
     static final StyleKey REPMODE = GmStructuredActivityStructuredStyleKeys.REPMODE;

    @objid ("1ac8ec66-a921-40af-84aa-34aab401dea1")
     static final StyleKey FONT = GmStructuredActivityStructuredStyleKeys.FONT;

    @objid ("3f7d5ac5-e1b4-43a8-96f5-087811b5b339")
     static final StyleKey TEXTCOLOR = GmStructuredActivityStructuredStyleKeys.TEXTCOLOR;

    @objid ("cf290765-0e08-4450-af43-78d265b1f307")
     static final StyleKey SHOWSTEREOTYPES = GmStructuredActivityStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("bb50b08b-4e22-4bb0-ad1c-50fb0648e98d")
     static final StyleKey SHOWTAGS = GmStructuredActivityStructuredStyleKeys.SHOWTAGS;

    @objid ("b64f64b0-514b-42bf-8ee7-7a537066f160")
     static final StyleKey AUTOSHOWPINS = GmStructuredActivityStructuredStyleKeys.AUTOSHOWPINS;

}
