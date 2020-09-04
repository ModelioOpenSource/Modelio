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

package org.modelio.diagram.editor.statik.elements.bpmnprocess;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnProcess when its representation mode is
 * RepresentationMode.STRUCTURED
 */
@objid ("342d8c79-55b7-11e2-877f-002564c97630")
public class GmBpmnProcessStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a601b620-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("BPMNPROCESS_REPMODE", MetaKey.REPMODE);

    @objid ("a6033caa-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("BPMNPROCESS_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("a6033cac-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("BPMNPROCESS_FILLMODE", MetaKey.FILLMODE);

    @objid ("a6033cae-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("BPMNPROCESS_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("a6033cb0-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("BPMNPROCESS_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("a6033cb2-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("BPMNPROCESS_FONT", MetaKey.FONT);

    @objid ("a6033cb4-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("BPMNPROCESS_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("a6033cb6-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("BPMNPROCESS_SHOWSTEREOTYPES",
                                                           MetaKey.SHOWSTEREOTYPES);

    @objid ("a6033cb8-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("BPMNPROCESS_SHOWTAGS", MetaKey.SHOWTAGS);

}
