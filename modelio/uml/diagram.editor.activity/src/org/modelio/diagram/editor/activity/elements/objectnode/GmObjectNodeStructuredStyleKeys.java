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

package org.modelio.diagram.editor.activity.elements.objectnode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmObjectNode when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("2add03f9-55b6-11e2-877f-002564c97630")
public class GmObjectNodeStructuredStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d24a4c6a-55c0-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("OBJECTNODE_REPMODE", MetaKey.REPMODE);

    @objid ("d24a4c6c-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("OBJECTNODE_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("d24a4c6e-55c0-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("OBJECTNODE_FILLMODE", MetaKey.FILLMODE);

    @objid ("d24a4c70-55c0-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("OBJECTNODE_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d24a4c72-55c0-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("OBJECTNODE_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d24a4c74-55c0-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("OBJECTNODE_FONT", MetaKey.FONT);

    @objid ("d24a4c76-55c0-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("OBJECTNODE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d24a4c78-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("OBJECTNODE_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("d24a4c7a-55c0-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("OBJECTNODE_SHOWTAGS", MetaKey.SHOWTAGS);

}
