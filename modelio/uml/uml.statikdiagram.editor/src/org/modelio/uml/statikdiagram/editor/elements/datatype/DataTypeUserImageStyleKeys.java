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

package org.modelio.uml.statikdiagram.editor.elements.datatype;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * GmClass style keys for the stereotype image representation mode.
 * 
 * @author cmarin
 */
@objid ("ed743055-2985-4507-85f7-d38c4482368c")
public class DataTypeUserImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("77457fb4-86a8-4d36-a4ef-f56c062713e9")
     static final StyleKey REPMODE = DataTypeStructuredStyleKeys.REPMODE;

    @objid ("b0a850ef-cd0f-49c0-bbdb-66924b1ff7e3")
     static final StyleKey FONT = DataTypeStructuredStyleKeys.FONT;

    @objid ("1411d94c-dcae-4c54-88e9-be265511bbaa")
     static final StyleKey TEXTCOLOR = DataTypeStructuredStyleKeys.TEXTCOLOR;

    @objid ("0581d31b-a99b-40d3-a2e9-a8ac2d4c9836")
     static final StyleKey SHOWNAME = DataTypeStructuredStyleKeys.SHOWNAME;

    @objid ("a478494d-367c-463d-b643-8de21a28cbcd")
     static final StyleKey SHOWSTEREOTYPES = DataTypeStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("032280d2-4f37-477f-901f-346062c99cda")
     static final StyleKey SHOWTAGS = DataTypeStructuredStyleKeys.SHOWTAGS;

    @objid ("4a6fe1ee-8638-47c0-85f7-b59eca26c5c4")
     static final StyleKey SHOWVISIBILITY = DataTypeStructuredStyleKeys.SHOWVISIBILITY;

}
