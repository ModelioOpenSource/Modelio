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

package org.modelio.bpmn.diagram.editor.elements.bpmnintermediatethrowevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmn.diagram.editor.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnComplexGateway when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("6112e284-55b6-11e2-877f-002564c97630")
public class GmBpmnIntermediateThrowEventStructuredStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("71a2d722-55c1-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("INTERMEDIATTHROWEVENT_REPMODE", MetaKey.REPMODE);

    @objid ("71a2d724-55c1-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("INTERMEDIATTHROWEVENT_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("71a2d726-55c1-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("INTERMEDIATTHROWEVENT_FONT", MetaKey.FONT);

    @objid ("71a2d728-55c1-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("INTERMEDIATTHROWEVENT_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("71a2d72a-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("INTERMEDIATTHROWEVENT_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("71a2d72c-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("INTERMEDIATTHROWEVENT_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Show the element name label.
     * 
     * @see MetaKey#SHOWLABEL
     */
    @objid ("71a2d72e-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = createStyleKey("INTERMEDIATTHROWEVENT_SHOWLABEL", MetaKey.SHOWLABEL);

}
