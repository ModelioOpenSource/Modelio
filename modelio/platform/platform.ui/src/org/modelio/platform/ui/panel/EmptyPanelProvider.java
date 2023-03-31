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
package org.modelio.platform.ui.panel;

import java.util.function.BiConsumer;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.modelio.platform.ui.UIColor;
import org.modelio.platform.ui.UIFont;

/**
 * A IPanelProvider implementation that provides an 'empty panel'.<br/>
 * 
 * Depending on the C'tor being used the "empty" panel displays either an strictly empty composite filling its parent or a simple header label showing name nad metaclass icon when current input is a ModelElement.
 * <p>
 * To display the "with header" variant, the C'tor requires an update BiConsumer lambda method that is called to update the header label contents.
 * </p>
 * The setInput(obj) and getInput() do nothing, but 'obj' is held by the class to keep setInput(x) getInput()=>x calls consistent.
 * 
 * @since 5.2
 */
@objid ("39a5da03-b293-45e6-bcfd-e719615ac495")
public class EmptyPanelProvider implements IPanelProvider {
    @objid ("fc3f8588-8f6d-4344-8204-3c42161c22c6")
    private Composite container;

    @objid ("89f6be67-7c99-4032-8952-05ecc057bb82")
    private Object input;

    @objid ("a1da5c7e-7a77-4b30-8506-e9ee53cd483d")
    private CLabel header;

    @objid ("03c7d9aa-baf2-4a51-82b2-d99ec14eb105")
    private final BiConsumer<CLabel, Object> selectionLabelUpdate;

    @objid ("787b6b8a-9345-49e7-a356-ff9ec1e942c4")
    @Override
    public boolean isRelevantFor(Object input) {
        return true;
    }

    @objid ("cb18e2cb-eb6e-4158-af2e-7ea8ee009e08")
    @Override
    public Object createPanel(Composite parent) {
        this.container = new Composite(parent, SWT.NONE);
        this.container.setBackground(UIColor.WHITE);
        this.container.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        this.container.setLayout(new GridLayout(1, false));
        
        // The header: a label indicating the current object name (text) and type (icon)
        if (this.selectionLabelUpdate != null) {
            this.header = new CLabel(this.container, SWT.NONE);
            this.header.setFont(UIFont.LARGEB);
            this.header.setForeground(UIColor.SWT_LIST_SELECTION);
            GridData gd = new GridData(SWT.FILL, SWT.FILL, true, false);
            this.header.setLayoutData(gd);
        }
        return this.container;
    }

    @objid ("8ac48fd2-0055-4e39-ad7b-00d32a36fb1f")
    public  EmptyPanelProvider(BiConsumer<CLabel, Object> selectionLabelUpdate) {
        this.selectionLabelUpdate = selectionLabelUpdate;
    }

    @objid ("3706d705-e97d-404c-b6ac-e6cf5ea86556")
    public  EmptyPanelProvider() {
        this(null);
    }

    @objid ("03f3b4bf-232f-4ed0-aa41-7a49f8daba69")
    @Override
    public Object getPanel() {
        return this.container;
    }

    @objid ("a59c58f7-e429-4402-a601-a9bf5bfc4f6b")
    @Override
    public String getHelpTopic() {
        return null;
    }

    @objid ("f2796d78-2964-4b97-a3ee-45a85f162e54")
    @Override
    public Object getInput() {
        return this.input;
    }

    @objid ("62dc7ac1-ca22-4562-ae99-2a9de464a36d")
    @Override
    public void setInput(Object input) {
        if(!this.container.isDisposed()) {
            this.input = input;
            if (this.selectionLabelUpdate != null) {
                this.selectionLabelUpdate.accept(this.header, input);
            }
        }
        
    }

    @objid ("0c159f5d-c44a-4bb5-bce0-eff5c9124d9c")
    @Override
    public void dispose() {
        
    }

}
