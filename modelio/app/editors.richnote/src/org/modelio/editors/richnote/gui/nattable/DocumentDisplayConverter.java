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

package org.modelio.editors.richnote.gui.nattable;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.data.convert.DisplayConverter;
import org.modelio.editors.richnote.plugin.EditorsRichNote;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Data type converter for an Document editor.
 * <p>
 * Assumes that the data value is stored as an {@link MRef} or an {@link Document}.
 * </p>
 */
@objid ("637aaba1-5a8c-4bd5-a0e0-a36835b46a6f")
public class DocumentDisplayConverter extends DisplayConverter {
    @objid ("c446776f-7c32-4a05-9d7c-25eba7368f8e")
    @Override
    public Object canonicalToDisplayValue(Object canonicalValue) {
        if (canonicalValue instanceof MRef) {
            final MRef mRef = (MRef) canonicalValue;
            if (mRef.mc.equals(Document.class.getSimpleName())) {
                return "";
            }
        } else if (canonicalValue instanceof Document) {
            return "";
        }
        return EditorsRichNote.I18N.getString("ScopeRichTextCellRenderer.NoRichNote");
    }

    @objid ("7503a8b7-5e56-4c4d-8ade-c71b89bf87ac")
    @Override
    public Object displayToCanonicalValue(Object displayValue) {
        return displayValue;
    }

}
