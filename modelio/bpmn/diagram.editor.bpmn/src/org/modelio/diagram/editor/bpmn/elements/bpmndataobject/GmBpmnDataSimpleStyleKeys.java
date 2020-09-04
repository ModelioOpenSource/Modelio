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

package org.modelio.diagram.editor.bpmn.elements.bpmndataobject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmComplexGatewayEvent when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("60d8e6c0-55b6-11e2-877f-002564c97630")
public class GmBpmnDataSimpleStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("7168db64-55c1-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmBpmnDataObjectStyleKeys.REPMODE;

    @objid ("7168db66-55c1-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("DATA_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("7168db68-55c1-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = createStyleKey("DATA_FILLMODE", MetaKey.FILLMODE);

    @objid ("7168db6a-55c1-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("DATA_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("7168db6c-55c1-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("DATA_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("7168db6e-55c1-11e2-9337-002564c97630")
     static final StyleKey FONT = GmBpmnDataObjectStyleKeys.FONT;

    @objid ("7168db70-55c1-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmBpmnDataObjectStyleKeys.TEXTCOLOR;

    @objid ("7168db72-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnDataObjectStyleKeys.SHOWSTEREOTYPES;

    @objid ("7168db74-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmBpmnDataObjectStyleKeys.SHOWTAGS;

}
