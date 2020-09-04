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

package org.modelio.diagram.editor.state.elements.deephistory;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.state.style.StateAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmDeepHistory when its representation mode is
 * RepresentationMode.STRUCTURED
 */
@objid ("f50b705e-55b6-11e2-877f-002564c97630")
public class GmDeepHistoryStructuredStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("8158f20b-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("DEEPHISTORY_REPMODE", MetaKey.REPMODE);

    @objid ("8158f20d-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("DEEPHISTORY_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("8158f20f-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("DEEPHISTORY_FILLMODE", MetaKey.FILLMODE);

    @objid ("8158f211-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("DEEPHISTORY_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("8158f213-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("DEEPHISTORY_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("8158f215-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("DEEPHISTORY_FONT", MetaKey.FONT);

    @objid ("8158f217-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("DEEPHISTORY_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("8158f219-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("DEEPHISTORY_SHOWSTEREOTYPES",
                                                           MetaKey.SHOWSTEREOTYPES);

    @objid ("8158f21b-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("DEEPHISTORY_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("8158f21d-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = createStyleKey("DEEPHISTORY_SHOWLABEL", MetaKey.SHOWLABEL);

}
