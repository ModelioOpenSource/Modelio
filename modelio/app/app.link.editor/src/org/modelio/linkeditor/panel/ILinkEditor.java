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
package org.modelio.linkeditor.panel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.printing.PrinterData;
import org.modelio.platform.ui.panel.IPanelProvider;
import org.modelio.vcore.session.api.model.change.IModelChangeListener;

@objid ("381cb567-1517-411e-819f-ed24523b59cd")
public interface ILinkEditor extends IPanelProvider, IModelChangeListener {
    /**
     * Get the configurator of this link editor. The configurator is used to modify and/or monitor the link editor configuration.
     * @return
     */
    @objid ("5f75e5a2-7f31-4316-af9d-dcb9fece5385")
    ILinkEditorConfigurator getConfigurator();

    /**
     * Set the link editor in edition mode:
     * <ul>
     * <li>disable selection change handling (the center element remains fixed)</li>
     * <li>enable dropping elements to create links</li>
     * </ul>
     * @param onOff
     */
    @objid ("f9e4aa29-3bcd-4f40-bca0-6c9e43d06045")
    void setEditMode(boolean onOff);

    @objid ("f8d14145-52b8-4fe8-9873-57cf0a0899e4")
    boolean isEditMode();

    /**
     * Set the zoom level.
     * @param level
     */
    @objid ("df7ffde0-54e2-48f0-8f9e-55cff0e084d3")
    void setZoomLevel(double level);

    /**
     * GEt the current zoom level
     * @return
     */
    @objid ("d5017a19-62a8-4235-a763-b71fc614e118")
    double getZoomLevel();

    @objid ("587c8917-3e77-450f-9919-c136b8457154")
    void print(PrinterData data);

    /**
     * Create a new image of the viewer contents.
     * The caller is in charge of disposing the image.
     */
    @objid ("cf2b2bc3-ae4b-4046-9db9-b5b8423a08b8")
    Image getImage();
}

