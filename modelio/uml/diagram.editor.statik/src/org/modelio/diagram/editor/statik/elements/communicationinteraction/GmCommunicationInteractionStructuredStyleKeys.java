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

package org.modelio.diagram.editor.statik.elements.communicationinteraction;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmCommunicationInteraction when its representation mode is
 * RepresentationMode.STRUCTURED
 */
@objid ("349b6976-55b7-11e2-877f-002564c97630")
public class GmCommunicationInteractionStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a6435300-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("INTERACTION_REPMODE", MetaKey.REPMODE);

    @objid ("a6435302-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("INTERACTION_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("a6435304-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("INTERACTION_FILLMODE", MetaKey.FILLMODE);

    @objid ("a6435306-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("INTERACTION_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("a6435308-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("INTERACTION_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("a644d989-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("INTERACTION_FONT", MetaKey.FONT);

    @objid ("a644d98b-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("INTERACTION_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("a644d98d-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("INTERACTION_SHOWSTEREOTYPES",
                                                           MetaKey.SHOWSTEREOTYPES);

    @objid ("a644d98f-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("INTERACTION_SHOWTAGS", MetaKey.SHOWTAGS);

}
