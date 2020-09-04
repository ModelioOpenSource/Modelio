/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.patterns.apply.gui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.modelio.api.ui.ModelioDialog;
import org.modelio.patterns.model.information.Parameter;
import org.modelio.patterns.plugin.Patterns;
import org.modelio.ui.panel.IPanelListener;

@objid ("da1fc62e-14e9-4e95-984d-3e20370e04be")
public class ApplyPatternDialog extends ModelioDialog {
    @objid ("2df530f6-b798-45e8-8486-4f891853071a")
    private ApplyPatternPanel panel;

    @objid ("0a2ccbf8-2f79-4b18-ad0f-33b16c9b5f07")
    @Override
    public void init() {
        setTitle(Patterns.I18N.getMessage("ApplyPatternDialog.title"));
        setMessage(Patterns.I18N.getMessage("ApplyPatternDialog.message"));
    }

    @objid ("47fab9da-4e13-4e7f-ac76-e37d7d62214a")
    @Override
    public Control createContentArea(Composite parent) {
        return this.panel.createPanel(parent);
    }

    @objid ("ce789058-f960-4059-ab99-0631c8a47a05")
    @Override
    public void addButtonsInButtonBar(Composite parent) {
        addDefaultButtons(parent);
        
        // Init ok button status
        ApplyPatternData data = (ApplyPatternData) this.panel.getInput();
        
        boolean parametersFilled = true;
        for (Parameter param : data.getPattern().getParameters()) {
            Object value = data.getParameterValues().get(param.getName());
            if (value == null || value.toString().isEmpty()) {
                parametersFilled = false;
                break;
            }
        }
        getButton(IDialogConstants.OK_ID).setEnabled(parametersFilled);
    }

    @objid ("4e76483e-a1c2-41eb-84e5-de4a7851129c")
    @Override
    protected Point getInitialSize() {
        return new Point(800, 600);
    }

    @objid ("2cb55bdc-399a-4b13-9ef8-854c6edea2bb")
    @Override
    protected String getHelpId() {
        return this.panel.getHelpTopic();
    }

    @objid ("4bcad5f1-2b53-4f9a-bb60-40c6bd913f95")
    public ApplyPatternDialog(Shell parentShell, ApplyPatternData data) {
        super(parentShell);
        this.setShellStyle(SWT.MODELESS | SWT.DIALOG_TRIM | SWT.RESIZE | SWT.MAX);
        
        this.panel = new ApplyPatternPanel();
        this.panel.setInput(data);
        this.panel.addListener(new IPanelListener() {
            @Override
            public void dataChanged(Object o, boolean isValidate) {
                ApplyPatternData data = (ApplyPatternData) o;
        
                boolean parametersFilled = true;
                for (Parameter param : data.getPattern().getParameters()) {
                    Object value = data.getParameterValues().get(param.getName());
                    if (value == null || value.toString().isEmpty()) {
                        parametersFilled = false;
                        break;
                    }
                }
                getButton(IDialogConstants.OK_ID).setEnabled(parametersFilled);
            }
        });
    }

    @objid ("22eb43b4-6776-487e-acc4-bddd9865ac76")
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(Patterns.I18N.getMessage("ApplyPatternDialog.title"));
    }

}
