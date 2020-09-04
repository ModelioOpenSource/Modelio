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

package org.modelio.diagram.editor.statik.elements.instance;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * GmClass style keys for the stereotype image representation mode.
 * 
 * @author cmarin
 */
@objid ("7d54d569-53b8-4cc4-b4fb-5f913d42d4c2")
public class GmInstanceUserImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a498cde1-e07a-4774-a895-75089c9c32cc")
     static final StyleKey REPMODE = GmInstanceStructuredStyleKeys.REPMODE;

    @objid ("d5a1f4d9-3206-4745-960c-fd06e9505e2b")
     static final StyleKey FONT = GmInstanceStructuredStyleKeys.FONT;

    @objid ("fb1c4faf-cdcd-4733-822e-015a2bb3f643")
     static final StyleKey TEXTCOLOR = GmInstanceStructuredStyleKeys.TEXTCOLOR;

    @objid ("d3acc1fd-efc4-4c32-8f57-3685f9181aed")
     static final StyleKey SHOWNAME = GmInstanceStructuredStyleKeys.SHOWNAME;

    @objid ("930fed8d-92fd-4b26-ae08-0a44d93651c9")
     static final StyleKey SHOWSTEREOTYPES = GmInstanceStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("03121cc3-694e-4080-9642-c0423d28d099")
     static final StyleKey SHOWTAGS = GmInstanceStructuredStyleKeys.SHOWTAGS;

}
