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

package org.modelio.diagram.editor.statik.elements.instance;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * GmClass style keys for the stereotype image representation mode.
 * 
 * @author cmarin
 */
@objid ("3535867a-55b7-11e2-877f-002564c97630")
public class GmInstanceImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a6884b2a-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmInstanceStructuredStyleKeys.REPMODE;

    @objid ("a6884b2c-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = GmInstanceStructuredStyleKeys.FONT;

    @objid ("a6884b2e-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmInstanceStructuredStyleKeys.TEXTCOLOR;

    @objid ("a6884b30-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAME = GmInstanceStructuredStyleKeys.SHOWNAME;

    @objid ("a6884b32-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmInstanceStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("a6884b34-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmInstanceStructuredStyleKeys.SHOWTAGS;

}
