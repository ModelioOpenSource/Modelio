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

package org.modelio.diagram.editor.bpmn.elements.style;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnBusinessRuleTask when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("6227117e-55b6-11e2-877f-002564c97630")
public class GmBpmnSubProcessStructuredStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("72af6509-55c1-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("SUBPROCESS_REPMODE", MetaKey.REPMODE);

    @objid ("72af650b-55c1-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = createStyleKey("SUBPROCESS_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("72af650d-55c1-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = createStyleKey("SUBPROCESS_FILLMODE", MetaKey.FILLMODE);

    @objid ("72af650f-55c1-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("SUBPROCESS_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("72af6511-55c1-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("SUBPROCESS_FONT", MetaKey.FONT);

    @objid ("72af6513-55c1-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("SUBPROCESS_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("72af6515-55c1-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("SUBPROCESS_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("72af6517-55c1-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("SUBPROCESS_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("72af6519-55c1-11e2-9337-002564c97630")
    public static final StyleKey SHOWCONTENT = createStyleKey("SUBPROCESS_SHOWCONTENT", Boolean.class);

}
