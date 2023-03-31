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
package org.modelio.bpmn.diagram.editor.elements.bpmncallactivity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmn.diagram.editor.elements.common.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnBusinessRuleTask when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("609eeaf0-55b6-11e2-877f-002564c97630")
public class GmBpmnCallActivityStructuredStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("713680ab-55c1-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("SUBPROCESS_REPMODE", MetaKey.REPMODE);

    @objid ("713680ad-55c1-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = createStyleKey("SUBPROCESS_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("713680af-55c1-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = createStyleKey("SUBPROCESS_FILLMODE", MetaKey.FILLMODE);

    @objid ("713680b1-55c1-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("SUBPROCESS_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("713680b3-55c1-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("SUBPROCESS_FONT", MetaKey.FONT);

    @objid ("713680b5-55c1-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("SUBPROCESS_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("713680b7-55c1-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("SUBPROCESS_SHOWSTEREOTYPES",
                    MetaKey.SHOWSTEREOTYPES);

    @objid ("713680b9-55c1-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("SUBPROCESS_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("713680bb-55c1-11e2-9337-002564c97630")
    public static final StyleKey SHOWREPRESENTED = createStyleKey("CALLACTIVITY_SHOWREPRESENTED",
                    Boolean.class);

}
