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
package org.modelio.uml.statediagram.editor.elements.initialstate;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statediagram.editor.style.StateAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a {@link GmInitialState} when its representation mode is
 * RepresentationMode.SIMPLE
 */
@objid ("f5487967-55b6-11e2-877f-002564c97630")
public class GmInitialStateSimpleStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("810812ec-55c2-11e2-9337-002564c97630")
    static final StyleKey REPMODE = GmInitialStateStructuredStyleKeys.REPMODE;

    @objid ("810812ee-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLCOLOR = GmInitialStateStructuredStyleKeys.FILLCOLOR;

    @objid ("810812f0-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLMODE = GmInitialStateStructuredStyleKeys.FILLMODE;

    @objid ("810812f2-55c2-11e2-9337-002564c97630")
    static final StyleKey LINECOLOR = createStyleKey("INITIALSTATE_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("81099989-55c2-11e2-9337-002564c97630")
    static final StyleKey LINEWIDTH = GmInitialStateStructuredStyleKeys.LINEWIDTH;

    @objid ("8109998b-55c2-11e2-9337-002564c97630")
    static final StyleKey FONT = GmInitialStateStructuredStyleKeys.FONT;

    @objid ("8109998d-55c2-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = GmInitialStateStructuredStyleKeys.TEXTCOLOR;

    @objid ("8109998f-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = GmInitialStateStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("81099991-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = GmInitialStateStructuredStyleKeys.SHOWTAGS;

    @objid ("81099993-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWLABEL = GmInitialStateStructuredStyleKeys.SHOWLABEL;

}
