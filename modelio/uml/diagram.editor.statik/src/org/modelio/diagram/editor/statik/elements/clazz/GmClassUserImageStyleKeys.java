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

package org.modelio.diagram.editor.statik.elements.clazz;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * GmClass style keys for the stereotype image representation mode.
 * 
 * @author cmarin
 */
@objid ("85732868-4cb2-46a8-8ec4-8ed1335add2b")
public class GmClassUserImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a39bd671-c6c1-4c1a-9701-bab643215869")
     static final StyleKey REPMODE = GmClassStructuredStyleKeys.REPMODE;

    @objid ("dac88316-efd1-40cc-88b1-a7f1682b9cc1")
     static final StyleKey FONT = GmClassStructuredStyleKeys.FONT;

    @objid ("9ecb82d7-0e04-4a14-be72-2bcc0184ea08")
     static final StyleKey TEXTCOLOR = GmClassStructuredStyleKeys.TEXTCOLOR;

    @objid ("83719a0a-e6f0-42d3-835f-2ca94cf13e27")
     static final StyleKey SHOWNAME = GmClassStructuredStyleKeys.SHOWNAME;

    @objid ("01bfdf0b-3e45-4901-906e-919a5d5b9b5d")
     static final StyleKey SHOWSTEREOTYPES = GmClassStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("2e496845-f5c8-4653-83c0-2b96148e66fe")
     static final StyleKey SHOWTAGS = GmClassStructuredStyleKeys.SHOWTAGS;

    @objid ("5a9a391d-d43a-4df1-87ac-eb1536d4b611")
     static final StyleKey SHOWVISIBILITY = GmClassStructuredStyleKeys.SHOWVISIBILITY;

    @objid ("a1e51b2c-0967-4377-bafa-772384a59bbc")
     static final StyleKey FILLCOLOR = GmClassStructuredStyleKeys.FILLCOLOR;

    @objid ("01180a59-9ae4-47fe-a9fd-ec75e5f2274d")
     static final StyleKey SHOWPORTS = GmClassStructuredStyleKeys.SHOWPORTS;

}
