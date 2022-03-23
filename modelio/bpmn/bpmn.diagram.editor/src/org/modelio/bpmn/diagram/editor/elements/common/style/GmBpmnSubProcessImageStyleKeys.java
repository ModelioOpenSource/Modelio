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
package org.modelio.bpmn.diagram.editor.elements.common.style;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnBusinessRuleTask when its representation mode is RepresentationMode.IMAGE
 */
@objid ("62240476-55b6-11e2-877f-002564c97630")
public class GmBpmnSubProcessImageStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("72adde78-55c1-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("SUBPROCESS_REPMODE", MetaKey.REPMODE);

    @objid ("72adde7a-55c1-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("SUBPROCESS_FONT", MetaKey.FONT);

    @objid ("72adde7c-55c1-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("SUBPROCESS_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("72adde7e-55c1-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("SUBPROCESS_SHOWSTEREOTYPES",
                MetaKey.SHOWSTEREOTYPES);

    @objid ("72adde80-55c1-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("SUBPROCESS_SHOWTAGS", MetaKey.SHOWTAGS);

}
