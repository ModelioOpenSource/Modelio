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

package org.modelio.editors.richnote.gui.creation.mimetype;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.modelio.editors.richnote.api.RichNoteFormat;

/**
 * MIME type combo label provider.
 */
@objid ("734fd273-f92c-4478-a79b-d7352aafbcc2")
public class MimeTypeLabelProvider extends LabelProvider {
    @objid ("a3cf2747-1431-4655-8da2-f0630a70c24e")
    @Override
    public Image getImage(final Object element) {
        return ((RichNoteFormat) element).getIcon();
    }

    @objid ("7593ac61-54e7-4abf-9368-2bb3e2d59fe1")
    @Override
    public String getText(final Object element) {
        return ((RichNoteFormat) element).getLabel();
    }

}
