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
 * RepresentationMode.SIMPLE
 */
@objid ("f52d0208-55b6-11e2-877f-002564c97630")
public class GmFinalStateSimpleStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("81632b3a-55c2-11e2-9337-002564c97630")
    static final StyleKey REPMODE = createStyleKey("FINALSTATE_REPMODE", MetaKey.REPMODE);

    @objid ("81632b3c-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLCOLOR = createStyleKey("FINALSTATE_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("81632b3e-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLMODE = createStyleKey("FINALSTATE_FILLMODE", MetaKey.FILLMODE);

    @objid ("8163524a-55c2-11e2-9337-002564c97630")
    static final StyleKey LINEWIDTH = createStyleKey("FINALSTATE_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("8163524c-55c2-11e2-9337-002564c97630")
    static final StyleKey FONT = createStyleKey("FINALSTATE_FONT", MetaKey.FONT);

    @objid ("8163524e-55c2-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = createStyleKey("FINALSTATE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("8163795a-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = createStyleKey("FINALSTATE_SHOWSTEREOTYPES",
                                                               MetaKey.SHOWSTEREOTYPES);

    @objid ("8163795c-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = createStyleKey("FINALSTATE_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("8163a069-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWLABEL = createStyleKey("FINALSTATE_SHOWLABEL", MetaKey.SHOWLABEL);

}
