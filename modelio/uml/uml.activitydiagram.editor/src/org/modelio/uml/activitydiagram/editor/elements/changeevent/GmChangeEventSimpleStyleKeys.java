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
package org.modelio.uml.activitydiagram.editor.elements.changeevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmChangeevent when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("29ffc377-55b6-11e2-877f-002564c97630")
public class GmChangeEventSimpleStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d1b167c9-55c0-11e2-9337-002564c97630")
    static final StyleKey REPMODE = GmChangeEventStructuredStyleKeys.REPMODE;

    @objid ("d1b167cb-55c0-11e2-9337-002564c97630")
    static final StyleKey FILLCOLOR = GmChangeEventStructuredStyleKeys.FILLCOLOR;

    @objid ("d1b167cd-55c0-11e2-9337-002564c97630")
    static final StyleKey FILLMODE = GmChangeEventStructuredStyleKeys.FILLMODE;

    @objid ("d1b167cf-55c0-11e2-9337-002564c97630")
    static final StyleKey LINECOLOR = GmChangeEventStructuredStyleKeys.LINECOLOR;

    @objid ("d1b167d1-55c0-11e2-9337-002564c97630")
    static final StyleKey LINEWIDTH = GmChangeEventStructuredStyleKeys.LINEWIDTH;

    @objid ("d1b167d3-55c0-11e2-9337-002564c97630")
    static final StyleKey FONT = GmChangeEventStructuredStyleKeys.FONT;

    @objid ("d1b167d5-55c0-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = GmChangeEventStructuredStyleKeys.TEXTCOLOR;

    @objid ("d1b167d7-55c0-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = GmChangeEventStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("d1b167d9-55c0-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = GmChangeEventStructuredStyleKeys.SHOWTAGS;

    @objid ("d1b167db-55c0-11e2-9337-002564c97630")
    static final StyleKey AUTOSHOWPINS = GmChangeEventStructuredStyleKeys.AUTOSHOWPINS;

}
