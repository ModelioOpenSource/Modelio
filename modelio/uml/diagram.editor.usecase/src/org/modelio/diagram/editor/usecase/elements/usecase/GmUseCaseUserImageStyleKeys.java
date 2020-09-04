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

package org.modelio.diagram.editor.usecase.elements.usecase;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.usecase.style.UseCaseAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

@objid ("9c304344-4c93-4009-a35b-2dda732665f0")
public class GmUseCaseUserImageStyleKeys extends UseCaseAbstractStyleKeyProvider {
    @objid ("a6f28600-5c71-432c-aea6-97ecf012499a")
    public static final StyleKey REPMODE = GmUseCaseStructuredStyleKeys.REPMODE;

    @objid ("ca4e6795-1264-43f9-b455-8435aac52428")
    public static final StyleKey FONT = GmUseCaseStructuredStyleKeys.FONT;

    @objid ("b1f6c43c-0259-4b91-9362-f0d1a7c26466")
    public static final StyleKey TEXTCOLOR = GmUseCaseStructuredStyleKeys.TEXTCOLOR;

    @objid ("e325e0af-bebe-4597-911f-31e3cafd8019")
    public static final StyleKey SHOWNAME = GmUseCaseStructuredStyleKeys.SHOWNAME;

    @objid ("fd04a9db-109b-4983-bf27-70685e1a72d9")
    public static final StyleKey SHOWSTEREOTYPES = GmUseCaseStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("eaef048d-a741-463f-aa33-f1ca5eca3498")
    public static final StyleKey SHOWTAGS = GmUseCaseStructuredStyleKeys.SHOWTAGS;

}
