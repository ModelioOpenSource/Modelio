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

package org.modelio.diagram.editor.statik.elements.component;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * GmClass style keys for the simple representation mode.
 * 
 * @author cmarin
 */
@objid ("349ffd25-55b7-11e2-877f-002564c97630")
public class ComponentSimpleStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a5bb854b-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = ComponentStructuredStyleKeys.REPMODE;

    @objid ("a5bb854d-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = ComponentStructuredStyleKeys.FILLCOLOR;

    @objid ("a5bb854f-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = ComponentStructuredStyleKeys.FILLMODE;

    @objid ("a5bb8551-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = ComponentStructuredStyleKeys.LINECOLOR;

    @objid ("a5bb8553-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = ComponentStructuredStyleKeys.LINEWIDTH;

    @objid ("a5bb8555-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = ComponentStructuredStyleKeys.FONT;

    @objid ("a5bb8557-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = ComponentStructuredStyleKeys.TEXTCOLOR;

    @objid ("a5bb8559-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAME = ComponentStructuredStyleKeys.SHOWNAME;

    @objid ("a5bb855b-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = ComponentStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("a5bb855d-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = ComponentStructuredStyleKeys.SHOWTAGS;

    @objid ("a5bb855f-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWPORTS = ComponentStructuredStyleKeys.SHOWPORTS;

}
