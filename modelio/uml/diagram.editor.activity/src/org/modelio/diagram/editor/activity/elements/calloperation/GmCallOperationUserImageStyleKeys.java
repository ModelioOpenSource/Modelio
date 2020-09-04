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

package org.modelio.diagram.editor.activity.elements.calloperation;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.style.ActivityAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmCalloperation when its representation mode is RepresentationMode.IMAGE
 */
@objid ("7e7c5dba-0ec7-4739-a51a-54a1fdb6825f")
public class GmCallOperationUserImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d9867e99-875c-46b0-b20b-3712d4811647")
     static final StyleKey REPMODE = GmCallOperationStructuredStyleKeys.REPMODE;

    @objid ("ec5eef85-c1d5-440d-928d-80122e7591ba")
     static final StyleKey FONT = GmCallOperationStructuredStyleKeys.FONT;

    @objid ("a3fdc3cf-a95f-4048-8bc5-167e39466ff1")
     static final StyleKey TEXTCOLOR = GmCallOperationStructuredStyleKeys.TEXTCOLOR;

    @objid ("efeb3ef6-2dc0-411b-96f9-e827ebf3c9f0")
     static final StyleKey SHOWSTEREOTYPES = GmCallOperationStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("800a0338-2c01-4f24-9bef-b99abfd658f8")
     static final StyleKey SHOWTAGS = GmCallOperationStructuredStyleKeys.SHOWTAGS;

    @objid ("4e9ebcd9-dd11-4596-9b29-f1e973a3a382")
     static final StyleKey AUTOSHOWPINS = GmCallOperationStructuredStyleKeys.AUTOSHOWPINS;

}
