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

package org.modelio.diagram.styles.viewer;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewItem;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.platform.ui.panel.IPanelListener;

@objid ("8bbe7dd1-10ad-4830-b5b5-7db3ca4a9e5b")
class StyleEditPanelController {
    @objid ("9120fb75-40c2-4560-b8eb-16e7384b4cb8")
    private StyleEditPanelUI ui;

    @objid ("1fbfd473-4197-40e0-89fc-34e2af23aa54")
    private StyleEditPanelUIData data;

    @objid ("bbc46eed-1ef4-4c66-a1c9-0021f6183fa0")
    private final List<IPanelListener> listeners = new ArrayList<>();

    @objid ("32aa3d23-f30f-4e06-9aca-40313e55be25")
    public ColumnViewer createUi(Composite parent, boolean tableMode) {
        this.ui = new StyleEditPanelUI(this, tableMode);
        this.ui.createUI(parent);
        return this.ui.viewer;
    }

    @objid ("16edb377-18ff-4125-926f-5d1dc0128267")
    public synchronized void addListener(IPanelListener l) {
        if (this.listeners.contains(l)) {
            throw new InvalidParameterException("Listener already registered");
        }
        this.listeners.add(l);
    }

    @objid ("826a9574-1fd8-49e3-a6ec-23f044ed1b33")
    public synchronized void removeListener(IPanelListener l) {
        this.listeners.remove(l);
    }

    @objid ("5f698368-cefe-4366-a26b-daaaf14e791c")
    public StyleEditPanelController() {
        // nothing
    }

    @objid ("8b9ffe68-db28-4d59-ac1c-f981e40a4b0b")
    public void onPropertySelection(ISymbolViewItem item) {
        this.ui.setDescription(item == null ? "" : item.getDescription());
    }

    @objid ("d29b8fcf-0311-41b3-86c7-42546a1f7221")
    public StyleEditPanelUI getUi() {
        return this.ui;
    }

    @objid ("e44db11f-d5b3-4c35-af58-8f482141aff1")
    public StyleEditPanelUIData getData() {
        return this.data;
    }

    @objid ("a839442d-9ace-46df-a1e0-26390292d68e")
    public void setData(StyleEditPanelUIData data) {
        this.data = data;
        if (this.ui != null) {
            this.ui.update(this.data);
        }
    }

    @objid ("45515e54-6d9c-44c9-826c-5cb231f7a1db")
    private void fireListeners(StyleEditPanelUIData changedData, boolean isValidate) {
        this.listeners.forEach(l -> l.dataChanged(changedData, isValidate));
    }

    @objid ("ff091d5a-d2ef-4099-923a-3d7f926749e3")
    public void dispose() {
        this.ui.dispose();
        this.ui = null;
    }

    @objid ("1193c365-884c-4187-90e3-649dbbb3a1c9")
    public void showHelp(boolean showHelp) {
        if (showHelp) {
            this.ui.getSash().setWeights(new int[] { 90, 10 });
        
        } else {
            this.ui.getSash().setWeights(new int[] { 100, 0 });
        }
        this.ui.getSash().layout(true);
    }

    @objid ("05e661e6-5156-4995-bae8-5fdbf4c9700e")
    public void onStyleChanged() {
        fireListeners(this.data, true);
    }

    @objid ("0b8f605b-7232-4a0b-84f6-52f11a25555c")
    public void onNormalize(ISelection selection) {
        toStyleKeys(selection)
        .forEach(skey -> this.data.getStyleData().normalize(skey));
    }

    @objid ("6e9f32fa-a651-40f0-b327-2f887e9b187d")
    public void onReset(ISelection selection) {
        // Process StyleKey
        toStyleKeys(selection)
        .forEach(skey -> this.data.getStyleData().removeProperty(skey));
    }

    @objid ("9b2327f4-705c-4cfa-acb7-d8756d41d6ae")
    static Stream<StyleKey> toStyleKeys(ISelection selection) {
        return SelectionHelper.toStream(selection, ISymbolViewItem.class)
                                                        .map(i -> i.getStyleKey())
                                                        .filter(Objects::nonNull);
    }

    @objid ("d86bbd49-7f36-4aed-960b-dc5ddd3d33fa")
    public void onApplyTo(ISelection selection, IStyle applyTo) {
        toStyleKeys(selection)
        .forEach(skey -> {
            IStyle styleData = this.data.getStyleData();
            Object value = styleData.getProperty(skey);
            applyTo.setProperty(skey, value);
            styleData.removeProperty(skey);
        });
    }

}
