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

package org.modelio.diagram.editor.bpmn.elements.bpmnintermediatecatchevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnComplexGateway when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("6108340f-55b6-11e2-877f-002564c97630")
public class GmBpmnIntermediateCatchEventStructuredStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("7199af4b-55c1-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("INTERMEDIATECATCHEVENT_REPMODE", MetaKey.REPMODE);

    @objid ("7199af4d-55c1-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("INTERMEDIATECATCHEVENT_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("7199af4f-55c1-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("INTERMEDIATECATCHEVENT_FONT", MetaKey.FONT);

    @objid ("7199af51-55c1-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("INTERMEDIATECATCHEVENT_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("7199af53-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("INTERMEDIATECATCHEVENT_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("7199af55-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("INTERMEDIATECATCHEVENT_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Show the element name label.
     * 
     * @see MetaKey#SHOWLABEL
     */
    @objid ("7199af57-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = createStyleKey("INTERMEDIATECATCHEVENT_SHOWLABEL", MetaKey.SHOWLABEL);

}
