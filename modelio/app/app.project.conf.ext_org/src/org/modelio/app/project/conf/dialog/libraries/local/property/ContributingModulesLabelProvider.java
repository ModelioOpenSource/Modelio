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
/**
 *
 */
package org.modelio.app.project.conf.dialog.libraries.local.property;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.TextStyle;
import org.modelio.app.project.conf.plugin.AppProjectConfExt;
import org.modelio.gproject.module.GModule;
import org.modelio.platform.ui.UIColor;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;

/**
 * @author xzhang
 */
@objid ("ab6de3b0-9cb4-42f3-91fa-17329026240c")
public class ContributingModulesLabelProvider extends StyledCellLabelProvider {
    @objid ("23d480ef-be0b-40ad-8e4e-0a9bdccfa3cf")
    private static final String VERSION_FORMAT = " (V.R.C) ";

    @objid ("75f20e4d-8db1-4827-9b09-28dd7f735cbb")
    private static final Styler INVALID = new Styler() {
            @Override
            public void applyStyles(final TextStyle textStyle) {
                textStyle.foreground = UIColor.RED;
            }
        };

    @objid ("89e40e90-b88d-4476-81c7-0d6c7037c1fe")
    private final List<GModule> modules;

    @objid ("9fdb810d-f710-45a1-8e5c-cd504a81466d")
    public  ContributingModulesLabelProvider(final List<GModule> modules) {
        super();
        this.modules = modules;
        
    }

    @objid ("955d98bd-77d7-46c5-af31-ee1d75d89f93")
    @Override
    public void update(final ViewerCell cell) {
        final Object element = cell.getElement();
        StyledString text = new StyledString();
        if (element instanceof VersionedItem) {
            text = getStyledText((VersionedItem<?>) element);
        }
        cell.setText(text.getString());
        cell.setStyleRanges(text.getStyleRanges());
        super.update(cell);
        
    }

    @objid ("02882f5c-7169-4844-ae13-f966a48a310f")
    private StyledString getStyledText(final VersionedItem<?> item) {
        final Version version = item.getVersion();
        String text;
        if (version == null) {
            text = item.getName() + " (" + AppProjectConfExt.I18N.getString("RamcPropertyDialog.NoVersion") + ")";
        } else {
            text = item.getName() + version.toString(VERSION_FORMAT);
        }
        final boolean valid = isValidVersioned(item);
        return new StyledString(text, valid?null:INVALID);
    }

    @objid ("92904d50-6a51-4121-80b8-59d1e90f4e86")
    private boolean isValidVersioned(final VersionedItem<?> item) {
        for (final GModule module : this.modules) {
            if (module.getName().equals(item.getName())) {
                if (!module.getVersion().isOlderThan(item.getVersion())) {
                    return true;    // equal or newer
                }
            }
        }
        return false;
    }

}
