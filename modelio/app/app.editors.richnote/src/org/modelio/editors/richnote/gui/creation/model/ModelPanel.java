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

package org.modelio.editors.richnote.gui.creation.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.modelio.editors.richnote.gui.creation.RichNoteDescriptor;
import org.modelio.editors.richnote.plugin.EditorsRichNote;
import org.modelio.platform.ui.panel.IPanelListener;
import org.modelio.platform.ui.panel.IPanelProvider;

@objid ("ebc71a3f-0e5a-4a4a-bc4f-fff8e7d11181")
public class ModelPanel implements IPanelProvider {
    @objid ("e3d247c3-2edc-4141-acc0-e5c1d1c4e89c")
    private ModelPanelController controller;

    @objid ("4c1f28a6-89a9-45e4-a150-7b1c7920708a")
    public ModelPanel() {
        this.controller = new ModelPanelController();
    }

    @objid ("59dc3b81-2ef1-4f76-a6df-c1a39550eba7")
    @Override
    public void addListener(IPanelListener l) {
        this.controller.addListener(l);
    }

    @objid ("3e44ba2f-4251-4297-bd3a-d639412359ba")
    @Override
    public Control createPanel(Composite parent) {
        return this.controller.createUi(parent);
    }

    @objid ("9f654b58-2358-46e8-b921-483e6e904f71")
    @Override
    public void dispose() {
        this.controller.dispose();
    }

    @objid ("08acaf9c-ca48-4753-bf5a-dd11cdb40a33")
    @Override
    public String getHelpTopic() {
        return EditorsRichNote.I18N.getString("CreateDocumentDlg.HelpId");
    }

    @objid ("69bef75e-6afb-4597-a124-4fc70f61075f")
    @Override
    public RichNoteDescriptor getInput() {
        return this.controller.getData();
    }

    @objid ("69702e35-5d85-46d6-8e4e-2d5587186586")
    @Override
    public Control getPanel() {
        return this.controller.getUi();
    }

    @objid ("ac12fdb1-f9e3-47f1-a842-b76d0646c9e7")
    @Override
    public boolean isRelevantFor(Object obj) {
        return false;
    }

    @objid ("843bc5b7-1f15-42b7-963e-0f0407829c6b")
    @Override
    public void removeListener(IPanelListener l) {
        this.controller.removeListener(l);
    }

    @objid ("c9f86372-efae-4b95-b69c-cbb00458a346")
    @Override
    public void setInput(Object input) {
        if (input instanceof RichNoteDescriptor) {
            this.controller.setData((RichNoteDescriptor) input);
        } else {
            this.controller.setData(null);
        }
    }

}
