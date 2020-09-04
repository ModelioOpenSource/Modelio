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

package org.modelio.editors.richnote.gui.creation.doctype;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

@objid ("b0440fd7-0894-41bc-983e-60a3729c2f58")
class DocTypeChooserComparator extends ViewerComparator {
    @objid ("906aa599-98f3-49e1-a34f-93df2759bb14")
    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
        if (e1 instanceof AdapterModule && e2 instanceof AdapterModule) {
            AdapterModule mdac1 = (AdapterModule) e1;
            AdapterModule mdac2 = (AdapterModule) e2;
            return mdac1.getMdac().getName().compareToIgnoreCase(mdac2.getMdac().getName());
        } else if (e1 instanceof AdapterRichNoteType && e2 instanceof AdapterRichNoteType) {
            AdapterRichNoteType docType1 = (AdapterRichNoteType) e1;
            AdapterRichNoteType docType2 = (AdapterRichNoteType) e2;
            return docType1.getDocType().getName().compareToIgnoreCase(docType2.getDocType().getName());
        }
        return 0;
    }

}
