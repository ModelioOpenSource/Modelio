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

package org.modelio.diagram.editor.statik.elements.statemachine;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmCallBehavior when its representation mode is
 * RepresentationMode.STRUCTURED
 */
@objid ("36ace45c-55b7-11e2-877f-002564c97630")
public class GmStateMachineStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a55f829b-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("STATEMACHINE_REPMODE", MetaKey.REPMODE);

    @objid ("a55fa9aa-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("STATEMACHINE_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("a55fa9ac-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("STATEMACHINE_FILLMODE", MetaKey.FILLMODE);

    @objid ("a55fa9ae-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("STATEMACHINE_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("a55fd0ba-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("STATEMACHINE_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("a55fd0bc-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("STATEMACHINE_FONT", MetaKey.FONT);

    @objid ("a55ff7c9-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("STATEMACHINE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("a55ff7cb-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("STATEMACHINE_SHOWSTEREOTYPES",
                                                           MetaKey.SHOWSTEREOTYPES);

    @objid ("a55ff7cd-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("STATEMACHINE_SHOWTAGS", MetaKey.SHOWTAGS);

}
