/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.module.report;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.LocationAdapter;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.help.IWorkbenchHelpSystem;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.navigation.INavigationService;
import org.modelio.api.plugin.Api;
import org.modelio.api.ui.ModelioDialog;
import org.modelio.platform.rcp.system.ModelioHelpSystem;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * The report dialog is used by modules willing to display the contents of a {@link Report} which they have built during a processing operation by adding {@link ReportEntry} records to it.
 * 
 * A Report is a sequence of ReportEntry. A Reportentry can be of several kind (see @link {@link EntryKind}. The contents of the report are displayed in HTML rendred text in a browser.
 * 
 * Two kind of links are exclusively supported:
 * <ul>
 * <li>modelio model element links that allow to navigate to a given element listed in the report</li>
 * <li>modelio help topic links that pops the Modelio help for a given topic.</li>
 * </ul>
 * The ReportDialog browser is configured to ignore any other type of link.
 * 
 * The @link {@link HTMLReport} private class is in charge to lay out the reported message in HTML text, controlled by its internal CSS stylesheet {@link HTMLReport#CSS}.
 * 
 * <p>
 * Typical use:
 * <pre>
 * Report myReport = new Report();
 * myReport.addError(...);
 * myReport.addWarning(...);
 * myReport.addTip(...);
 * myReport.addInfo(...);
 * 
 * ReportDialog dlg = new ReportDialog(parent, navigationService, modelioHelpService, myReport);
 * dlg.open();
 * </pre>
 * </p>
 * @since 4.1
 */
@objid ("a517d575-5af7-431c-8070-ebb7e9617a06")
public class ReportDialog extends ModelioDialog {
    /**
     * The Modelio hep service used to displays help topics fromModelio documentation.
     */
    @objid ("a8dbbac1-5eb2-46c7-a0ac-ee23c2e6b8d4")
     IWorkbenchHelpSystem helpService;

    /**
     * The Report being displayed
     */
    @objid ("ff4e6d96-3378-449e-b9c9-8d87d830489e")
    private final Report report;

    /**
     * The Modelio navigation service used select elements in Modelio.
     */
    @objid ("1a470a1d-a387-4761-a933-1b5641004179")
    private INavigationService navigationService;

    @objid ("10951397-e353-4e5c-a2ca-9da9a9839d53")
    private IModelingSession session;

    /**
     * C'tor
     * 
     * @param parentShell the parent shell. Can be <code>null</code>.
     * @param session access to the project's model. Can be <code>null</code> in which case element links are not displayed and no navigation is possible..
     * @param navigationService the Modelio navigation service. Can be <code>null</code> in which case element links are not displayed and no navigation is possible.
     * @param report the report to display. Cannot be <code>null</code>.
     */
    @objid ("e541a99c-7ee0-45d1-abff-80fda5cf5442")
    public ReportDialog(Shell parentShell, final IModelingSession session, final INavigationService navigationService, Report report) {
        super(parentShell);
        setShellStyle(SWT.RESIZE | SWT.DIALOG_TRIM | getDefaultOrientation());
        
        this.session = session;
        this.navigationService = navigationService;
        this.report = report;
        this.helpService = ModelioHelpSystem.getInstance();
    }

    @objid ("74e69d11-4e05-4b9a-8f8e-77c63dcf688e")
    @Override
    protected void addButtonsInButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.CLOSE_LABEL, true);
    }

    @objid ("01da107f-9dbd-4826-b334-32ffd6703680")
    @Override
    protected void init() {
        // Nothing to do
    }

    @objid ("66587b66-f364-4817-bb64-7834dd04a5ac")
    @Override
    protected Control createContentArea(Composite parent) {
        Browser browser = new Browser(parent, SWT.NONE);
        browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        browser.addLocationListener(new LocationAdapter() {
            @Override
            public void changing(LocationEvent event) {
                if (Objects.equals(event.location, "about:blank")) {
                    return;
                }
                try {
                    URI uri = new URI(event.location);
                    if (Objects.equals(uri.getScheme(), "modelio")) {
                        if (Objects.equals(uri.getPath(), "/element")) {
                            MRef mRef = new MRef(uri.getFragment());
                            MObject target = ReportDialog.this.session != null ? ReportDialog.this.session.findByRef(mRef) : null;
                            ReportDialog.this.navigationService.fireNavigate(target);
                        } else if (Objects.equals(uri.getPath(), "/help")) {
                            if (ReportDialog.this.helpService != null) {
                                ReportDialog.this.helpService.displayHelpResource(uri.getFragment());
                            }
                        }
                    }
        
                } catch (URISyntaxException e) {
                    Api.LOG.error("Invalid URI " + event.location);
                    Api.LOG.debug(e);
                }
                event.doit = false;
            }
        });
        browser.setText(new HTMLReport(this.report, this.navigationService != null && this.session != null, this.helpService != null).getText());
        getShell().setText(this.report.getTitle());
        return browser;
    }

    @objid ("8b399229-f4e0-41b7-8e98-1b4e9d4b13ef")
    @Override
    protected Point getInitialSize() {
        return new Point(700, 800);
    }

    /**
     * This class renders the report s a HTML document string.
     */
    @objid ("de918d8a-0fc9-4004-bc2f-3693005c3073")
    private static class HTMLReport {
        @objid ("23b00d41-7428-4b0d-9a19-4541ee7b1e2a")
        private final String CSS = "<style>"
                + "body {background-color: linen;}"
                + ".summary{color:gray;font-size:0.75em;}"
                + ".info{color:black;font-weight: bold;margin-left:4px;font-size:0.85em;}"
                + ".entry{margin-top:4px;margin-bottom:12px;color: black;font-size:0.75em;}"
                + ".error{color:red;margin-left:20px;}"
                + ".warning{color:chocolate;margin-left:20px;}"
                + ".tip{color:green;margin-left:20px;}"
                + ".help{}"
                + ".category{color:grey;margin: 0;position: absolute;right: 10px;}"
                + ".message{color: black;font-style:italic;margin-left:34px;}"
                + ".links{margin-left:34px;margin-top:2px;}"
                + "</style>";

        @objid ("eae94003-761b-4fe4-b4dd-f8ef1235d0a4")
        private String text;

        @objid ("5a539b51-1966-4a74-a610-dd81da3d88c3")
        private boolean withNavigation;

        @objid ("30bbcc64-4d74-4f03-b849-134b97871dc1")
        private boolean withHelp;

        @objid ("7451f33b-52e9-46b6-813a-34b08fb05493")
        public HTMLReport(Report report, boolean withNavigation, boolean withHelp) {
            this.withNavigation = withNavigation;
            this.withHelp = withHelp;
            this.text = produce(report);
        }

        @objid ("ae9b19e2-2ed1-4343-a495-79b2829fd235")
        public String getText() {
            return this.text;
        }

        @objid ("157abc77-d7c9-4345-bb49-0ae56aec5e88")
        private String produce(Report report) {
            StringBuilder html = new StringBuilder();
            String title = report.getTitle() != null ? report.getTitle() : "";
            
            // Produce the html document header
            html.append(String.format("<!DOCTYPE html>\n <html><head>%s<title>%s</title></head><body>\n", this.CSS, title));
            
            // Produce the report title and summary
            produceTitle(html, report);
            
            // Produce report entries
            produceEntries(html, report.getEntries());
            return html.toString();
        }

        @objid ("77010baf-a9d6-411f-8b47-6e0c9fbdd577")
        private void produceEntries(StringBuilder html, List<ReportEntry> entries) {
            for (ReportEntry e : entries) {
                switch (e.getKind()) {
                case ERROR:
                    produceError(html, e);
                    break;
                case TIP:
                    produceTip(html, e);
                    break;
                case WARNING:
                    produceWarning(html, e);
                    break;
                case INFO:
                    produceInfo(html, e);
                    break;
                default:
                    produceInfo(html, e);
                    break;
                }
            }
        }

        @objid ("0a592760-4e18-459c-a99e-89fe86dd1d70")
        private void produceTitle(StringBuilder html, Report report) {
            if (!report.getTitle().isEmpty()) {
                html.append(String.format("<h3>%s</h3>\n", report.getTitle()));
            }
            
            long nErrors = report.getEntries().stream().filter(ReportEntry::isError).count();
            long nWarnings = report.getEntries().stream().filter(ReportEntry::isWarning).count();
            html.append(String.format("<div class=\"summary\">%s, %d Error(s), %d Warning(s)</div>\n", new Date().toString(), nErrors, nWarnings));
        }

        @objid ("ff1846fb-0ef4-4df2-8a33-598eacb5bcd9")
        private void produceError(StringBuilder html, ReportEntry e) {
            html.append(String.format("<div class=\"entry\">\n"));
            html.append(String.format("<span class=\"error\">%s %d </span>", e.getKind(), e.getCode()));
            produceHelp(html, e);
            produceCategory(html, e);
            produceMessage(html, e);
            produceLinks(html, e);
            html.append(String.format("</div>\n"));
        }

        @objid ("5259dadf-d6fa-4da6-ac93-34a817926fb3")
        private void produceWarning(StringBuilder html, ReportEntry e) {
            html.append(String.format("<div class=\"entry\">\n"));
            html.append(String.format("<span class=\"warning\">%s %d </span>", e.getKind(), e.getCode()));
            produceHelp(html, e);
            produceCategory(html, e);
            produceMessage(html, e);
            produceLinks(html, e);
            html.append(String.format("</div>\n"));
        }

        @objid ("927e7a71-21d6-4e0d-9568-3d9a4a94ce57")
        private void produceTip(StringBuilder html, ReportEntry e) {
            html.append(String.format("<div class=\"entry\">\n"));
            html.append(String.format("<span class=\"tip\">%s %d </span>", e.getKind(), e.getCode()));
            produceHelp(html, e);
            produceCategory(html, e);
            produceMessage(html, e);
            produceLinks(html, e);
            html.append(String.format("</div>\n"));
        }

        @objid ("7fae74a0-df9c-43d6-bd4c-ae35a9439486")
        private void produceInfo(StringBuilder html, ReportEntry e) {
            html.append(String.format("<hr>\n"));
            html.append(String.format("<div class=\"info\">%s</div>\n", e.getMessage()));
        }

        /**
         * Produce HTML links for the linked elements.<br>
         * The href syntax is <code>"modelio:/element#'ArchiMate'%20%7Bb18a2429-49a6-499e-9cfe-1222471f3635%7D%20Archimate.ArchimateProject"</code> which is later recognized by the browser location listener and intercepted to select the corresponding
         * element in Modelio.
         */
        @objid ("8379d5d3-8347-498b-831f-00aaf2f6ef70")
        private void produceLinks(StringBuilder html, ReportEntry e) {
            if (this.withNavigation && !e.getLinkedObjects().isEmpty()) {
                html.append(String.format("<div class=\"links\">\n"));
                for (MObject o : e.getLinkedObjects()) {
                    MRef mRef = new MRef(o);
            
                    try {
                        URI uri = new URI("modelio", null, "/element", null, mRef.toString());
                        String s = String.format("<a href=\"%s\">%s</a> \n", uri.toString(), mRef.name);
                        html.append(s);
            
                    } catch (URISyntaxException e1) {
                        Api.LOG.error("Invalid element URI");
                        Api.LOG.debug(e1);
                    }
            
                }
                html.append(String.format("</div>\n"));
            }
        }

        /**
         * The help link which is produced uses a specialized href syntax to be later recognized by the browser location listener and intercepted to call the Modelio help system.<br>
         * 
         * The href syntax is: <code>"modelio:/help#help topic url"</code>.
         */
        @objid ("a4db7082-5dff-4487-9b72-c700eff1e8c6")
        private void produceHelp(StringBuilder html, ReportEntry e) {
            if (this.withHelp && e.getHelpUrl() != null && !e.getHelpUrl().isEmpty()) {
                try {
                    URI uri = new URI("modelio", null, "/help", null, e.getHelpUrl());
                    html.append(String.format("<span class=\"help\"><a href=\"%s\">(?)</a></span>", uri.toString()));
                } catch (URISyntaxException e1) {
                    Api.LOG.error("Invalid help URI");
                    Api.LOG.debug(e1);
                }
            }
        }

        @objid ("648ea142-93a2-46fa-b0a6-ed4f20663a8c")
        private void produceMessage(StringBuilder html, ReportEntry e) {
            String s = e.getMessage().replaceAll("\\n", "<br>\n");
            html.append(String.format("<div class=\"message\">%s</div>\n", s));
        }

        @objid ("90404cf4-b058-4d33-96f0-a086ebdc5ca0")
        private void produceCategory(StringBuilder html, ReportEntry e) {
            if (e.getCategory() != null && !e.getCategory().isEmpty()) {
                html.append(String.format("<span class=\"category\">[%s]</span>\n", e.getCategory()));
            }
        }

    }

}
