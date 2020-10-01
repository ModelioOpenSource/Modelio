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

package org.modelio.uml.statikdiagram.editor.elements.bpmnbehavior;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a {@link GmBpmnBehavior} when its representation mode is
 * RepresentationMode.STRUCTURED
 */
@objid ("34215759-55b7-11e2-877f-002564c97630")
public class GmBpmnBehaviorStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a5fd2240-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("BPMNBEHAVIOR_REPMODE", MetaKey.REPMODE);

    @objid ("a5fd2242-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("BPMNBEHAVIOR_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("a5fd2244-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("BPMNBEHAVIOR_FILLMODE", MetaKey.FILLMODE);

    @objid ("a5fd2246-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("BPMNBEHAVIOR_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("a5fd2248-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("BPMNBEHAVIOR_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("a5fd224a-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("BPMNBEHAVIOR_FONT", MetaKey.FONT);

    @objid ("a5fea8c9-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("BPMNBEHAVIOR_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("a5fea8cb-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("BPMNBEHAVIOR_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("a5fea8cd-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("BPMNBEHAVIOR_SHOWTAGS", MetaKey.SHOWTAGS);

}
