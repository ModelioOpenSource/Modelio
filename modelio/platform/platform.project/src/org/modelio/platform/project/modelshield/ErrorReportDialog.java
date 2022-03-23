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
package org.modelio.platform.project.modelshield;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IconAndMessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.modelio.platform.project.plugin.AppProjectCore;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelError;

/**
 * This class implements the dialog box that display a core audit error report
 */
@objid ("b622ae3f-4413-45e5-8da2-42b675fcb499")
public class ErrorReportDialog extends IconAndMessageDialog {
    @objid ("bb614345-76a3-40be-8411-9cd4f0d3e3b8")
    private String title;

    @objid ("9d11b9b1-4686-494d-89f7-be6a6e833e2a")
    private static final String CSSSTYLES = "#desc { margin-left:20px; color:grey; font-size:$font_size; font-family: $font_family; font-style: italic;}" + " #what {font-size:$font_size; font-family:$font-family; color:red;}";

    @objid ("7c731691-49d4-44c1-ab7a-0612842bc11a")
    private static String cssStyles;

    @objid ("ff04082f-045c-4cf3-9a3c-5740e524d23c")
    private String htmlErrorReport;

    @objid ("4fc27ce6-7554-4419-aa90-99f8fdeb90cb")
    protected  ErrorReportDialog(Shell parentShell, String dialogTitle, String message, String htmlErrorReport) {
        super(parentShell);
        setShellStyle(getShellStyle() | SWT.RESIZE);
        
        this.title = (dialogTitle == null) ? "" : dialogTitle;
        this.message = (message == null) ? "" : message;
        this.htmlErrorReport = htmlErrorReport;
        
    }

    @objid ("14f04041-5563-4339-9548-9ee647cc9931")
    @Override
    protected Image getImage() {
        return getErrorImage();
    }

    @objid ("11de29df-b5cc-408f-8176-2037a0f195ca")
    @Override
    protected Control createDialogArea(Composite parent) {
        createMessageArea(parent);
        getShell().setText(this.title);
        
        Browser browser = new Browser(parent, SWT.BORDER);
        GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        gd.horizontalSpan = 2;
        gd.widthHint = 600;
        gd.heightHint = 350;
        browser.setLayoutData(gd);
        
        // Fill contents
        browser.setText(this.htmlErrorReport);
        return parent;
    }

    @objid ("0b91d751-f628-4ca6-8095-349d88f7270a")
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, AppProjectCore.I18N.getString("CoreAudit.report.close"), true);
    }

    @objid ("d7c923a9-8483-4ab3-ac18-744aabb33ec6")
    private static String getInlineCSS() {
        if (ErrorReportDialog.cssStyles == null) {
            // Get body font family/size
            Font font = Display.getDefault().getSystemFont();
            String fontFamily = font.getFontData()[0].getName();
            int fontSize = font.getFontData()[0].getHeight();
            ErrorReportDialog.cssStyles = new String(ErrorReportDialog.CSSSTYLES);
            ErrorReportDialog.cssStyles = ErrorReportDialog.cssStyles.replaceAll("\\$font_family", fontFamily);
            ErrorReportDialog.cssStyles = ErrorReportDialog.cssStyles.replaceAll("\\$font_size", Integer.toString(fontSize) + "pt");
        }
        return ErrorReportDialog.cssStyles;
    }

    /**
     * Open an audit report dialog.
     * <p>
     * The method returns immediately and the dialog is not modal.
     * @param dialogTitle dialog title
     * @param message dialog message displayed just before the report.
     * @param errorReport the audit report to display.
     */
    @objid ("5b7d7cd0-a738-44d2-a4a4-b8341db3912b")
    public static void open(final String dialogTitle, final String message, final IErrorReport errorReport) {
        Display display = Display.getDefault();
        
        display.syncExec(() -> {
            // Build the HTMl before the transaction is rollbacked:
            // After roll back objects loose the modification that made them
            // invalid,
            // created objects become deleted and have no name, ...
            final String htmlReport = ErrorReportDialog.buildHtmlReport(errorReport);
        
            // The report dialog can open later
            display.asyncExec(() -> new ErrorReportDialog(display.getActiveShell(), dialogTitle, message, htmlReport).open());
        });
        
    }

    @objid ("4fc80fb1-3f2e-46b2-9369-33fbf1dc35bd")
    protected static String buildHtmlReport(IErrorReport errorReport) {
        StringBuilder msg = new StringBuilder();
        for (IModelError error : errorReport.getEntries()) {
            msg.append("<head><style>");
            msg.append(ErrorReportDialog.getInlineCSS());
            msg.append("</style></head>");
        
            msg.append("<p>");
            msg.append("<div id = \"what\">");
            msg.append(error.getRuleId() + ": ");
            msg.append(ErrorFormatter.getWhat(error));
            msg.append("</div>");
        
            msg.append("<br/><span id=\"desc\">");
            msg.append(ErrorFormatter.getDescription(error));
            msg.append("</span>");
            msg.append("</p>");
        
        }
        return msg.toString();
    }

    /**
     * The main composite of the dialog box The modeling session.
     */
    @objid ("9be58d3f-ba1b-4446-8c1a-5645f91bdc14")
    private static class ErrorFormatter {
        @objid ("47d9b127-ac89-4c70-b14f-cc3e5fa22d70")
        private static final String DESC_SUFFIX = ".desc";

        @objid ("8483a342-89c0-4e35-9651-0e293c35c9b7")
        private static final String WHAT_SUFFIX = ".what";

        @objid ("116fbff7-b4f7-4042-8985-d798a28d1217")
        private static final String I18N_PREFIX = "$";

        @objid ("8d74111e-9158-423a-b68e-1a3116549817")
        public static String getWhat(IModelError error) {
            String key = ErrorFormatter.I18N_PREFIX + error.getRuleId() + ErrorFormatter.WHAT_SUFFIX;
            try {
                String pattern = AppProjectCore.I18N.getString(key);
                return MessageFormat.format(pattern, ErrorFormatter.makeInfos(error.getElement(), error.getLinkedObjects()));
            } catch (@SuppressWarnings("unused") IllegalArgumentException e) {
                AppProjectCore.LOG.warning("Missing i18n for " + key);
                return "!" + key + "!";
            }
            
        }

        @objid ("6e89096d-6b66-440f-a4c4-e8d3505dfe54")
        public static String getDescription(IModelError error) {
            String key = ErrorFormatter.I18N_PREFIX + error.getRuleId() + ErrorFormatter.DESC_SUFFIX;
            try {
                return AppProjectCore.I18N.getString(key);
            } catch (@SuppressWarnings("unused") IllegalArgumentException e) {
                AppProjectCore.LOG.warning("Missing i18n for " + key);
                return "!" + key + "!";
            }
            
        }

        @objid ("9def5e99-268d-40e3-8525-0a1b2eec0399")
        private static Object[] makeInfos(Object main, List<Object> linkedObjects) {
            ArrayList<Object> infos = new ArrayList<>();
            infos.add(main);
            for (Object o : linkedObjects) {
                infos.add(o);
            }
            return infos.toArray();
        }

    }

}
