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

package org.modelio.diagram.editor.state.elements.region;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.state.style.StateAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmRegion when its representation mode is
 * RepresentationMode.STRUCTURED
 */
@objid ("f56b91b7-55b6-11e2-877f-002564c97630")
public class GmRegionStructuredStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("817e5459-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("REGION_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("817e545b-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("REGION_FILLMODE", MetaKey.FILLMODE);

}
