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

package org.modelio.uml.activitydiagram.editor.elements.calloperation;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmCalloperation when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("29de31ba-55b6-11e2-877f-002564c97630")
public class GmCallOperationStructuredStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d19a8476-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("CALLOPERATION_REPMODE", MetaKey.REPMODE);

    @objid ("d19a8478-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("CALLOPERATION_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("d19a847a-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("CALLOPERATION_FILLMODE", MetaKey.FILLMODE);

    @objid ("d19a847c-55c0-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("CALLOPERATION_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d19a847e-55c0-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("CALLOPERATION_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d19a8480-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("CALLOPERATION_FONT", MetaKey.FONT);

    @objid ("d19a8482-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("CALLOPERATION_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d19a8484-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("CALLOPERATION_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("d19a8486-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("CALLOPERATION_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("d19a8488-55c0-11e2-9337-002564c97630")
     static final StyleKey AUTOSHOWPINS = createStyleKey("CALLOPERATION_AUTOSHOWPINS", MetaKey.AUTOSHOWPINS);

}
