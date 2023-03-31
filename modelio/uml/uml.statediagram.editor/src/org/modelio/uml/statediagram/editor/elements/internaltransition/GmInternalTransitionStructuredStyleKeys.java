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
package org.modelio.uml.statediagram.editor.elements.internaltransition;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statediagram.editor.style.StateAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmExit when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("f5501a7f-55b6-11e2-877f-002564c97630")
public class GmInternalTransitionStructuredStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("816ec3fb-55c2-11e2-9337-002564c97630")
    static final StyleKey FONT = createStyleKey("INTERNALTRANSITION_FONT", MetaKey.FONT);

    @objid ("816ec3fd-55c2-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = createStyleKey("INTERNALTRANSITION_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("816eeb0a-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = createStyleKey("INTERNALTRANSITION_SHOWSTEREOTYPES",
                                                               MetaKey.SHOWSTEREOTYPES);

    @objid ("816eeb0c-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = createStyleKey("INTERNALTRANSITION_SHOWTAGS", MetaKey.SHOWTAGS);

}
