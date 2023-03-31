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
package org.modelio.platform.model.ui.swt.selectmetaclass.multiple;

import java.util.List;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Composite;
import org.modelio.platform.model.ui.swt.multistring.MultiStringEditionComposite;
import org.modelio.platform.ui.panel.IPanelProvider;
import org.modelio.vcore.smkernel.mapi.MClass;

@objid ("9f5d10af-d9c8-42b0-9878-f3c19b4f83a5")
public class MultipleMetaclassPanelProvider implements IPanelProvider {
    @objid ("c6098256-48e8-4ed2-908d-aef5e42d7ddc")
    private List<? extends MClass> candidates;

    @objid ("bbcfb80c-bcf1-4648-8eee-b704217d5143")
    private List<? extends MClass> selected;

    @objid ("57411868-a6a7-42ac-8c6a-e90faf00e0f6")
    private MultiStringEditionComposite multiStringEditor;

    @objid ("d97775e7-c05d-437b-b4cd-a4e0b094e138")
    public  MultipleMetaclassPanelProvider(List<? extends MClass> candidates) {
        this.candidates = candidates;
    }

    @objid ("6c5b73d9-872c-4865-ba52-8e390db7020a")
    @Override
    public boolean isRelevantFor(Object input) {
        return true;
    }

    @objid ("0b2032db-db30-42e3-8bd8-cf9e3ede2283")
    @Override
    public Object createPanel(Composite parent) {
        this.multiStringEditor = new MultiStringEditionComposite(parent, 0, 0);
        return this.multiStringEditor;
    }

    @objid ("d9516416-8d06-420a-b77b-3b5741349709")
    @Override
    public Object getPanel() {
        return this.multiStringEditor;
    }

    @objid ("654cec40-e0b8-44cd-a71d-bf061f952306")
    @Override
    public String getHelpTopic() {
        return null;
    }

    @objid ("61d138aa-2855-4c8f-b0fb-ed0c6316e799")
    @Override
    public Object getInput() {
        return this.selected;
    }

    @objid ("00d9d77c-d892-433b-8b65-5c3298fede6c")
    public List<? extends MClass> getSelected() {
        return this.selected;
    }

    @objid ("3694b27a-4ca6-452c-b7c8-0e65df4e2242")
    @Override
    public void setInput(Object input) {
        this.selected = (List<MClass>)input;
        this.multiStringEditor.initContent(this.candidates.stream()
                                                          .map((mc) -> mc.getQualifiedName())
                                                          .collect(Collectors.toList()));
        
    }

    @objid ("435c7406-cd14-491a-b404-ddc651ed134a")
    @Override
    public void dispose() {
        // Nothing to do
    }

}
