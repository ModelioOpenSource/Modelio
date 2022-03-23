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
package org.modelio.uml.statikdiagram.editor.elements.clazz;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * GmClass style keys for the stereotype image representation mode.
 * 
 * @author cmarin
 */
@objid ("343e5549-55b7-11e2-877f-002564c97630")
public class GmClassImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a508b00c-55c2-11e2-9337-002564c97630")
    static final StyleKey REPMODE = GmClassStructuredStyleKeys.REPMODE;

    @objid ("a508b00e-55c2-11e2-9337-002564c97630")
    static final StyleKey FONT = GmClassStructuredStyleKeys.FONT;

    @objid ("a508b010-55c2-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = GmClassStructuredStyleKeys.TEXTCOLOR;

    @objid ("a508b012-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWNAME = GmClassStructuredStyleKeys.SHOWNAME;

    @objid ("a508b014-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = GmClassStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("a508b016-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = GmClassStructuredStyleKeys.SHOWTAGS;

    @objid ("a508b018-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWVISIBILITY = GmClassStructuredStyleKeys.SHOWVISIBILITY;

    @objid ("a508b01a-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLCOLOR = GmClassStructuredStyleKeys.FILLCOLOR;

    @objid ("a508b01c-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWPORTS = GmClassStructuredStyleKeys.SHOWPORTS;

}
