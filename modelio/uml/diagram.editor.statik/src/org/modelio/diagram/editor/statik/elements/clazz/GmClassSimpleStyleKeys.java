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

package org.modelio.diagram.editor.statik.elements.clazz;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * GmClass style keys for the simple representation mode.
 * 
 * @author cmarin
 */
@objid ("34446fb9-55b7-11e2-877f-002564c97630")
public class GmClassSimpleStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a517f24b-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmClassStructuredStyleKeys.REPMODE;

    @objid ("a517f24d-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = GmClassStructuredStyleKeys.FILLCOLOR;

    @objid ("a517f24f-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = GmClassStructuredStyleKeys.FILLMODE;

    @objid ("a517f251-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = GmClassStructuredStyleKeys.LINECOLOR;

    @objid ("a517f253-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = GmClassStructuredStyleKeys.LINEWIDTH;

    @objid ("a517f255-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = GmClassStructuredStyleKeys.FONT;

    @objid ("a517f257-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmClassStructuredStyleKeys.TEXTCOLOR;

    @objid ("a517f259-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmClassStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("a517f25b-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmClassStructuredStyleKeys.SHOWTAGS;

    @objid ("a517f25d-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWPORTS = GmClassStructuredStyleKeys.SHOWPORTS;

}
