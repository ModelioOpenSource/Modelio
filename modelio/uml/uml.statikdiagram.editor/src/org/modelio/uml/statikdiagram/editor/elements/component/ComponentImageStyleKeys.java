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

package org.modelio.uml.statikdiagram.editor.elements.component;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * {@link GmComponent} style keys for the stereotype image representation mode.
 * 
 * @author cmarin
 */
@objid ("349e767a-55b7-11e2-877f-002564c97630")
public class ComponentImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a5b6f16a-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = ComponentStructuredStyleKeys.REPMODE;

    @objid ("a5b6f16c-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = ComponentStructuredStyleKeys.FONT;

    @objid ("a5b6f16e-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = ComponentStructuredStyleKeys.TEXTCOLOR;

    @objid ("a5b6f170-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAME = ComponentStructuredStyleKeys.SHOWNAME;

    @objid ("a5b6f172-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = ComponentStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("a5b6f174-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = ComponentStructuredStyleKeys.SHOWTAGS;

    @objid ("a5b6f176-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWPORTS = ComponentStructuredStyleKeys.SHOWPORTS;

}
