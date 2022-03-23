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
package org.modelio.platform.model.ui.nattable.parts.data.texticon;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;

/**
 * A simple data structure holding a text and an icon.
 */
@objid ("4a66fbb2-d030-49e4-99c3-87073210b03f")
public class TextIcon {
    @objid ("7b9b3927-b8ea-4ec0-a41a-94c00566db45")
    private String text;

    @objid ("d3a10fdf-4a1d-45a7-9e2e-fa554cfd20e5")
    private Image icon;

    /**
     * Create a new TextIcon instance.
     * @param text a text.
     * @param icon an optional icon. Might be <code>null</code>.
     */
    @objid ("72810016-7d1d-4a2e-ac38-6f3b513bf686")
    public  TextIcon(String text, Image icon) {
        this.text = text;
        this.icon = icon;
        
    }

    /**
     * @return the current icon. Might be <code>null</code>.
     */
    @objid ("6d20a146-edb4-41d5-845b-64b7b0f90fa5")
    public Image getIcon() {
        return this.icon;
    }

    /**
     * @return the current text.
     */
    @objid ("90ac1c6c-0080-4df5-af5e-eccbc1acbb8f")
    public String getText() {
        return this.text;
    }

    @objid ("8a387270-8d57-4b84-a4c8-54e3d0c44a44")
    @Override
    public String toString() {
        return this.text;
    }

}
