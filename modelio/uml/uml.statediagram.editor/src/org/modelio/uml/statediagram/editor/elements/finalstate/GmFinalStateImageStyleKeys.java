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

package org.modelio.uml.statediagram.editor.elements.finalstate;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statediagram.editor.style.StateAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmFinalstate when its representation mode is
 * RepresentationMode.IMAGE
 */
@objid ("f529f4d4-55b6-11e2-877f-002564c97630")
public class GmFinalStateImageStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("816219ce-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmFinalStateStructuredStyleKeys.REPMODE;

    @objid ("816219d0-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = GmFinalStateStructuredStyleKeys.FONT;

    @objid ("816219d2-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmFinalStateStructuredStyleKeys.TEXTCOLOR;

    @objid ("816240da-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmFinalStateStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("816240dc-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmFinalStateStructuredStyleKeys.SHOWTAGS;

    @objid ("abd0c214-a379-4f97-85ce-1278d425e293")
     static final StyleKey SHOWLABEL = createStyleKey("FINALSTATE_SHOWLABEL", MetaKey.SHOWLABEL);

}
