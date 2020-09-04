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

package org.modelio.diagram.editor.statik.elements.informationitem;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * GmClass style keys for the stereotype image representation mode.
 * 
 * @author cmarin
 */
@objid ("0cbacbd3-6f6b-456b-8c49-9fbfd371fb8c")
public class InformationItemUserImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("27d11df7-513e-4b0a-8ea1-604baa1c4ec9")
     static final StyleKey REPMODE = InformationItemStructuredStyleKeys.REPMODE;

    @objid ("d44abd84-e37d-4d1d-8d5c-111d5d9df204")
     static final StyleKey FONT = InformationItemStructuredStyleKeys.FONT;

    @objid ("b322fbc2-ab4e-4fe3-9d0d-fa7ed2e955f2")
     static final StyleKey TEXTCOLOR = InformationItemStructuredStyleKeys.TEXTCOLOR;

    @objid ("73ab8c15-4173-4eb9-9161-120ae7ded708")
     static final StyleKey SHOWNAME = InformationItemStructuredStyleKeys.SHOWNAME;

    @objid ("93656b4e-df99-43d1-8869-b6d626f3243e")
     static final StyleKey SHOWSTEREOTYPES = InformationItemStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("e3400d61-a618-4a31-b207-bb51b53c86f2")
     static final StyleKey SHOWTAGS = InformationItemStructuredStyleKeys.SHOWTAGS;

}
