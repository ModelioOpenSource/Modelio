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

package org.modelio.diagram.editor.activity.elements.objectnode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmObjectNode when its representation mode is RepresentationMode.IMAGE
 */
@objid ("5e5ac68f-fb21-48ab-b047-4d59eb287cc8")
public class GmObjectNodeUserImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("8a1d77e9-6f73-4e7b-9649-c7550a6f111a")
     static final StyleKey REPMODE = GmObjectNodeStructuredStyleKeys.REPMODE;

    @objid ("f0d6e01b-6cb7-4079-9a22-63482ae8db2f")
     static final StyleKey FONT = GmObjectNodeStructuredStyleKeys.FONT;

    @objid ("6064d1b4-4506-4b87-ab4f-c37fb557915b")
     static final StyleKey TEXTCOLOR = GmObjectNodeStructuredStyleKeys.TEXTCOLOR;

    @objid ("cb7f9cb3-4a6b-443e-aae5-13373a19cdf9")
     static final StyleKey SHOWSTEREOTYPES = GmObjectNodeStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("37955888-aecc-4319-bdb4-d23a3071301a")
     static final StyleKey SHOWTAGS = GmObjectNodeStructuredStyleKeys.SHOWTAGS;

}
