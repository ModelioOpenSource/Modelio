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
package org.modelio.editors.richnote.gui.creation.mimetype;

import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.editors.richnote.api.RichNoteFormat;
import org.modelio.editors.richnote.api.RichNoteFormatRegistry;
import org.modelio.editors.richnote.api.SupportLevel;

/**
 * Content provider for the MIME type combo.
 */
@objid ("6943f192-3ad6-4f31-97b9-bbc85813698e")
public class MimeTypeContentProvider implements IStructuredContentProvider {
    @objid ("62cb9eb5-e41e-4246-9d3a-ce0f3a5f982d")
    public  MimeTypeContentProvider() {
        super();
    }

    @objid ("afb76128-7bc5-4ae0-95eb-3445741c4497")
    @Override
    public void dispose() {
        // nothing
    }

    @objid ("5fff9fda-8823-42b0-bd23-7593c8ec5f85")
    @Override
    public Object[] getElements(final Object inputElement) {
        Collection<RichNoteFormat> formats = RichNoteFormatRegistry.getInstance().getAllEditableFormats();
        ArrayList<RichNoteFormat> ret = new ArrayList<>(formats.size());
        
        for (RichNoteFormat f : formats) {
            if (f.getSupportLevel() == SupportLevel.Primary) {
                ret.add(f);
            }
        }
        return ret.toArray();
    }

    @objid ("88a8076f-b7df-419c-ab82-3c38dd6ded60")
    @Override
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
        // ignore
    }

}
