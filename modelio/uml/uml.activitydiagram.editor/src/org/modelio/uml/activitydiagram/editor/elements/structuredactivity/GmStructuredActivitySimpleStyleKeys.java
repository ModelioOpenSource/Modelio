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
package org.modelio.uml.activitydiagram.editor.elements.structuredactivity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmStructuredactivity when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("2b58eac0-55b6-11e2-877f-002564c97630")
public class GmStructuredActivitySimpleStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d29b2b8a-55c0-11e2-9337-002564c97630")
    static final StyleKey REPMODE = GmStructuredActivityStructuredStyleKeys.REPMODE;

    @objid ("d29b2b8c-55c0-11e2-9337-002564c97630")
    static final StyleKey FILLCOLOR = GmStructuredActivityStructuredStyleKeys.FILLCOLOR;

    @objid ("d29b2b8e-55c0-11e2-9337-002564c97630")
    static final StyleKey FILLMODE = GmStructuredActivityStructuredStyleKeys.FILLMODE;

    @objid ("d29b2b90-55c0-11e2-9337-002564c97630")
    static final StyleKey LINECOLOR = GmStructuredActivityStructuredStyleKeys.LINECOLOR;

    @objid ("d29b2b92-55c0-11e2-9337-002564c97630")
    static final StyleKey LINEWIDTH = GmStructuredActivityStructuredStyleKeys.LINEWIDTH;

    @objid ("d29b2b94-55c0-11e2-9337-002564c97630")
    static final StyleKey FONT = GmStructuredActivityStructuredStyleKeys.FONT;

    @objid ("d29b2b96-55c0-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = GmStructuredActivityStructuredStyleKeys.TEXTCOLOR;

    @objid ("d29b2b98-55c0-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = GmStructuredActivityStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("d29b2b9a-55c0-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = GmStructuredActivityStructuredStyleKeys.SHOWTAGS;

    @objid ("d29b2b9c-55c0-11e2-9337-002564c97630")
    static final StyleKey AUTOSHOWPINS = GmStructuredActivityStructuredStyleKeys.AUTOSHOWPINS;

}
