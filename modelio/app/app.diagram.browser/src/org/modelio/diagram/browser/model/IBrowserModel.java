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
package org.modelio.diagram.browser.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.modelio.vcore.session.api.ICoreSession;

/**
 * An IBrowserModel is used to configure the diagram browser tree aspect and
 * behavior. The IBrowserModel provides the tree's content provider, label
 * provider, editing support and content sorter.
 * 
 * @author phv
 */
@objid ("003083d8-0d4f-10c6-842f-001ec947cd2a")
public interface IBrowserModel {
    @objid ("2ea1b336-184d-4ee8-b86c-450c8ff8d0af")
    public static final String CONTEXT_MODEL = "context";

    @objid ("b11a202a-c3ba-400a-9785-94f665a41b0c")
    public static final String USER_MODEL = "user";

    @objid ("eaf4d4cb-8864-44f2-86f5-8c0b7b7f7b8e")
    public static final String BY_TYPE_MODEL = "byType";

    @objid ("b204129e-e65b-41e5-b02f-a49a18761f34")
    public static final String FLAT_MODEL = "flat";

    @objid ("82c2bc52-6286-48ec-85fe-5a78e3520eef")
    public static final String RELATED_MODEL = "related";

    @objid ("00309b2a-0d4f-10c6-842f-001ec947cd2a")
    ITreeContentProvider getContentProvider();

    @objid ("0030b614-0d4f-10c6-842f-001ec947cd2a")
    IBaseLabelProvider getLabelProvider(TreeViewer browserView);

    @objid ("0030ce88-0d4f-10c6-842f-001ec947cd2a")
    ViewerSorter getSorter();

    @objid ("0030d694-0d4f-10c6-842f-001ec947cd2a")
    ICellModifier getLabelEditor(TreeViewer browserView, ICoreSession iCoreSession);

}
