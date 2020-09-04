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

package org.modelio.diagram.editor.statik.elements.activity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;

/**
 * This class provides the StyleKey constants for a {@link Activity} when its representation mode is
 * RepresentationMode.IMAGE
 */
@objid ("3ac480dd-39c2-403b-8b44-92afe5d3e847")
public class GmActivityUserImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("219798f3-61d6-4076-939a-8ae0c7a8d906")
     static final StyleKey REPMODE = GmActivityStructuredStyleKeys.REPMODE;

    @objid ("50d6219c-e193-40d2-ba8b-1965035fdce0")
     static final StyleKey FONT = GmActivityStructuredStyleKeys.FONT;

    @objid ("3bfcb30c-6d39-47aa-b81a-e67a65e5f1ab")
     static final StyleKey TEXTCOLOR = GmActivityStructuredStyleKeys.TEXTCOLOR;

    @objid ("320dc4f1-dd20-40f4-b0a1-0910fba10d0c")
     static final StyleKey SHOWSTEREOTYPES = GmActivityStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("168d7eb1-57c0-446d-8b4f-b218feb42b33")
     static final StyleKey SHOWTAGS = GmActivityStructuredStyleKeys.SHOWTAGS;

}
