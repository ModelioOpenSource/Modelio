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

package org.modelio.app.project.core.services.openproject;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.project.core.plugin.AppProjectCore;
import org.modelio.core.ui.swt.StyledTextHelper;
import org.modelio.gproject.gproject.GProblem;
import org.modelio.ui.dialog.ModelioDialog;

@objid ("72be6be3-3dba-43d4-9468-74dff63a1147")
public class GProblemReportDialog extends ModelioDialog {
    @objid ("3f76f710-3057-4485-b1f7-40b6e51e6a62")
    private String projectName;

    @objid ("cdf29826-01e8-4565-b1a0-25dcd89911b3")
    private List<GProblem> failures;

    @objid ("822b1260-d254-485c-b4cd-266e55d7264c")
    public static final void open(Shell parent, String projectName, List<GProblem> failures) {
        GProblemReportDialog dlg = new GProblemReportDialog(parent, projectName, failures);
        dlg.setBlockOnOpen(true);
        dlg.open();
    }

    @objid ("747e8b0b-0385-42f2-8692-92caeaafb65b")
    protected GProblemReportDialog(Shell parentShell, String projectName, List<GProblem> failures) {
        super(parentShell);
        this.projectName = projectName;
        this.failures = failures;
    }

    @objid ("f8cf5ef0-d355-41f8-9c9e-fa4724f89e08")
    @Override
    protected void addButtonsInButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.CLOSE_LABEL, true);
    }

    @objid ("9f776d23-c5d7-4c71-a15d-0f827107caee")
    @Override
    protected Control createContentArea(Composite parent) {
        StyledText styledText = new StyledText(parent, SWT.V_SCROLL | SWT.WRAP | SWT.BORDER);
        styledText.setBackground(parent.getBackground());
        ((GridLayout) parent.getLayout()).marginHeight = 5;
        ((GridLayout) parent.getLayout()).marginWidth = 5;
        GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
        layoutData.widthHint = 1; // Fixes styled text strange resize behavior in dialogs
        styledText.setLayoutData(layoutData);
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("<html>");
        sb.append(AppProjectCore.I18N.getMessage("ProjectService.ProjectSynchroProblems.message", this.projectName));
        sb.append("<br/>");
        
        for (final GProblem f : this.failures) {
            sb.append(String.format("<b>%s</b><br/>", f.getSubject()));
            sb.append(String.format("<i>  %s</i><br/><br/>", f.getProblem()));
        }
        sb.append("</html>");
        
        StyledTextHelper.setStyledText(sb.toString(), styledText);
        return styledText;
    }

    @objid ("cbd327c0-7c8a-4013-8385-bd275650243b")
    @Override
    protected void init() {
        setTitle(AppProjectCore.I18N.getMessage("ProjectService.ProjectSynchroProblems.title", this.projectName));
    }

    @objid ("055c5f70-d025-4ce3-8658-a6ae132f3685")
    @Override
    protected Point getInitialSize() {
        return new Point(800, 600);
    }

    @objid ("54fe8d73-5353-4678-9cc5-63560807751f")
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        setTitle("Shell title");
    }

}
