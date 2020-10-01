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

package org.modelio.uml.statediagram.editor.elements.fork;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statediagram.editor.elements.ForkJoinOrientation;
import org.modelio.uml.statediagram.editor.style.StateAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmFork when its representation mode is RepresentationMode.SIMPLE
 */
@objid ("f53629f7-55b6-11e2-877f-002564c97630")
public class GmForkStateSimpleStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("81207cec-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("FORK_REPMODE", MetaKey.REPMODE);

    @objid ("81207cee-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("FORK_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("81207cf0-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("FORK_FILLMODE", MetaKey.FILLMODE);

    @objid ("81207cf2-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("FORK_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("81207cf4-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("FORK_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("81207cf6-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("FORK_FONT", MetaKey.FONT);

    @objid ("81207cf8-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("FORK_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("81207cfa-55c2-11e2-9337-002564c97630")
     static final StyleKey ORIENTATION = createStyleKey("FORK_ORIENTATION", ForkJoinOrientation.class);

    @objid ("81207cfc-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("FORK_SHOWSTEREOTYPES", MetaKey.SHOWSTEREOTYPES);

    @objid ("81207cfe-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("FORK_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("81207d00-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = createStyleKey("FORK_SHOWLABEL", MetaKey.SHOWLABEL);

}
