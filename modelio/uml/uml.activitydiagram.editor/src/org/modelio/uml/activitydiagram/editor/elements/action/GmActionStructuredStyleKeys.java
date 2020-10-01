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
import org.eclipse.swt.graphics.Font;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a {@link GmAction} when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("298bcbe7-55b6-11e2-877f-002564c97630")
public class GmActionStructuredStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d14b2bea-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("ACTION_REPMODE", MetaKey.REPMODE);

    @objid ("d14b2bec-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("ACTION_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("d14b2bee-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("ACTION_FILLMODE", MetaKey.FILLMODE);

    @objid ("d14b2bf0-55c0-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("ACTION_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d14cb289-55c0-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("ACTION_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d14cb28b-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("ACTION_FONT", MetaKey.FONT);

    @objid ("d14cb28d-55c0-11e2-9337-002564c97630")
     static final StyleKey BODYFONT = createStyleKey("ACTION_BODYFONT", Font.class);

    @objid ("d14cb28f-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("ACTION_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d14cb291-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("ACTION_SHOWSTEREOTYPES", MetaKey.SHOWSTEREOTYPES);

    @objid ("d14cb293-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("ACTION_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("d14cb295-55c0-11e2-9337-002564c97630")
     static final StyleKey AUTOSHOWPINS = createStyleKey("ACTION_AUTOSHOWPINS", MetaKey.AUTOSHOWPINS);

}
