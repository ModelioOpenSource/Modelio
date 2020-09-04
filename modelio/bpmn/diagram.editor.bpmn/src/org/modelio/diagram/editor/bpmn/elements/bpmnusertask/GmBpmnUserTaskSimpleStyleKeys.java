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

package org.modelio.diagram.editor.bpmn.elements.bpmnusertask;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmGmBpmnUserTask when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("61ea0895-55b6-11e2-877f-002564c97630")
public class GmBpmnUserTaskSimpleStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("726319cc-55c1-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("CALLOPERATION_REPMODE", MetaKey.REPMODE);

    @objid ("726319ce-55c1-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = createStyleKey("CALLOPERATION_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("726319d0-55c1-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = createStyleKey("CALLOPERATION_FILLMODE", MetaKey.FILLMODE);

    @objid ("726319d2-55c1-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("CALLOPERATION_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("726319d4-55c1-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("CALLOPERATION_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("726319d6-55c1-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("CALLOPERATION_FONT", MetaKey.FONT);

    @objid ("726319d8-55c1-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("CALLOPERATION_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("726319da-55c1-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("CALLOPERATION_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("7264a069-55c1-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("CALLOPERATION_SHOWTAGS", MetaKey.SHOWTAGS);

}
