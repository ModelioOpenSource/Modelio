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

package org.modelio.uml.activitydiagram.editor.elements.action;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmAction when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("298a455b-55b6-11e2-877f-002564c97630")
public class GmActionSimpleStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d12fb4aa-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmActionStructuredStyleKeys.REPMODE;

    @objid ("d12fb4ac-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = GmActionStructuredStyleKeys.FILLCOLOR;

    @objid ("d12fb4ae-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = GmActionStructuredStyleKeys.FILLMODE;

    @objid ("d12fb4b0-55c0-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = GmActionStructuredStyleKeys.LINECOLOR;

    @objid ("d12fb4b2-55c0-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = GmActionStructuredStyleKeys.LINEWIDTH;

    @objid ("d12fb4b4-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = GmActionStructuredStyleKeys.FONT;

    @objid ("d12fb4b6-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmActionStructuredStyleKeys.TEXTCOLOR;

    @objid ("d12fb4b8-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmActionStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("d12fb4ba-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmActionStructuredStyleKeys.SHOWTAGS;

    @objid ("d12fb4bc-55c0-11e2-9337-002564c97630")
     static final StyleKey AUTOSHOWPINS = GmActionStructuredStyleKeys.AUTOSHOWPINS;

}
