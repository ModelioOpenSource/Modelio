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
package org.modelio.uml.statediagram.editor.elements.exit;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statediagram.editor.style.StateAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmExit when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("f51f4685-55b6-11e2-877f-002564c97630")
public class GmExitSimpleStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("815d85f0-55c2-11e2-9337-002564c97630")
    static final StyleKey REPMODE = GmExitStructuredStyleKeys.REPMODE;

    @objid ("815d85f2-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLCOLOR = GmExitStructuredStyleKeys.FILLCOLOR;

    @objid ("815d85f4-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLMODE = GmExitStructuredStyleKeys.FILLMODE;

    @objid ("815d85f6-55c2-11e2-9337-002564c97630")
    static final StyleKey LINECOLOR = GmExitStructuredStyleKeys.LINECOLOR;

    @objid ("815f0c8a-55c2-11e2-9337-002564c97630")
    static final StyleKey LINEWIDTH = GmExitStructuredStyleKeys.LINEWIDTH;

    @objid ("815f0c8c-55c2-11e2-9337-002564c97630")
    static final StyleKey FONT = GmExitStructuredStyleKeys.FONT;

    @objid ("815f0c8e-55c2-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = GmExitStructuredStyleKeys.TEXTCOLOR;

    @objid ("815f0c90-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = GmExitStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("815f0c92-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = GmExitStructuredStyleKeys.SHOWTAGS;

    @objid ("815f0c94-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWLABEL = createStyleKey("EXIT_SHOWLABEL", MetaKey.SHOWLABEL);

}
