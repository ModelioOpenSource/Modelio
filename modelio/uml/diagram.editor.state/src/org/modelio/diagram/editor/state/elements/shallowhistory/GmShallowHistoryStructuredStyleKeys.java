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

package org.modelio.diagram.editor.state.elements.shallowhistory;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.state.style.StateAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmShallowHistory when its representation mode is
 * RepresentationMode.STRUCTURED
 */
@objid ("f574b976-55b6-11e2-877f-002564c97630")
public class GmShallowHistoryStructuredStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("818420bb-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("SHALLOWHISTORY_REPMODE", MetaKey.REPMODE);

    @objid ("818447ca-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("SHALLOWHISTORY_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("818447cc-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("SHALLOWHISTORY_FILLMODE", MetaKey.FILLMODE);

    @objid ("818447ce-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("SHALLOWHISTORY_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("81846eda-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("SHALLOWHISTORY_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("81846edc-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("SHALLOWHISTORY_FONT", MetaKey.FONT);

    @objid ("818495e9-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("SHALLOWHISTORY_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("818495eb-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("SHALLOWHISTORY_SHOWSTEREOTYPES",
                                                           MetaKey.SHOWSTEREOTYPES);

    @objid ("8184bcf9-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("SHALLOWHISTORY_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("8184bcfb-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = createStyleKey("SHALLOWHISTORY_SHOWLABEL", MetaKey.SHOWLABEL);

}
