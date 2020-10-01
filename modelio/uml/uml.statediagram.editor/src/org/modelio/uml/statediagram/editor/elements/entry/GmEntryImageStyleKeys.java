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

package org.modelio.uml.statediagram.editor.elements.entry;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statediagram.editor.style.StateAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmEntry when its representation mode is RepresentationMode.IMAGE
 */
@objid ("f5118ae2-55b6-11e2-877f-002564c97630")
public class GmEntryImageStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("812b2b51-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmEntryStructuredStyleKeys.REPMODE;

    @objid ("812b2b53-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = GmEntryStructuredStyleKeys.FONT;

    @objid ("812b2b55-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmEntryStructuredStyleKeys.TEXTCOLOR;

    @objid ("812b2b57-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmEntryStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("812cb1ea-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmEntryStructuredStyleKeys.SHOWTAGS;

    @objid ("9acb9840-dd59-48d4-b8e8-3c36ab85319d")
     static final StyleKey SHOWLABEL = GmEntryStructuredStyleKeys.SHOWLABEL;

}
