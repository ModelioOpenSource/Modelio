/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.editor.bpmn.elements.bpmnendevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnComplexGateway when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("60e51bd6-55b6-11e2-877f-002564c97630")
public class GmBpmnEndEventStructuredStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("71781da2-55c1-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("ENDEVENT_REPMODE", MetaKey.REPMODE);

    @objid ("71781da4-55c1-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("ENDEVENT_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("7179a42a-55c1-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("ENDEVENT_FONT", MetaKey.FONT);

    @objid ("7179a42c-55c1-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("ENDEVENT_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("7179a42e-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("ENDEVENT_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("7179a430-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("ENDEVENT_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Show the element name label.
     * 
     * @see MetaKey#SHOWLABEL
     */
    @objid ("7179a432-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = createStyleKey("ENDEVENT_SHOWLABEL", MetaKey.SHOWLABEL);

}
