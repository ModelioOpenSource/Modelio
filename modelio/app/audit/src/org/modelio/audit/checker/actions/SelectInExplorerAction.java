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

package org.modelio.audit.checker.actions;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.TreeItem;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.plugin.Audit;
import org.modelio.audit.view.model.AuditElementModel;
import org.modelio.ui.plugin.UI;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("dba3fb61-f279-4762-9a73-7c3b424a441b")
public class SelectInExplorerAction extends Action {
    @objid ("b8908aa3-372e-493d-b1ec-f9054d285033")
    private TreeViewer tree;

    @objid ("d50c56fa-68a0-4bdf-818b-b7ca1d2dd2b6")
    private IModelioNavigationService navigationService;

    @objid ("56f082dd-d319-48b7-a644-5f17d8ffc582")
    public SelectInExplorerAction(IModelioNavigationService navigationService, TreeViewer tree) {
        this.tree = tree;
        this.navigationService = navigationService;
        setText(Audit.I18N.getString("Audit.CheckerView.Contextual.Select"));
        setImageDescriptor(UI.getImageDescriptor("icons/selectinbrowser.png"));
    }

    @objid ("311ea849-b554-4e21-97fa-4d565a3f239e")
    @Override
    public void run() {
        List<MObject> elements = elements().collect(Collectors.toList());
        
        if (!elements.isEmpty()) {
            this.navigationService.fireNavigate(elements);
        }
    }

    @objid ("c843b139-b3b4-45f0-b152-2da7f03e0a36")
    @Override
    public boolean isEnabled() {
        return elements().findAny().isPresent();
    }

    @objid ("dcb8d97f-5e5a-473a-be9e-a8d919b7c731")
    private Stream<MObject> elements() {
        TreeItem[] items = this.tree.getTree().getSelection();
        Stream<MObject> stream = Stream.of(items)
                .map((it) -> {
                    Object obj = it.getData();
                    if (obj instanceof IAuditEntry) {
                        return ((IAuditEntry) obj).getElement();
                    } else if (obj instanceof AuditElementModel) {
                        return ((AuditElementModel) obj).element;
                    } else {
                        return null;
                    }
        
                })
                .filter(Objects::nonNull);
        return stream;
    }

}
