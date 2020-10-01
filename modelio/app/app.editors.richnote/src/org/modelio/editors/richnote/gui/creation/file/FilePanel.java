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

package org.modelio.editors.richnote.gui.creation.file;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.modelio.editors.richnote.gui.creation.RichNoteDescriptor;
import org.modelio.editors.richnote.plugin.EditorsRichNote;
import org.modelio.platform.ui.panel.IPanelListener;
import org.modelio.platform.ui.panel.IPanelProvider;

@objid ("a4b5ee04-c3fe-4743-b6ec-39cd27d98a46")
class FilePanel implements IPanelProvider {
    @objid ("67348083-2c8c-4dd9-af6c-bb08c6e40d0f")
    private FilePanelController controller;

    @objid ("8ecba590-9afe-463b-8bfa-4b3003b073a7")
    public FilePanel() {
        this.controller = new FilePanelController();
    }

    @objid ("df2823ff-a2f5-4f2c-a9f4-80b3112c1fe0")
    @Override
    public void addListener(IPanelListener l) {
        this.controller.addListener(l);
    }

    @objid ("dba511a4-c295-4ee1-91a4-3cd4313951e6")
    @Override
    public Control createPanel(Composite parent) {
        return this.controller.createUi(parent);
    }

    @objid ("7b38a8a9-f6e1-414b-a07f-dc0f1355c4c4")
    @Override
    public void dispose() {
        this.controller.dispose();
    }

    @objid ("6f8f9502-84aa-4cd9-8fd9-b31ea5889f2e")
    @Override
    public String getHelpTopic() {
        return EditorsRichNote.I18N.getString("CreateDocumentDlg.HelpId");
    }

    @objid ("a2b4a33c-3404-42e9-90df-c4c4f0ba4ca2")
    @Override
    public RichNoteDescriptor getInput() {
        return this.controller.getData();
    }

    @objid ("5845f0bc-dfdc-484d-a227-3b39c431257c")
    @Override
    public Control getPanel() {
        return this.controller.getUi();
    }

    @objid ("4ed20ced-b99e-4f22-b27d-b40e9ce5d69d")
    @Override
    public boolean isRelevantFor(Object obj) {
        return false;
    }

    @objid ("417b8a75-debb-4c4f-9478-bfaa3268ea19")
    @Override
    public void removeListener(IPanelListener l) {
        this.controller.removeListener(l);
    }

    @objid ("c1529241-f414-4e74-ab6a-36879199840f")
    @Override
    public void setInput(Object input) {
        if (input instanceof RichNoteDescriptor) {
            this.controller.setData((RichNoteDescriptor) input);
        } else {
            this.controller.setData(null);
        }
    }

    @objid ("ab7b49b3-da1f-4e92-82f2-6d597cd888e6")
    public void setErrorIndication(String s) {
        this.controller.setErrorIndication(s);
    }

}
