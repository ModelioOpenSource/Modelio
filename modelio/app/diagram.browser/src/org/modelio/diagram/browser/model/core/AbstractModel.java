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

package org.modelio.diagram.browser.model.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.modelio.core.ui.swt.images.ElementDecoratedStyledLabelProvider;
import org.modelio.diagram.browser.model.IBrowserModel;
import org.modelio.vcore.session.api.ICoreSession;

@objid ("003ceccc-0d4f-10c6-842f-001ec947cd2a")
public abstract class AbstractModel implements IBrowserModel {
    @objid ("003d02b6-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public ICellModifier getLabelEditor(TreeViewer browserView, ICoreSession iCoreSession) {
        return new DefaultLabelEditor(browserView, iCoreSession);
    }

    @objid ("003d379a-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public IBaseLabelProvider getLabelProvider(TreeViewer browserView) {
        DefaultLabelProvider baseProvider = new DefaultLabelProvider(browserView);
        return new ElementDecoratedStyledLabelProvider(baseProvider, true, true);
    }

    @objid ("003d7ed0-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public ViewerSorter getSorter() {
        return new DefaultSorter();
    }

}
