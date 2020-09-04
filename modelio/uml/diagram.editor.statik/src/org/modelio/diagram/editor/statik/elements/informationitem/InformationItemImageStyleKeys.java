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

package org.modelio.diagram.editor.statik.elements.informationitem;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * GmClass style keys for the stereotype image representation mode.
 * 
 * @author cmarin
 */
@objid ("351b95cc-55b7-11e2-877f-002564c97630")
public class InformationItemImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a67d9ccd-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = InformationItemStructuredStyleKeys.REPMODE;

    @objid ("a67d9ccf-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = InformationItemStructuredStyleKeys.FONT;

    @objid ("a67f2369-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = InformationItemStructuredStyleKeys.TEXTCOLOR;

    @objid ("a67f236b-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAME = InformationItemStructuredStyleKeys.SHOWNAME;

    @objid ("a67f236d-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = InformationItemStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("a67f236f-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = InformationItemStructuredStyleKeys.SHOWTAGS;

}
