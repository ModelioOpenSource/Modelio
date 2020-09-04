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

package org.modelio.diagram.editor.statik.elements.interfaze;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * GmClass style keys for the stereotype image representation mode.
 * 
 * @author cmarin
 */
@objid ("357d3dda-55b7-11e2-877f-002564c97630")
public class InterfaceImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a5a935ca-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = InterfaceStructuredStyleKeys.REPMODE;

    @objid ("a5a935cc-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = InterfaceStructuredStyleKeys.FILLCOLOR;

    @objid ("a5a935ce-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = InterfaceStructuredStyleKeys.FONT;

    @objid ("a5aabc69-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = InterfaceStructuredStyleKeys.TEXTCOLOR;

    @objid ("a5aabc6b-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAME = InterfaceStructuredStyleKeys.SHOWNAME;

    @objid ("a5aabc6d-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = InterfaceStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("a5aabc6f-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = InterfaceStructuredStyleKeys.SHOWTAGS;

    @objid ("a5aabc71-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWVISIBILITY = InterfaceStructuredStyleKeys.SHOWVISIBILITY;

}
