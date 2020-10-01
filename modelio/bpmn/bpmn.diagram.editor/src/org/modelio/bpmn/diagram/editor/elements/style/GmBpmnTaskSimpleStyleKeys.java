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

package org.modelio.bpmn.diagram.editor.elements.style;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmGmBpmnBusinessRuleTask when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("62289820-55b6-11e2-877f-002564c97630")
public class GmBpmnTaskSimpleStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("72af6525-55c1-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("TASK_REPMODE", MetaKey.REPMODE);

    @objid ("72af6527-55c1-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = createStyleKey("TASK_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("72af6529-55c1-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = createStyleKey("TASK_FILLMODE", MetaKey.FILLMODE);

    @objid ("72af652b-55c1-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("TASK_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("72af652d-55c1-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("TASK_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("72af652f-55c1-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("TASK_FONT", MetaKey.FONT);

    @objid ("72b0eba9-55c1-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("TASK_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("72b0ebab-55c1-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("TASK_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("72b0ebad-55c1-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("TASK_SHOWTAGS", MetaKey.SHOWTAGS);

}
