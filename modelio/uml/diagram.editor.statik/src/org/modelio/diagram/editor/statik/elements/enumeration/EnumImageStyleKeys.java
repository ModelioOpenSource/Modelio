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

package org.modelio.diagram.editor.statik.elements.enumeration;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * GmClass style keys for the stereotype image representation mode.
 * 
 * @author cmarin
 */
@objid ("34d0d11a-55b7-11e2-877f-002564c97630")
public class EnumImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a65d91aa-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = EnumStructuredStyleKeys.REPMODE;

    @objid ("a65d91ac-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = EnumStructuredStyleKeys.FONT;

    @objid ("a65d91ae-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = EnumStructuredStyleKeys.TEXTCOLOR;

    @objid ("a65f184a-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAME = EnumStructuredStyleKeys.SHOWNAME;

    @objid ("a65f184c-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = EnumStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("a65f184e-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = EnumStructuredStyleKeys.SHOWTAGS;

    @objid ("a65f1850-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWVISIBILITY = EnumStructuredStyleKeys.SHOWVISIBILITY;

}
