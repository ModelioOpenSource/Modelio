/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.editor.statik.elements.enumeration;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * GmClass style keys for the stereotype image representation mode.
 * 
 * @author cmarin
 */
@objid ("122b486b-413d-43ce-a528-539f1dcce422")
public class EnumUserImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("1d23f252-92f3-4f6b-ad82-352ea524ca27")
     static final StyleKey REPMODE = EnumStructuredStyleKeys.REPMODE;

    @objid ("aea0d3c4-c956-47a7-919b-018f7003c6b4")
     static final StyleKey FONT = EnumStructuredStyleKeys.FONT;

    @objid ("3a512db0-d83b-4892-b761-b56e169c7640")
     static final StyleKey TEXTCOLOR = EnumStructuredStyleKeys.TEXTCOLOR;

    @objid ("c19b8106-8213-4ecd-9602-49f189533404")
     static final StyleKey SHOWNAME = EnumStructuredStyleKeys.SHOWNAME;

    @objid ("8c4ab618-e218-4f42-b843-bd238ba91abd")
     static final StyleKey SHOWSTEREOTYPES = EnumStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("a5bc9019-8233-4ec1-9785-4405771c6d0c")
     static final StyleKey SHOWTAGS = EnumStructuredStyleKeys.SHOWTAGS;

    @objid ("cccb434a-7da7-446c-aaf9-4c3eced5ad9a")
     static final StyleKey SHOWVISIBILITY = EnumStructuredStyleKeys.SHOWVISIBILITY;

}
