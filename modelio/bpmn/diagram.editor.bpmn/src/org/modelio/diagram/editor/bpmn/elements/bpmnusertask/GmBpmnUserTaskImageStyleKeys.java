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

package org.modelio.diagram.editor.bpmn.elements.bpmnusertask;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnUserTask when its representation mode is RepresentationMode.IMAGE
 */
@objid ("61e574dc-55b6-11e2-877f-002564c97630")
public class GmBpmnUserTaskImageStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("72600c8d-55c1-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("CALLOPERATION_REPMODE", MetaKey.REPMODE);

    @objid ("72600c8f-55c1-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("CALLOPERATION_FONT", MetaKey.FONT);

    @objid ("72600c91-55c1-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("CALLOPERATION_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("72600c93-55c1-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("CALLOPERATION_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("72600c95-55c1-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("CALLOPERATION_SHOWTAGS", MetaKey.SHOWTAGS);

}
