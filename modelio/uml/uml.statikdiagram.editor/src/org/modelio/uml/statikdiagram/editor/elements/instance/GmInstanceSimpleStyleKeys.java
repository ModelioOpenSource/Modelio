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
package org.modelio.uml.statikdiagram.editor.elements.instance;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * GmClass style keys for the simple representation mode.
 * 
 * @author cmarin
 */
@objid ("353d27a3-55b7-11e2-877f-002564c97630")
public class GmInstanceSimpleStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a68fec49-55c2-11e2-9337-002564c97630")
    static final StyleKey REPMODE = GmInstanceStructuredStyleKeys.REPMODE;

    @objid ("a68fec4b-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLCOLOR = GmInstanceStructuredStyleKeys.FILLCOLOR;

    @objid ("a68fec4d-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLMODE = GmInstanceStructuredStyleKeys.FILLMODE;

    @objid ("a68fec4f-55c2-11e2-9337-002564c97630")
    static final StyleKey LINECOLOR = GmInstanceStructuredStyleKeys.LINECOLOR;

    @objid ("a68fec51-55c2-11e2-9337-002564c97630")
    static final StyleKey LINEWIDTH = GmInstanceStructuredStyleKeys.LINEWIDTH;

    @objid ("a68fec53-55c2-11e2-9337-002564c97630")
    static final StyleKey FONT = GmInstanceStructuredStyleKeys.FONT;

    @objid ("a68fec55-55c2-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = GmInstanceStructuredStyleKeys.TEXTCOLOR;

    @objid ("a68fec57-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWNAME = GmInstanceStructuredStyleKeys.SHOWNAME;

    @objid ("a68fec59-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = GmInstanceStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("a68fec5b-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = GmInstanceStructuredStyleKeys.SHOWTAGS;

}
