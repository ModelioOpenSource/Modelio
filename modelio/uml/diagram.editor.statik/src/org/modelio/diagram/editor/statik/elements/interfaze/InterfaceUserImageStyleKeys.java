/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.statik.elements.interfaze;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * GmClass style keys for the stereotype image representation mode.
 * 
 * @author cmarin
 */
@objid ("676c3bf2-e1e5-4da8-897a-c97ad63513dc")
public class InterfaceUserImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("cf2aa069-4e10-4a31-9b1c-cfb8cb9df58a")
     static final StyleKey REPMODE = InterfaceStructuredStyleKeys.REPMODE;

    @objid ("6fcf0fa0-be7b-440c-a94f-8e87df7ae6c1")
     static final StyleKey FILLCOLOR = InterfaceStructuredStyleKeys.FILLCOLOR;

    @objid ("eb364f33-a3c8-4771-b5d9-a7421a65d752")
     static final StyleKey FONT = InterfaceStructuredStyleKeys.FONT;

    @objid ("58c3dfd4-7c01-4e5d-84cb-bb1e06a1786f")
     static final StyleKey TEXTCOLOR = InterfaceStructuredStyleKeys.TEXTCOLOR;

    @objid ("15f86573-71c8-4f5b-b54f-5b5e5ecf776b")
     static final StyleKey SHOWNAME = InterfaceStructuredStyleKeys.SHOWNAME;

    @objid ("503c6d9e-267a-400b-b1fa-6916f88378b4")
     static final StyleKey SHOWSTEREOTYPES = InterfaceStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("884a7605-fe0a-4dce-94ce-51e8ab998550")
     static final StyleKey SHOWTAGS = InterfaceStructuredStyleKeys.SHOWTAGS;

    @objid ("435122eb-754b-4ea5-94b6-3114b6302842")
     static final StyleKey SHOWVISIBILITY = InterfaceStructuredStyleKeys.SHOWVISIBILITY;

}
