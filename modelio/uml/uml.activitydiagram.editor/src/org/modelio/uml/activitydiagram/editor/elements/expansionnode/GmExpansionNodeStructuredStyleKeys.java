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
package org.modelio.uml.activitydiagram.editor.elements.expansionnode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmExpansionNode when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("2a5b50db-55b6-11e2-877f-002564c97630")
public class GmExpansionNodeStructuredStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d1f4d977-55c0-11e2-9337-002564c97630")
    static final StyleKey REPMODE = createStyleKey("EXPANSIONNODE_REPMODE", MetaKey.REPMODE);

    @objid ("d1f4d979-55c0-11e2-9337-002564c97630")
    static final StyleKey FILLCOLOR = createStyleKey("EXPANSIONNODE_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("d1f4d97b-55c0-11e2-9337-002564c97630")
    static final StyleKey FILLMODE = createStyleKey("EXPANSIONNODE_FILLMODE", MetaKey.FILLMODE);

    @objid ("d1f4d97d-55c0-11e2-9337-002564c97630")
    static final StyleKey LINECOLOR = createStyleKey("EXPANSIONNODE_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d1f4d97f-55c0-11e2-9337-002564c97630")
    static final StyleKey LINEWIDTH = createStyleKey("EXPANSIONNODE_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d1f4d981-55c0-11e2-9337-002564c97630")
    static final StyleKey FONT = createStyleKey("EXPANSIONNODE_FONT", MetaKey.FONT);

    @objid ("d1f4d983-55c0-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = createStyleKey("EXPANSIONNODE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d1f4d985-55c0-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = createStyleKey("EXPANSIONNODE_SHOWSTEREOTYPES",
                MetaKey.SHOWSTEREOTYPES);

    @objid ("d1f4d987-55c0-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = createStyleKey("EXPANSIONNODE_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("d1f4d989-55c0-11e2-9337-002564c97630")
    static final StyleKey SHOWLABEL = createStyleKey("EXPANSIONNODE_SHOWLABEL", MetaKey.SHOWLABEL);

}
