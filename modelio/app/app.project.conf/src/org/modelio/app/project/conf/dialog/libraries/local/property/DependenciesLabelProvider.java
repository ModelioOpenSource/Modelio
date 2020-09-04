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

package org.modelio.app.project.conf.dialog.libraries.local.property;

import java.io.IOException;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.TextStyle;
import org.modelio.app.project.conf.plugin.AppProjectConf;
import org.modelio.gproject.data.ramc.IModelComponentInfos;
import org.modelio.gproject.fragment.ramcfile.RamcFileFragment;
import org.modelio.ui.UIColor;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;

@objid ("e15ff7d2-94b8-41d3-815f-6c8856770801")
class DependenciesLabelProvider extends StyledCellLabelProvider {
    @objid ("c259c973-22ab-42e4-90ee-bb30ec9814de")
    public static final Styler INVALID = new Styler() {
        @Override
        public void applyStyles(TextStyle textStyle) {
            textStyle.foreground = UIColor.RED;
        }
    };

    @objid ("37356e22-2a51-4b45-a073-3da24245963c")
    private List<RamcFileFragment> fragments;

    @objid ("aa5cfa19-237d-4eef-9e0e-26a83dde3ccb")
    public DependenciesLabelProvider(List<RamcFileFragment> fragments) {
        super();
        this.fragments = fragments;
    }

    @objid ("e10cd899-7853-4f80-a769-9b9139262c28")
    @Override
    public void update(ViewerCell cell) {
        Object element = cell.getElement();
        StyledString text = new StyledString();
        if (element instanceof VersionedItem) {
            text = getStyledText((VersionedItem<?>) element);
        }
        cell.setText(text.getString());
        cell.setStyleRanges(text.getStyleRanges());
        super.update(cell);
    }

    @objid ("7cc0f797-ec1e-48ac-82d3-ff7605d5dc8e")
    private StyledString getStyledText(VersionedItem<?> item) {
        Version version = item.getVersion();
        String text;
        if (version == null) {
            text = item.getName() + " (" + AppProjectConf.I18N.getString("RamcProperty.NoVersion") + ")";
        } else {
            text = item.getName() + version.toString("(V.R.C)");
        }
        boolean valid = isValidVersioned(item);
        return new StyledString(text, valid?null:INVALID);
    }

    @objid ("6435d80e-387d-45a5-9cd7-36a9fa52158b")
    private boolean isValidVersioned(VersionedItem<?> item) {
        for (RamcFileFragment fragment : this.fragments) {
            try {
                IModelComponentInfos infos = fragment.getInformations();
                if (infos.getName().equals(item.getName())) {
                    if (!infos.getVersion().isOlderThan(item.getVersion())) {
                        return true;    // equal or newer
                    }
                }
            } catch (IOException e) {
                AppProjectConf.LOG.error(e);
            }
        }
        return false;
    }

}
