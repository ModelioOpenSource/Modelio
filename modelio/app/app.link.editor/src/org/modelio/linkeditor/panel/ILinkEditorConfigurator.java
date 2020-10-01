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

import java.beans.PropertyChangeListener;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.linkeditor.panel.ILinkEditorConfiguration.Orientation;

/**
 * Fast Link Editor configurator.
 * 
 * Can be used to <ul>
 * <li>change the current configuration of a FLE.</li>
 * <li>listen to changes occurring on the current configuration of a FLE</li>
 * </ul>
 */
@objid ("586db3ca-f7b6-435a-8a95-45ff1f9eeea3")
public interface ILinkEditorConfigurator {
    @objid ("75f8b6fb-4ab8-46a5-b752-7aa4ff177e3c")
    int getLeftDepth();

    @objid ("817edbec-dbd9-470d-8f16-1b1c7345cc43")
    int getRightDepth();

    @objid ("1d2db000-4581-4167-b2e9-c49a2b447f00")
    void setLeftDepth(int depth);

    @objid ("5c51a8a7-1835-42fa-b09f-007086a92550")
    void setRightDepth(int depth);

    @objid ("6505b335-f88f-4d5c-a842-b88d5d91f548")
    Orientation getLayoutOrientation();

    @objid ("fdd113de-359a-4fff-ba6d-5b33043d0453")
    void setLayoutOrientation(Orientation o);

    @objid ("37a92896-5c16-4339-8b96-765db4fca226")
    void setLinkFilter(ILinkEditorFilter linkFilter);

    @objid ("ddc0f31d-a810-4128-901a-0b61d694203a")
    ILinkEditorFilter getLinkFilter();

    @objid ("660a353b-aa11-4e68-a4bf-402807a86ec3")
    void apply(ILinkEditorConfiguration aConfig);

    @objid ("402b3e17-368f-420c-9935-4ca81d6ad71b")
    void addPropertyChangeListener(PropertyChangeListener listener);

    @objid ("4f06fadb-f5c5-43a1-bd8c-adc47606ec5e")
    void removePropertyChangeListener(PropertyChangeListener listener);

}
