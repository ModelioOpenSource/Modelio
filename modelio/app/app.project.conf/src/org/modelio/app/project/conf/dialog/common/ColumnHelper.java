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
package org.modelio.app.project.conf.dialog.common;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.modelio.app.project.conf.plugin.AppProjectConf;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.platform.model.ui.swt.images.FragmentImageService;
import org.modelio.vbasic.files.FileUtils;

/**
 * Create already configured table columns.
 * @author cmarin
 */
@objid ("1000e997-c3d3-44bb-8b5d-2b3c371fb8e5")
public class ColumnHelper {
    /**
     * Create a fragment name table column viewer.
     * @param viewer the table viewer
     * @return the column
     */
    @objid ("43b3a0a5-6b1b-44ae-a01d-e2a6a70aeafb")
    public static TableViewerColumn createFragmentNameColumn(TableViewer viewer) {
        TableViewerColumn nameColumn = new TableViewerColumn(viewer, SWT.NONE);
        nameColumn.getColumn().setText(AppProjectConf.I18N.getString("Column.fragmentName.title"));
        nameColumn.getColumn().setToolTipText(AppProjectConf.I18N.getString("Column.fragmentName.tooltip"));
        nameColumn.getColumn().setWidth(120);
        nameColumn.getColumn().setResizable(true);
        nameColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                if (element instanceof IGModelFragment) {
                    return ((IGModelFragment) element).getId();
                }
                return ""; //$NON-NLS-1$
            }
        
            @Override
            public Image getImage(Object element) {
                if (element instanceof IGModelFragment) {
                    return FragmentImageService.getImage((IGModelFragment) element);
                }
                return null;
            }
        });
        return nameColumn;
    }

    /**
     * Create a fragment scope table column viewer.
     * @param viewer the table viewer
     * @return the column
     */
    @objid ("f81f4613-91fa-4ab7-b8e5-765df7c96957")
    public static TableViewerColumn createFragmentScopeColumn(TableViewer viewer) {
        TableViewerColumn scopeColumn = new TableViewerColumn(viewer, SWT.NONE);
        scopeColumn.getColumn().setText(AppProjectConf.I18N.getString("Column.fragmentScope.title"));
        scopeColumn.getColumn().setToolTipText(AppProjectConf.I18N.getString("Column.fragmentScope.tooltip"));
        scopeColumn.getColumn().setWidth(120);
        scopeColumn.getColumn().setResizable(true);
        scopeColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                if (element instanceof IGModelFragment) {
                    return ScopeHelper.getText(((IGModelFragment) element).getDefinitionScope());
                }
                return ""; //$NON-NLS-1$
            }
        });
        return scopeColumn;
    }

    /**
     * Create a fragment metamodel version table column viewer.
     * @param viewer the table viewer
     * @return the column
     */
    @objid ("f5c8aaff-b07d-4134-b3f0-bea5dcf07fda")
    public static TableViewerColumn createFragmentMmVersionColumn(TableViewer viewer) {
        TableViewerColumn mmVer = new TableViewerColumn(viewer, SWT.NONE);
        mmVer.getColumn().setWidth(200);
        mmVer.getColumn().setResizable(true);
        mmVer.getColumn().setText(AppProjectConf.I18N.getString("Column.fragmentMmVersion.title"));
        mmVer.getColumn().setToolTipText(AppProjectConf.I18N.getString("Column.fragmentMmVersion.tooltip"));
        mmVer.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                if (element instanceof IGModelFragment) {
                    try {
                        return ((IGModelFragment) element).getRequiredMetamodelDescriptor().toString();
                    } catch (IOException e) {
                        return FileUtils.getLocalizedMessage(e);
                    }
                }
                return ""; //$NON-NLS-1$
            }
            @Override
            public String getToolTipText(Object element) {
                if (element instanceof IGModelFragment) {
                    try {
                        return AppProjectConf.I18N.getMessage("Column.fragmentMmVersion.val.tooltip", ((IGModelFragment) element).getRequiredMetamodelDescriptor().toString());
                    } catch (IOException e) {
                        return FileUtils.getLocalizedMessage(e);
                    }
                }
                return super.getToolTipText(element);
            }
        });
        return mmVer;
    }

}
